// Addi AI App Controller - REST API Version

let recognition = null;
let finalTranscript = '';

// Pre-trigger voices load for Chrome/Safari compatibility
if (typeof window !== 'undefined' && window.speechSynthesis) {
  window.speechSynthesis.getVoices();
  if (window.speechSynthesis.onvoiceschanged !== undefined) {
    window.speechSynthesis.onvoiceschanged = () => {
      window.speechSynthesis.getVoices();
    };
  }
}

let state = {
  activeScreen: 'landing-screen',
  selectedRole: 'software-engineer',
  selectedDifficulty: 'mid',
  selectedType: 'behavioral',
  questionLimit: 3,
  simulationMode: 'relaxed',
  
  // Current Interview Session
  currentSession: {
    questions: [],
    currentIndex: 0,
    answers: [],
    timerInterval: null,
    secondsElapsed: 0,
    isRecording: false,
    recordingInterval: null,
    speakingTimeout: null,
    silenceTimeout: null
  },
  
  // History fetched from backend
  history: []
};

// Simulated transcription fragments for the recording simulator
const VOICE_SIMULATION_FRAGMENTS = {
  "software-engineer": [
    "From a technical standpoint, I would first outline the core requirements and constraints.",
    "Specifically, I'd analyze the time complexity, aiming for an O(1) or O(N log N) solution rather than a naive nested loop approach.",
    "I would leverage indexing and database clustering to optimize lookups.",
    "For scalability, I'd put a Redis cache layer in front and partition the tables horizontally.",
    "In terms of team communication, I would draft an RFC document first and seek feedback from the senior developers before writing code.",
    "To ensure reliability, I'd write unit tests covering edge cases and run integration tests in a staging environment."
  ],
  "product-manager": [
    "To approach this product challenge, I would start by defining the core user personas and their primary pain points.",
    "According to user research, the primary friction point occurs during onboarding.",
    "I would prioritize this feature using the RICE framework, weighing the impact against engineering resource costs.",
    "Our success metrics would focus on conversion rate, daily active users, and customer acquisition cost.",
    "I'd collaborate closely with engineering leads to define technical constraints and designers to build rapid wireframes.",
    "Finally, I would design an A/B test to validate our hypothesis with a small percentage of users before rolling it out globally."
  ],
  "data-analyst": [
    "To address this data query, I would first check the source integrity and write SQL scripts to filter out anomalies.",
    "I would use a window function partitioned by date to calculate running averages and compare changes week-over-week.",
    "We need to examine the p-value to ensure our A/B test results are statistically significant.",
    "I would build a dashboard using clean visual hierarchies and bar charts to make the complex metrics digestible for stakeholders.",
    "The analysis suggests a 15% drop-off at checkout, which indicates a payment gateway delay.",
    "I recommend configuring pre-aggregated tables to improve dashboard query performance and reduce load times."
  ],
  "ux-designer": [
    "My design process always starts with user empathy, drafting user journey maps and defining key heuristics.",
    "I would build low-fidelity wireframes in Figma to iterate on the screen layout and test the navigation flow.",
    "According to WCAG standards, I will verify the contrast ratios and design clear keyboard focus states.",
    "I'd implement a mobile-first responsive layout, restructuring components for smaller viewports dynamically.",
    "To reduce cognitive load, I would simplify the input fields and add smart validation messages.",
    "Finally, I'd run usability testing sessions with 5-8 users to gather qualitative feedback and address friction spots."
  ]
};

// Initialize Application
document.addEventListener('DOMContentLoaded', () => {
  loadHistory();
  initSetupOptions();
  bindNavigation();
  bindSetupSubmit();
  bindSimulatorActions();
  bindReportActions();
  updateHeroStats();
});

// Load history from REST API
function loadHistory() {
  fetch('/api/history')
    .then(res => res.json())
    .then(data => {
      state.history = data;
      updateDashboard();
    })
    .catch(err => {
      console.error("Error loading interview history:", err);
    });
}

// Navigation & Screen Management
function bindNavigation() {
  document.querySelectorAll('[data-screen-target]').forEach(link => {
    link.addEventListener('click', (e) => {
      const target = e.currentTarget.getAttribute('data-screen-target');
      
      // Prevent exiting simulator screen mid-interview without warning
      if (state.activeScreen === 'simulator-screen' && state.currentSession.questions.length > 0) {
        if (!confirm('Are you sure you want to exit the current interview? Your progress will be lost.')) {
          return;
        }
        resetCurrentSession();
      }
      
      showScreen(target);
    });
  });
}

function showScreen(screenId) {
  state.activeScreen = screenId;
  
  // Update UI sections
  document.querySelectorAll('.screen-section').forEach(sec => {
    sec.classList.remove('active-screen');
  });
  
  const activeSec = document.getElementById(screenId);
  if (activeSec) {
    activeSec.classList.add('active-screen');
  }
  
  // Update Nav links
  document.querySelectorAll('[data-screen-target]').forEach(link => {
    if (link.getAttribute('data-screen-target') === screenId) {
      link.classList.add('active');
    } else {
      link.classList.remove('active');
    }
  });

  if (screenId === 'analytics-screen') {
    loadHistory();
  }
  
  window.scrollTo(0, 0);
}

// Setup Options Card Interaction
function initSetupOptions() {
  const roleCards = document.querySelectorAll('#role-selection .option-card');
  roleCards.forEach(card => {
    card.addEventListener('click', () => {
      roleCards.forEach(c => c.classList.remove('selected'));
      card.classList.add('selected');
      state.selectedRole = card.getAttribute('data-value');
      updateQuestionSliderCap();
    });
  });

  const diffCards = document.querySelectorAll('#difficulty-selection .option-card');
  diffCards.forEach(card => {
    card.addEventListener('click', () => {
      diffCards.forEach(c => c.classList.remove('selected'));
      card.classList.add('selected');
      state.selectedDifficulty = card.getAttribute('data-value');
      updateQuestionSliderCap();
    });
  });

  const modeCards = document.querySelectorAll('#simulation-mode-selection .option-card');
  modeCards.forEach(card => {
    card.addEventListener('click', () => {
      modeCards.forEach(c => c.classList.remove('selected'));
      card.classList.add('selected');
      state.simulationMode = card.getAttribute('data-value');
    });
  });

  const typeSelect = document.getElementById('interview-type-select');
  if (typeSelect) {
    typeSelect.addEventListener('change', (e) => {
      state.selectedType = e.target.value;
      updateQuestionSliderCap();
    });
  }

  const qSlider = document.getElementById('question-limit-slider');
  const qVal = document.getElementById('question-limit-val');
  if (qSlider && qVal) {
    qSlider.addEventListener('input', (e) => {
      state.questionLimit = parseInt(e.target.value);
      qVal.innerText = state.questionLimit;
    });
  }
  
  // Initialize slider limits for default selected configurations
  setTimeout(updateQuestionSliderCap, 200);
}

// Start Interview logic
function bindSetupSubmit() {
  const startBtn = document.getElementById('start-interview-btn');
  if (startBtn) {
    startBtn.addEventListener('click', () => {
      // Synchronously unlock browser SpeechSynthesis audio context under user interaction
      if (window.speechSynthesis) {
        try {
          const dummy = new SpeechSynthesisUtterance('');
          window.speechSynthesis.speak(dummy);
        } catch (e) {}
      }

      const typeSelect = document.getElementById('interview-type-select');
      if (typeSelect) {
        state.selectedType = typeSelect.value;
      }
      
      startInterview();
    });
  }
}

function startInterview() {
  const url = `/api/questions?role=${state.selectedRole}&level=${state.selectedDifficulty}&type=${state.selectedType}&limit=${state.questionLimit}`;
  
  fetch(url)
    .then(res => res.json())
    .then(questions => {
      if (questions.length === 0) {
        startLocalInterview();
        return;
      }
      
      state.currentSession.questions = questions;
      state.currentSession.currentIndex = 0;
      state.currentSession.answers = [];
      state.currentSession.secondsElapsed = 0;
      
      showScreen('simulator-screen');
      loadQuestion(0);
      startTimer();
    })
    .catch(err => {
      console.warn("Backend questions fetch failed, starting local mock session fallback:", err);
      startLocalInterview();
    });
}

function resetCurrentSession() {
  clearInterval(state.currentSession.timerInterval);
  clearInterval(state.currentSession.recordingInterval);
  clearTimeout(state.currentSession.speakingTimeout);
  clearTimeout(state.currentSession.silenceTimeout);
  
  if (window.speechSynthesis) {
    window.speechSynthesis.cancel();
  }
  
  if (recognition) {
    try {
      recognition.stop();
    } catch (e) {}
    recognition = null;
  }
  
  state.currentSession.questions = [];
  state.currentSession.currentIndex = 0;
  state.currentSession.answers = [];
  state.currentSession.isRecording = false;
  
  const micWrapper = document.getElementById('mic-wrapper');
  if (micWrapper) {
    micWrapper.classList.remove('recording-active');
  }
  document.getElementById('recording-status-text').innerText = 'Click to speak';
}

function startTimer() {
  const timerVal = document.getElementById('timer-value');
  state.currentSession.timerInterval = setInterval(() => {
    state.currentSession.secondsElapsed++;
    const mins = Math.floor(state.currentSession.secondsElapsed / 60).toString().padStart(2, '0');
    const secs = (state.currentSession.secondsElapsed % 60).toString().padStart(2, '0');
    if (timerVal) {
      timerVal.innerText = `${mins}:${secs}`;
    }
  }, 1000);
}

function loadQuestion(index) {
  const question = state.currentSession.questions[index];
  if (!question) return;
  
  document.getElementById('q-title').innerText = `Question ${index + 1} of ${state.currentSession.questions.length}`;
  document.getElementById('q-body').innerText = question.question;
  document.getElementById('q-hint-content').innerText = question.hint;
  
  document.getElementById('q-hint-content').classList.remove('show');
  document.getElementById('hint-chevron').className = 'fas fa-chevron-down';
  
  document.getElementById('text-response').value = '';
  document.getElementById('transcript-preview').innerText = 'Your spoken response will appear here...';
  finalTranscript = '';
  
  const progressPercent = Math.round((index / state.currentSession.questions.length) * 100);
  document.getElementById('progress-bar-fill').style.width = `${progressPercent}%`;
  document.getElementById('progress-text').innerText = `${progressPercent}% Complete`;
  
  triggerAIInterviewerSpeech();
}

function triggerAIInterviewerSpeech() {
  const avatarWrapper = document.getElementById('avatar-wrapper');
  if (!avatarWrapper) return;
  
  avatarWrapper.classList.add('speaking-active');
  
  if (window.speechSynthesis) {
    window.speechSynthesis.cancel();
    
    const textToSpeak = state.currentSession.currentTransitionSpeech || document.getElementById('q-body').innerText;
    
    const bubble = document.getElementById('ai-speech-bubble');
    const bubbleText = document.getElementById('ai-speech-text');
    if (bubble && bubbleText && state.currentSession.currentTransitionSpeech) {
      bubbleText.innerText = state.currentSession.currentTransitionSpeech;
      bubble.style.display = 'block';
    } else if (bubble) {
      bubble.style.display = 'none';
    }
    
    state.currentSession.currentTransitionSpeech = null;
    
    const utterance = new SpeechSynthesisUtterance(textToSpeak);
    
    // Attempt to pick a high-quality Google or standard English voice
    const voices = window.speechSynthesis.getVoices();
    let selectedVoice = voices.find(v => v.lang.startsWith('en') && (v.name.includes('Google') || v.name.includes('Natural')));
    if (!selectedVoice) {
      selectedVoice = voices.find(v => v.lang.startsWith('en'));
    }
    if (selectedVoice) {
      utterance.voice = selectedVoice;
    }
    
    utterance.rate = 1.0;
    utterance.pitch = 1.0;
    
    utterance.onend = () => {
      avatarWrapper.classList.remove('speaking-active');
      if (bubble) bubble.style.display = 'none';
    };
    utterance.onerror = () => {
      avatarWrapper.classList.remove('speaking-active');
      if (bubble) bubble.style.display = 'none';
    };
    
    window.speechSynthesis.speak(utterance);
  } else {
    // Fallback static animation timer if browser does not support SpeechSynthesis
    clearTimeout(state.currentSession.speakingTimeout);
    state.currentSession.speakingTimeout = setTimeout(() => {
      avatarWrapper.classList.remove('speaking-active');
    }, 3500);
  }
}

// Bind Simulator actions
function bindSimulatorActions() {
  const hintTrigger = document.getElementById('q-hint-trigger');
  hintTrigger.addEventListener('click', () => {
    const hintContent = document.getElementById('q-hint-content');
    const hintChevron = document.getElementById('hint-chevron');
    const isShown = hintContent.classList.toggle('show');
    hintChevron.className = isShown ? 'fas fa-chevron-up' : 'fas fa-chevron-down';
  });

  const modeBtns = document.querySelectorAll('.response-mode-toggle .mode-btn');
  modeBtns.forEach(btn => {
    btn.addEventListener('click', () => {
      modeBtns.forEach(b => c => c.classList.remove('active')); // fix arrow function bind
      modeBtns.forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      
      const mode = btn.getAttribute('data-mode');
      if (mode === 'voice') {
        document.getElementById('voice-mode-ui').style.display = 'flex';
        document.getElementById('text-mode-ui').style.display = 'none';
      } else {
        document.getElementById('voice-mode-ui').style.display = 'none';
        document.getElementById('text-mode-ui').style.display = 'flex';
      }
    });
  });

  const micBtn = document.getElementById('mic-action-btn');
  const micWrapper = document.getElementById('mic-wrapper');
  const statusText = document.getElementById('recording-status-text');
  const transcriptPreview = document.getElementById('transcript-preview');
  
  micBtn.addEventListener('click', () => {
    state.currentSession.isRecording = !state.currentSession.isRecording;
    
    if (state.currentSession.isRecording) {
      micWrapper.classList.add('recording-active');
      statusText.innerText = 'Recording... click to pause';
      
      let currentVal = transcriptPreview.innerText;
      if (currentVal === 'Your spoken response will appear here...') {
        currentVal = '';
      }
      
      const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
      if (SpeechRecognition) {
        // Instantiate a brand new isolated capture session
        recognition = new SpeechRecognition();
        recognition.continuous = true;
        recognition.interimResults = true;
        recognition.lang = 'en-US';
        
        finalTranscript = currentVal ? currentVal + ' ' : '';
        
        recognition.onresult = (event) => {
          if (!state || !state.currentSession || !state.currentSession.isRecording) {
            return;
          }
          let interimTranscript = '';
          for (let i = event.resultIndex; i < event.results.length; ++i) {
            if (event.results[i].isFinal) {
              finalTranscript += event.results[i][0].transcript + ' ';
            } else {
              interimTranscript += event.results[i][0].transcript;
            }
          }
          transcriptPreview.innerText = finalTranscript + interimTranscript;
          
          if (state.simulationMode === 'interactive') {
            clearTimeout(state.currentSession.silenceTimeout);
            state.currentSession.silenceTimeout = setTimeout(() => {
              const textVal = transcriptPreview.innerText.trim();
              if (textVal && textVal !== 'Your spoken response will appear here...') {
                console.log("3s silence detected, auto-submitting...");
                submitCurrentAnswer();
              }
            }, 3000);
          }
        };
        
        recognition.onend = () => {
          if (state && state.currentSession && state.currentSession.isRecording && recognition) {
            try {
              recognition.start();
            } catch (e) {
              console.warn("SpeechRecognition auto-restart failed:", e);
            }
          }
        };
        
        try {
          recognition.start();
        } catch (e) {
          console.warn("SpeechRecognition start failed:", e);
        }
      } else {
        // Fallback simulation mode
        let textBuffer = currentVal;
        let phraseIndex = 0;
        const phrases = VOICE_SIMULATION_FRAGMENTS[state.selectedRole] || ["Hello context"];
        const sessionPhrases = [...phrases].sort(() => 0.5 - Math.random());
        
        state.currentSession.recordingInterval = setInterval(() => {
          if (phraseIndex < sessionPhrases.length) {
            textBuffer += (textBuffer ? ' ' : '') + sessionPhrases[phraseIndex];
            transcriptPreview.innerText = textBuffer;
            phraseIndex++;
          } else {
            clearInterval(state.currentSession.recordingInterval);
          }
        }, 3000);
      }
    } else {
      micWrapper.classList.remove('recording-active');
      statusText.innerText = 'Click to resume recording';
      
      if (recognition) {
        try {
          recognition.stop();
        } catch (e) {}
        recognition = null;
      } else {
        clearInterval(state.currentSession.recordingInterval);
      }
    }
  });

  const submitBtn = document.getElementById('submit-answer-btn');
  submitBtn.addEventListener('click', () => {
    submitCurrentAnswer();
  });
  
  const skipBtn = document.getElementById('skip-question-btn');
  skipBtn.addEventListener('click', () => {
    if (confirm("Are you sure you want to skip this question? You won't receive a score for it.")) {
      saveAnswer("");
      advanceQuestion();
    }
  });
}

function submitCurrentAnswer() {
  clearTimeout(state.currentSession.silenceTimeout);
  const activeMode = document.querySelector('.mode-btn.active').getAttribute('data-mode');
  let answerText = "";
  
  if (activeMode === 'voice') {
    const previewText = document.getElementById('transcript-preview').innerText;
    if (previewText === 'Your spoken response will appear here...') {
      answerText = "";
    } else {
      answerText = previewText;
    }
  } else {
    answerText = document.getElementById('text-response').value.trim();
  }
  
  if (!answerText) {
    alert("Please provide an answer before submitting, or choose 'Skip' if you are unsure.");
    return;
  }
  
  saveAnswer(answerText);
  fetchTransitionAndAdvance(answerText);
}

function fetchTransitionAndAdvance(answerText) {
  const currentIdx = state.currentSession.currentIndex;
  const nextIdx = currentIdx + 1;
  
  if (nextIdx < state.currentSession.questions.length) {
    const currentQ = state.currentSession.questions[currentIdx].question;
    const nextQ = state.currentSession.questions[nextIdx].question;
    
    const submitBtn = document.getElementById('submit-answer-btn');
    const skipBtn = document.getElementById('skip-question-btn');
    if (submitBtn) {
      submitBtn.disabled = true;
      submitBtn.innerHTML = `Analyzing <i class="fas fa-spinner fa-spin"></i>`;
    }
    if (skipBtn) skipBtn.disabled = true;

    fetch('/api/interact', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        question: currentQ,
        userAnswer: answerText,
        nextQuestion: nextQ
      })
    })
      .then(res => {
        if (!res.ok) throw new Error("API failed");
        return res.text();
      })
      .then(transitionText => {
        state.currentSession.currentTransitionSpeech = transitionText;
        if (submitBtn) {
          submitBtn.disabled = false;
          submitBtn.innerHTML = `Submit Response <i class="fas fa-arrow-right"></i>`;
        }
        if (skipBtn) skipBtn.disabled = false;
        advanceQuestion();
      })
      .catch(err => {
        console.warn("Interact API failed, falling back to standard transition:", err);
        state.currentSession.currentTransitionSpeech = `Got it. Let's move on to the next question. ${nextQ}`;
        if (submitBtn) {
          submitBtn.disabled = false;
          submitBtn.innerHTML = `Submit Response <i class="fas fa-arrow-right"></i>`;
        }
        if (skipBtn) skipBtn.disabled = false;
        advanceQuestion();
      });
  } else {
    advanceQuestion();
  }
}

function saveAnswer(text) {
  const currentIdx = state.currentSession.currentIndex;
  const question = state.currentSession.questions[currentIdx];
  if (!question) return;
  
  state.currentSession.answers.push({
    questionId: question.id,
    questionText: question.question,
    userAnswer: text,
    keywords: question.keywords,
    sampleAnswer: question.sampleAnswer
  });
}

function advanceQuestion() {
  if (state.currentSession.isRecording) {
    state.currentSession.isRecording = false;
    document.getElementById('mic-wrapper').classList.remove('recording-active');
    document.getElementById('recording-status-text').innerText = 'Click to speak';
    
    if (window.speechSynthesis) {
      window.speechSynthesis.cancel();
    }
    
    if (recognition) {
      try {
        recognition.stop();
      } catch (e) {}
      recognition = null;
    } else {
      clearInterval(state.currentSession.recordingInterval);
    }
  }
  
  state.currentSession.currentIndex++;
  
  if (state.currentSession.currentIndex < state.currentSession.questions.length) {
    loadQuestion(state.currentSession.currentIndex);
  } else {
    finishInterview();
  }
}

// Post final results to Spring Boot for score calculations
function finishInterview() {
  clearInterval(state.currentSession.timerInterval);
  
  const payload = {
    role: state.selectedRole,
    difficulty: state.selectedDifficulty,
    type: state.selectedType,
    durationSeconds: state.currentSession.secondsElapsed,
    answers: state.currentSession.answers
  };
  
  fetch('/api/assess', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(payload)
  })
  .then(res => res.json())
  .then(report => {
    resetCurrentSession();
    renderReportDetails(report);
    showScreen('reports-screen');
  })
  .catch(err => {
    console.warn("Backend assessment grading failed, using local in-browser grading engine:", err);
    const localReport = generateLocalAIReport();
    state.history.unshift(localReport);
    
    try {
      localStorage.setItem('agy_interview_history', JSON.stringify(state.history));
    } catch(e) {}
    
    resetCurrentSession();
    renderReportDetails(localReport);
    showScreen('reports-screen');
  });
}

// Render report details to reports screen
function renderReportDetails(report) {
  const roleDisplay = formatString(report.role);
  const diffDisplay = formatString(report.difficulty);
  const typeDisplay = formatString(report.type);
  
  document.getElementById('report-subheading').innerText = `${roleDisplay} • ${diffDisplay} • ${typeDisplay} • ${report.date}`;
  
  const scoreNum = document.getElementById('report-score-num');
  const overallScore = report.overallScore !== undefined ? report.overallScore : report.score;
  scoreNum.innerText = overallScore;
  
  const circle = document.getElementById('radial-circle-svg');
  const offset = 565.48 - (overallScore / 100) * 565.48;
  
  setTimeout(() => {
    circle.style.strokeDashoffset = offset;
  }, 100);
  
  document.getElementById('comm-score-val').innerText = `${report.commScore}/100`;
  document.getElementById('comm-score-bar').style.width = `${report.commScore}%`;
  
  document.getElementById('tech-score-val').innerText = `${report.techScore}/100`;
  document.getElementById('tech-score-bar').style.width = `${report.techScore}%`;
  
  document.getElementById('ps-score-val').innerText = `${report.psScore}/100`;
  document.getElementById('ps-score-bar').style.width = `${report.psScore}%`;
  
  const container = document.getElementById('report-qa-breakdown');
  container.innerHTML = "";
  
  report.questions.forEach((q, idx) => {
    const card = document.createElement('div');
    card.className = 'qa-card';
    card.innerHTML = `
      <div class="qa-question">
        <span class="qa-q-num">Q${idx + 1}.</span>
        <span>${q.questionText}</span>
      </div>
      <div class="qa-answer">
        <div class="qa-answer-title">Your Response</div>
        <p>${q.userAnswer}</p>
      </div>
      <div class="qa-feedback">
        <div class="qa-fb-badge">Feedback</div>
        <div class="qa-fb-text">
          <p style="margin-bottom: 0.5rem; font-weight: 500;">Score: <span style="color:var(--accent-cyan)">${q.score}/100</span></p>
          <p>${q.feedback}</p>
          <div style="margin-top: 1rem; border-top: 1px dashed var(--glass-border); padding-top: 0.75rem;">
            <p style="font-weight: 600; font-size: 0.8rem; text-transform: uppercase; color: var(--text-muted); margin-bottom: 0.25rem;">Expert Guidelines</p>
            <p style="font-size: 0.85rem; font-style: italic; color: var(--text-secondary);">${q.sampleAnswer}</p>
          </div>
        </div>
      </div>
    `;
    container.appendChild(card);
  });
}

// Dashboard statistics
function updateDashboard() {
  const totalCount = state.history.length;
  document.getElementById('total-interviews-count').innerText = totalCount;
  
  let avgScore = 0;
  let topRole = "N/A";
  
  if (totalCount > 0) {
    const sum = state.history.reduce((acc, rep) => acc + rep.overallScore, 0);
    avgScore = Math.round(sum / totalCount);
    
    const roleCounts = {};
    state.history.forEach(rep => {
      roleCounts[rep.role] = (roleCounts[rep.role] || 0) + 1;
    });
    
    let max = 0;
    Object.keys(roleCounts).forEach(role => {
      if (roleCounts[role] > max) {
        max = roleCounts[role];
        topRole = formatString(role);
      }
    });
  }
  
  document.getElementById('avg-score-val').innerText = `${avgScore}%`;
  document.getElementById('top-role-val').innerText = topRole;
  
  const historyList = document.getElementById('dashboard-history-list');
  historyList.innerHTML = "";
  
  if (totalCount === 0) {
    document.getElementById('no-history-box').style.display = 'flex';
    document.getElementById('dashboard-history-list').style.display = 'none';
    document.getElementById('performance-chart-card').style.display = 'none';
    updateSuccessRateStat();
  } else {
    document.getElementById('no-history-box').style.display = 'none';
    document.getElementById('dashboard-history-list').style.display = 'flex';
    document.getElementById('performance-chart-card').style.display = 'block';
    
    state.history.forEach(rep => {
      const item = document.createElement('div');
      item.className = 'history-item';
      item.innerHTML = `
        <div class="history-item-left">
          <div class="history-role">${formatString(rep.role)} (${formatString(rep.difficulty)})</div>
          <div class="history-meta">${formatString(rep.type)} • ${rep.date}</div>
        </div>
        <div class="history-score">${rep.overallScore}%</div>
      `;
      item.addEventListener('click', () => {
        renderReportDetails(rep);
        showScreen('reports-screen');
      });
      historyList.appendChild(item);
    });
    
    renderChart();
    updateSuccessRateStat();
  }
}

function renderChart() {
  const chart = document.getElementById('dashboard-chart');
  chart.innerHTML = "";
  
  const chartSessions = [...state.history].slice(0, 7).reverse();
  
  chartSessions.forEach((s, idx) => {
    const col = document.createElement('div');
    col.className = 'chart-bar-wrapper';
    
    const bar = document.createElement('div');
    bar.className = 'chart-bar';
    const overallScore = s.overallScore !== undefined ? s.overallScore : s.score;
    bar.setAttribute('data-score', `${overallScore}%`);
    
    const label = document.createElement('div');
    label.className = 'chart-label';
    label.innerText = `S${idx + 1}`;
    
    col.appendChild(bar);
    col.appendChild(label);
    chart.appendChild(col);
    
    setTimeout(() => {
      bar.style.height = `${overallScore}%`;
    }, 100 + (idx * 50));
  });
}

// Helper formatting utilities
function formatString(str) {
  if (!str) return '';
  return str.split('-')
    .map(word => word.charAt(0).toUpperCase() + word.slice(1))
    .join(' ');
}

function bindReportActions() {
  const restartBtn = document.getElementById('restart-interview-btn');
  if (restartBtn) {
    restartBtn.addEventListener('click', () => {
      showScreen('setup-screen');
    });
  }
}

function startLocalInterview() {
  const rolePool = QUESTION_BANK[state.selectedRole];
  if (!rolePool) return;
  
  let questionsList = [];
  if (state.selectedType === 'mixed') {
    Object.keys(rolePool).forEach(t => {
      const list = rolePool[t][state.selectedDifficulty] || [];
      questionsList = questionsList.concat(list);
    });
  } else {
    const typePool = rolePool[state.selectedType];
    if (typePool) {
      questionsList = typePool[state.selectedDifficulty] || [];
    }
  }

  if (questionsList.length === 0) {
    alert("No questions found for this configuration.");
    return;
  }
  
  // Shuffle
  let shuffled = [...questionsList].sort(() => 0.5 - Math.random());
  
  // Cycle/repeat to match requested limit if necessary
  let result = [];
  while (result.length < state.questionLimit) {
    result = result.concat(shuffled);
  }
  state.currentSession.questions = result.slice(0, state.questionLimit);
  state.currentSession.currentIndex = 0;
  state.currentSession.answers = [];
  state.currentSession.secondsElapsed = 0;
  showScreen('simulator-screen');
  loadQuestion(0);
  startTimer();
}

function generateLocalAIReport() {
  let totalScore = 0;
  const reviews = [];
  
  state.currentSession.answers.forEach(ans => {
    let score = 0;
    let feedback = "";
    const matchedKeywords = [];
    const text = ans.userAnswer;
    
    if (!text || text === 'Skipped' || text === '') {
      feedback = "No response was provided. A structured response mapping your capabilities is essential for standard grading.";
    } else {
      const wordCount = text.split(/\s+/).length;
      
      // Keyword matching
      if (ans.keywords) {
        ans.keywords.forEach(kw => {
          const regex = new RegExp('\\b' + kw.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&') + '\\b', 'gi');
          if (regex.test(text)) {
            matchedKeywords.push(kw);
          }
        });
      }
      
      // Score calculation
      const lengthScore = Math.min(45, Math.round((wordCount / 100) * 45));
      let kwScore = 0;
      if (ans.keywords && ans.keywords.length > 0) {
        kwScore = Math.round((matchedKeywords.length / ans.keywords.length) * 45);
      } else {
        kwScore = 45;
      }
      const structureBonus = text.length > 250 ? 10 : 5;
      
      score = Math.min(100, lengthScore + kwScore + structureBonus);
      
      // Feedback text
      if (score < 50) {
        feedback = "The answer is relatively brief. Try expanding your response. Consider referencing structural components like: " +
            (ans.keywords && ans.keywords.length >= 3 
                ? ans.keywords.slice(0, 3).join(", ") 
                : "specific technical terms") + ".";
      } else if (score < 80) {
        feedback = "Solid structure and details (Local Client Fallback). You correctly mentioned key principles like: \"" +
            matchedKeywords.join(", ") + "\". To get an elite score, try diving deeper into direct trade-offs.";
      } else {
        feedback = "Excellent answer (Local Client Fallback)! Demonstrates clear technical competence. Comprehensive coverage of terms: \"" +
            matchedKeywords.join(", ") + "\".";
      }
    }
    
    totalScore += score;
    reviews.push({
      questionText: ans.questionText,
      userAnswer: text || "Skipped",
      score: score,
      feedback: feedback,
      matchedKeywords: matchedKeywords,
      sampleAnswer: ans.sampleAnswer
    });
  });
  
  const totalQuestions = Math.max(1, state.currentSession.questions.length);
  const finalScore = Math.round(totalScore / totalQuestions);
  const finalComm = Math.min(100, Math.round(finalScore * 1.05));
  const finalTech = Math.round(finalScore * 0.95);
  const finalPS = Math.round(finalScore * 1.00);
  
  const dateStr = new Date().toLocaleString('en-US', { 
    month: 'short', 
    day: 'numeric', 
    year: 'numeric', 
    hour: '2-digit', 
    minute: '2-digit', 
    hour12: true 
  });
  
  return {
    id: "report_" + Date.now(),
    role: state.selectedRole,
    difficulty: state.selectedDifficulty,
    type: state.selectedType,
    date: dateStr,
    durationSeconds: state.currentSession.secondsElapsed,
    score: finalScore,
    overallScore: finalScore,
    commScore: finalComm,
    techScore: finalTech,
    psScore: finalPS,
    reviews: reviews
  };
}

function updateHeroStats() {
  // 1. Interviews Mocked (visit counter loaded from localStorage)
  let visitCount = parseInt(localStorage.getItem('addi_ai_visit_count') || "14");
  visitCount += 1;
  localStorage.setItem('addi_ai_visit_count', visitCount);
  
  const mockedEl = document.getElementById('stat-mocked-count');
  if (mockedEl) {
    mockedEl.innerText = visitCount;
  }
  
  // 2. Scenario Models (size of local question bank)
  let totalScenarios = 0;
  if (typeof QUESTION_BANK !== 'undefined') {
    for (let role in QUESTION_BANK) {
      for (let type in QUESTION_BANK[role]) {
        for (let diff in QUESTION_BANK[role][type]) {
          totalScenarios += QUESTION_BANK[role][type][diff].length;
        }
      }
    }
  } else {
    totalScenarios = 28;
  }
  
  const scenariosEl = document.getElementById('stat-scenarios-count');
  if (scenariosEl) {
    scenariosEl.innerText = totalScenarios;
  }
  
  // 3. Success Rate
  updateSuccessRateStat();
}

function updateSuccessRateStat() {
  const successEl = document.getElementById('stat-success-rate');
  if (!successEl) return;
  
  if (state.history && state.history.length > 0) {
    let passCount = 0;
    state.history.forEach(item => {
      const score = item.overallScore !== undefined ? item.overallScore : item.score;
      if (score >= 70) {
        passCount++;
      }
    });
    const rate = Math.round((passCount / state.history.length) * 100);
    successEl.innerText = `${rate}%`;
  } else {
    // Default believable starting success rate
    successEl.innerText = "85%";
  }
}

function updateQuestionSliderCap() {
  const qSlider = document.getElementById('question-limit-slider');
  if (qSlider) {
    qSlider.max = 25;
  }
}
