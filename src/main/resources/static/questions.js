// Addi AI Static Fallback Question Bank Database

const QUESTION_BANK = {
  "software-engineer": {
    "behavioral": {
      "junior": [
        {
          "id": "swe_beh_jr_1",
          "question": "Tell me about a time you worked on a team project. How did you resolve differences of opinion?",
          "hint": "Focus on collaboration, active listening, and compromising to achieve group goals.",
          "keywords": ["collaborate", "listen", "compromise", "consensus"],
          "sampleAnswer": "In my university capstone project, we disagreed on using SQL vs NoSQL. I set up a quick benchmark, we reviewed the data together, and chose SQL as a team consensus."
        },
        {
          "id": "swe_beh_jr_2",
          "question": "Describe a technical problem you faced. How did you diagnose and solve it?",
          "hint": "Walk through your logical debugging steps, tools used, and what you learned.",
          "keywords": ["debug", "diagnose", "logs", "breakpoint", "solve"],
          "sampleAnswer": "I faced a memory leak in a Node script. I used memory profiling tools, inspected garbage collection logs, found a listener that wasn't removed, and cleaned it up."
        },
        {
          "id": "swe_beh_jr_3",
          "question": "Describe a scenario where you had to work with a teammate who had a very different working style than yours.",
          "hint": "Emphasize communication, compromise, and focus on the project's success.",
          "keywords": ["communication", "alignment", "compromise", "collaboration", "understand", "listen", "respect"],
          "sampleAnswer": "My partner preferred working nights while I worked mornings. We aligned by scheduling daily check-ins at 5 PM to sync our branches and progress."
        },
        {
          "id": "swe_beh_jr_4",
          "question": "Describe a time you failed to meet a deadline. How did you handle it and what did you learn?",
          "hint": "Be honest, take accountability, and focus on how you communicate delays and adjust next steps.",
          "keywords": ["deadline", "delay", "accountability", "planning", "mitigation"],
          "sampleAnswer": "I underestimated a task's scope. I communicated the delay to my lead 2 days early, worked extra hours to mitigate, and now use split task estimates."
        },
        {
          "id": "swe_beh_jr_5",
          "question": "Why do you want to join our engineering team? What interests you about our technical architecture?",
          "hint": "Show that you researched the team's stack (Spring Boot, dynamic UI) and align with our engineering guidelines.",
          "keywords": ["architecture", "stack", "Spring Boot", "learn", "scale"],
          "sampleAnswer": "I want to work with Spring Boot backend services because of their scalability, and I'm eager to learn about real-world REST API design."
        }
      ],
      "mid": [
        {
          "id": "swe_beh_mid_1",
          "question": "Tell me about a time you disagreed with a technical decision made by your lead or team. How did you handle it?",
          "hint": "Focus on data-driven discussions, respectful disagreements, and commitment to the final decision.",
          "keywords": ["disagree", "data", "respectful", "alignment", "commitment"],
          "sampleAnswer": "Our lead wanted to deploy a monolithic update. I proposed a phased microservices migration with a risk matrix. We debated, agreed on a compromise, and I committed fully."
        },
        {
          "id": "swe_beh_mid_2",
          "question": "Describe a project where you had to learn a new technology under a tight deadline.",
          "hint": "Highlight your learning methodology, prioritization, and delivery success.",
          "keywords": ["learning", "methodology", "prioritization", "deadline", "delivery"],
          "sampleAnswer": "I had to learn Go in two weeks for a microservice migration. I built prototype endpoints, paired with senior peers, focused on idiomatic patterns, and shipped the service on time."
        },
        {
          "id": "swe_beh_mid_3",
          "question": "How do you balance technical debt against the pressure to deliver new features rapidly?",
          "hint": "Discuss trade-offs, making tech debt visible, and negotiation with product managers.",
          "keywords": ["tech debt", "trade-off", "refactor", "refinement", "negotiation", "impact", "documentation", "velocity"],
          "sampleAnswer": "I log tech debt in our backlog. During sprint planning, I negotiate with the PM to allocate 10-15% of velocity to refactoring high-impact areas, preventing our velocity from dropping."
        },
        {
          "id": "swe_beh_mid_4",
          "question": "Describe a time you had to onboard a new team member or explain a complex codebase to a peer.",
          "hint": "Highlight documentation, pair programming, starting with high-level architecture, and patience.",
          "keywords": ["onboard", "documentation", "architecture", "mentor", "pair programming"],
          "sampleAnswer": "I created a README checklist, walked them through our architecture diagram, and paired for 1 hour daily on small bugs to help them build confidence."
        },
        {
          "id": "swe_beh_mid_5",
          "question": "How do you handle scope creep when a product manager requests extra features in the middle of a sprint?",
          "hint": "Discuss negotiation, impact analysis, documenting changes, and swapping sprint tasks to maintain velocity.",
          "keywords": ["scope creep", "sprint", "negotiation", "velocity", "backlog", "swap"],
          "sampleAnswer": "I evaluate the effort. If critical, I discuss swapping a task of equal weight out of the current sprint with the PM to protect our velocity."
        }
      ],
      "senior": [
        {
          "id": "swe_beh_sr_1",
          "question": "How do you manage technical debt in your projects while balancing feature delivery?",
          "hint": "Discuss categorization of debt, setting aside engineering capacity, and explaining business value to product managers.",
          "keywords": ["technical debt", "refactor", "capacity", "business value", "trade-offs"],
          "sampleAnswer": "I categorize debt into critical, moderate, and low. I allocate 20% of sprint capacity to refactoring, mapping refactoring goals directly to performance improvements to show product value."
        }
      ]
    },
    "technical": {
      "junior": [
        {
          "id": "swe_tech_jr_1",
          "question": "Explain the difference between a Process and a Thread in operating systems.",
          "hint": "Mention memory address spaces, resource overhead, and communication complexity.",
          "keywords": ["process", "thread", "address space", "memory", "overhead", "ipc"],
          "sampleAnswer": "A process has its own address space and overhead, communicating via IPC. A thread shares memory with its parent process, is lightweight, and communicates directly."
        },
        {
          "id": "swe_tech_jr_2",
          "question": "What is the event loop in JavaScript, and how does it handle asynchronous execution?",
          "hint": "Describe the call stack, callback queue, microtask queue (Promises), and the rendering pipeline.",
          "keywords": ["call stack", "callback queue", "microtask", "promise", "blocking", "single-threaded", "non-blocking", "event loop"],
          "sampleAnswer": "JavaScript is single-threaded. The event loop monitors the call stack and callback queue. When the stack is empty, it pushes asynchronous callbacks to the stack to be executed."
        },
        {
          "id": "swe_tech_jr_3",
          "question": "What are the core concepts of Object-Oriented Programming (OOP), and why are they useful?",
          "hint": "Mention Encapsulation, Inheritance, Polymorphism, and Abstraction with real-world analogies.",
          "keywords": ["encapsulation", "inheritance", "polymorphism", "abstraction", "reuse", "class", "interface", "modifiers"],
          "sampleAnswer": "OOP relies on Encapsulation (hiding state), Abstraction (hiding implementation details), Inheritance (reusing code), and Polymorphism (different behavior via shared interfaces)."
        },
        {
          "id": "swe_tech_jr_4",
          "question": "Explain what REST APIs are and list the primary HTTP methods and their usage.",
          "hint": "Define REST principles, HTTP methods (GET, POST, PUT, DELETE), and status codes.",
          "keywords": ["REST", "HTTP", "GET", "POST", "PUT", "DELETE", "endpoints"],
          "sampleAnswer": "REST is an architectural style using stateless HTTP protocols. GET retrieves data, POST creates, PUT updates, and DELETE removes resources."
        },
        {
          "id": "swe_tech_jr_5",
          "question": "What is the difference between a SQL and NoSQL database? When would you prefer NoSQL?",
          "hint": "Compare tabular structured relational databases with schema-less document databases, addressing consistency and horizontal scaling.",
          "keywords": ["relational", "schema", "NoSQL", "document", "horizontal scaling", "ACID"],
          "sampleAnswer": "SQL is relational with a strict schema and ACID compliance. NoSQL is document-based, schema-less, and scales horizontally, ideal for unstructured logs."
        }
      ],
      "mid": [
        {
          "id": "swe_tech_mid_1",
          "question": "How do indexes work in relational databases, and what are the trade-offs of using them?",
          "hint": "Mention B-Trees, search speedups, and write performance impacts.",
          "keywords": ["indexes", "B-tree", "lookup", "write performance", "overhead", "reads"],
          "sampleAnswer": "Indexes speed up read lookups by creating a B-Tree structure. However, they add overhead to writes (inserts, updates, deletes) because index nodes must rebalance."
        },
        {
          "id": "swe_tech_mid_2",
          "question": "Explain the difference between optimistic and pessimistic locking in databases, and when you would use each.",
          "hint": "Address write conflicts, transaction throughput, version numbers, lock duration, and deadlock risk.",
          "keywords": ["versioning", "conflict", "throughput", "concurrency", "lock", "deadlock", "isolation level", "optimistic", "pessimistic"],
          "sampleAnswer": "Optimistic locking assumes conflicts are rare, using a version field; it checks for changes before committing. Pessimistic locking locks the rows immediately (SELECT FOR UPDATE)."
        },
        {
          "id": "swe_tech_mid_3",
          "question": "What is dependency injection, and how does it improve software testability and maintainability?",
          "hint": "Discuss decoupling, unit testing with mocks, dependency inversion, and application configuration flexibility.",
          "keywords": ["decoupling", "unit test", "mocking", "inversion of control", "IoC", "dependency injection", "interface", "flexible"],
          "sampleAnswer": "Dependency Injection passes dependent objects into a class rather than having the class instantiate them, decoupling classes and allowing easy mocking in unit testing."
        },
        {
          "id": "swe_tech_mid_4",
          "question": "What is a Connection Pool in database connectivity? Why is it useful, and what happens if it runs out of connections?",
          "hint": "Discuss connection reuse, overhead of establishing TCP connections, and pool exhaustion timeouts.",
          "keywords": ["connection pool", "reuse", "overhead", "exhaustion", "timeout", "HikariCP"],
          "sampleAnswer": "A connection pool keeps database connections open to reuse them, avoiding TCP handshakes. If exhausted, requests block until a connection is returned or times out."
        },
        {
          "id": "swe_tech_mid_5",
          "question": "Explain the concept of MVC (Model-View-Controller) architecture. How is it implemented in Spring Boot?",
          "hint": "Map database entities (Model), static resources/templates (View), and REST endpoints/controllers (Controller).",
          "keywords": ["MVC", "controller", "model", "view", "Spring Boot", "DispatcherServlet"],
          "sampleAnswer": "MVC separates business logic (Model), user interface (View), and input handler (Controller). In Spring Boot, DispatcherServlet routes requests to Controller beans."
        }
      ],
      "senior": [
        {
          "id": "swe_tech_sr_1",
          "question": "Explain the concept of Garbage Collection in modern virtual machines (e.g., JVM or V8). What are the main pause profiles?",
          "hint": "Discuss generations, mark-and-sweep, stop-the-world pauses, and tuning configurations.",
          "keywords": ["JVM", "V8", "generations", "mark-and-sweep", "stop-the-world", "G1GC"],
          "sampleAnswer": "Garbage collection dynamically reclaims heap memory using young/old generations and mark-and-sweep phases. Pause profiles include stop-the-world pauses, which we tune using G1GC."
        }
      ]
    },
    "system-design": {
      "junior": [
        {
          "id": "swe_sys_jr_1",
          "question": "What is a load balancer, and why would you use one in a web application?",
          "hint": "Mention distribution of traffic, high availability, and round-robin algorithms.",
          "keywords": ["load balancer", "high availability", "distribution", "round-robin", "scale"],
          "sampleAnswer": "A load balancer distributes traffic across multiple backend servers to prevent overload, provide redundancy, and scale requests."
        }
      ],
      "mid": [
        {
          "id": "swe_sys_mid_1",
          "question": "Design a simple URL shortening service (like Bitly). What database choices would you make?",
          "hint": "Discuss read-to-write ratios, caching strategies, and unique ID generation.",
          "keywords": ["shortener", "base62", "hashing", "cache", "NoSQL", "Redis"],
          "sampleAnswer": "I'd use a Base62 encoding on auto-incrementing IDs. Since reads vastly outnumber writes, I'd use Redis for caching and a relational database for metadata mapping."
        }
      ],
      "senior": [
        {
          "id": "swe_sys_sr_1",
          "question": "Design a global real-time chat system (like WhatsApp). How do you handle message persistence and active connection management?",
          "hint": "Discuss WebSockets, message brokers, caching active sessions, and Cassandra/NoSQL storage.",
          "keywords": ["WebSocket", "broker", "Kafka", "Cassandra", "active session", "scale"],
          "sampleAnswer": "I would use WebSockets for connections, Redis to store session presence, Kafka for message routing, and Cassandra for decentralized, high-write historical storage."
        }
      ]
    }
  },
  "product-manager": {
    "behavioral": {
      "junior": [
        {
          "id": "pm_beh_jr_1",
          "question": "Tell me about a time you had to coordinate between engineering and design. How did you align them?",
          "hint": "Focus on trade-offs, scope definitions, and shared alignment meetings.",
          "keywords": ["coordinate", "align", "engineering", "design", "trade-offs"],
          "sampleAnswer": "I held three-way design reviews, aligning designers on technical limitations and developers on user experience goals to reach compromise."
        }
      ],
      "mid": [
        {
          "id": "pm_beh_mid_1",
          "question": "How do you handle feature prioritization when stakeholders disagree on business importance?",
          "hint": "Mention frameworks like RICE or MoSCoW, data analytics, and stakeholder management.",
          "keywords": ["prioritization", "RICE", "MoSCoW", "stakeholders", "data-driven"],
          "sampleAnswer": "I apply the RICE framework (Reach, Impact, Confidence, Effort) to score features objectively, aligning stakeholders around a transparent score matrix."
        }
      ],
      "senior": [
        {
          "id": "pm_beh_sr_1",
          "question": "Describe a product launch that failed. What did you learn and how did you pivot?",
          "hint": "Focus on post-mortem, metrics evaluation, customer interviews, and subsequent roadmap iterations.",
          "keywords": ["fail", "post-mortem", "metrics", "interview", "pivot", "roadmap"],
          "sampleAnswer": "We launched a subscription tier that had low conversion. I ran customer interviews, realized the pricing was too complex, simplified it, and rebounded conversion."
        }
      ]
    },
    "technical": {
      "junior": [
        {
          "id": "pm_tech_jr_1",
          "question": "What is an API, and how would you explain its purpose to a non-technical marketing teammate?",
          "hint": "Use analogies (like a waiter in a restaurant) and explain integration value.",
          "keywords": ["API", "waiter analogy", "integration", "exchange", "data"],
          "sampleAnswer": "An API is like a restaurant waiter: it takes your order (request), goes to the kitchen (server), and brings you back your food (data)."
        }
      ],
      "mid": [
        {
          "id": "pm_tech_mid_1",
          "question": "Explain the difference between client-side rendering (CSR) and server-side rendering (SSR) from a product perspective.",
          "hint": "Discuss page load speed, SEO performance, and user experience.",
          "keywords": ["CSR", "SSR", "SEO", "load speed", "user experience"],
          "sampleAnswer": "CSR loads a shell first and renders on the client (faster app feel), while SSR renders on the server first, benefiting SEO and initial page load."
        }
      ],
      "senior": [
        {
          "id": "pm_tech_sr_1",
          "question": "How do you evaluate and prioritize technical debt as a Product Manager? What metrics do you look at?",
          "hint": "Discuss developer velocity, bug frequencies, crash rates, and roadmap balance.",
          "keywords": ["technical debt", "developer velocity", "bug rate", "refactor", "velocity"],
          "sampleAnswer": "I track sprint velocity and escape-defect rates. When debt starts reducing dev speed, I allocate dedicated sprint space for code cleanup."
        }
      ]
    },
    "system-design": {
      "junior": [
        {
          "id": "pm_sys_jr_1",
          "question": "What are microservices, and what are their product advantages over a monolith?",
          "hint": "Discuss independent deployment, reliability, and scaling teams.",
          "keywords": ["microservices", "monolith", "deployment", "scaling", "independence"],
          "sampleAnswer": "Microservices break an app into small independent services. This allows teams to build, deploy, and scale features without breaking other sections."
        }
      ],
      "mid": [
        {
          "id": "pm_sys_mid_1",
          "question": "How would you design the feedback loop metrics for a recommendation algorithm (like Netflix home feed)?",
          "hint": "Discuss click-through-rates, watch time, feedback loops, and metrics dashboard.",
          "keywords": ["recommendation", "CTR", "watch time", "feedback loop", "implicit signal"],
          "sampleAnswer": "I'd track CTR, average watch duration, and completion rates as positive implicit signals, and early exits as negative signals to optimize recommendations."
        }
      ],
      "senior": [
        {
          "id": "pm_sys_sr_1",
          "question": "Design the technical roadmap for scaling a product from 10k to 10M daily active users.",
          "hint": "Discuss caching, content delivery networks (CDNs), auto-scaling, database sharding, and service metrics.",
          "keywords": ["scale", "CDN", "caching", "auto-scaling", "sharding", "metrics"],
          "sampleAnswer": "I'd move static assets to a CDN, cache queries with Redis, introduce database replication/sharding, and set up auto-scaling containers based on resource metrics."
        }
      ]
    }
  },
  "data-analyst": {
    "behavioral": {
      "junior": [
        {
          "id": "da_beh_jr_1",
          "question": "Describe a time you found an error in a data report. How did you address it?",
          "hint": "Focus on data validation, root cause analysis, and transparent communication.",
          "keywords": ["error", "validation", "root cause", "transparent", "communication"],
          "sampleAnswer": "I noticed an anomaly in weekly revenue metrics. I traced it to a duplicate join in SQL, resolved the query, and communicated the corrected numbers transparently."
        }
      ],
      "mid": [
        {
          "id": "da_beh_mid_1",
          "question": "How do you explain statistical insights to non-analytical stakeholders?",
          "hint": "Discuss visual charts, simple vocab, and linking data directly to business decisions.",
          "keywords": ["stakeholders", "insights", "visualizations", "analogy", "business decision"],
          "sampleAnswer": "I avoid statistical jargon like p-values in meetings. Instead, I show clean bar charts and tell the story of how a 5% drop-off impacts marketing budgets."
        }
      ],
      "senior": [
        {
          "id": "da_beh_sr_1",
          "question": "How do you establish data governance and trust across different departments in a company?",
          "hint": "Discuss data catalogs, master records, standard SQL definitions, and training programs.",
          "keywords": ["governance", "trust", "catalog", "definitions", "alignment"],
          "sampleAnswer": "I built a centralized data catalog and aligned all team leads on a single database source of truth, standardizing calculations like 'active user'."
        }
      ]
    },
    "technical": {
      "junior": [
        {
          "id": "da_tech_jr_1",
          "question": "What is the difference between inner, left, and outer joins in SQL?",
          "hint": "Describe matching records, null values, and visual sets.",
          "keywords": ["inner join", "left join", "outer join", "records", "null"],
          "sampleAnswer": "Inner join returns matching rows. Left join returns all rows from the left and matching right rows (or nulls). Outer join returns all records from both."
        }
      ],
      "mid": [
        {
          "id": "da_tech_mid_1",
          "question": "Explain what A/B testing is and how you determine statistical significance.",
          "hint": "Discuss control groups, p-value thresholds, and confidence intervals.",
          "keywords": ["A/B test", "p-value", "significance", "control group", "confidence"],
          "sampleAnswer": "We compare a control group against a variation. We determine significance when the p-value is below our threshold (typically 0.05), indicating results aren't random."
        }
      ],
      "senior": [
        {
          "id": "da_tech_sr_1",
          "question": "Describe how you optimize query performance for datasets containing billions of rows in BigQuery or Snowflake.",
          "hint": "Discuss partition keys, clustering, query filters, and materialized views.",
          "keywords": ["optimize", "partition", "clustering", "materialized view", "scale"],
          "sampleAnswer": "I partition tables by date and cluster by high-frequency filter keys. I also write materialized views for complex dashboards to avoid full scans."
        }
      ]
    },
    "system-design": {
      "junior": [
        {
          "id": "da_sys_jr_1",
          "question": "What is a data warehouse, and how is it different from a transactional database?",
          "hint": "Discuss analytics optimization vs transactional consistency (OLAP vs OLTP).",
          "keywords": ["warehouse", "OLAP", "OLTP", "analytics", "transactional"],
          "sampleAnswer": "A transactional database (OLTP) is optimized for quick writes. A data warehouse (OLAP) is optimized for heavy analytics queries across massive historical datasets."
        }
      ],
      "mid": [
        {
          "id": "da_sys_mid_1",
          "question": "How would you design a data pipeline to sync real-time sales transactions to a dashboard?",
          "hint": "Discuss streaming logs, Kafka/Kinesis, loading schedules, and dashboard refreshes.",
          "keywords": ["pipeline", "Kafka", "streaming", "load", "refresh"],
          "sampleAnswer": "I'd stream logs via Kafka, feed them into a data lake, and configure auto-refresh dashboard queries on the streaming data."
        }
      ],
      "senior": [
        {
          "id": "da_sys_sr_1",
          "question": "Design a centralized business intelligence dashboard system. How do you handle schema changes and maintain query speeds?",
          "hint": "Discuss semantic modeling, caching layers, schema versions, and query optimization.",
          "keywords": ["semantic model", "cache", "schema version", "dbt", "performance"],
          "sampleAnswer": "I build a semantic modeling layer (like dbt) to handle schema versioning, configure Redis/DB caching, and schedule pre-aggregated tables overnight."
        }
      ]
    }
  },
  "ux-designer": {
    "behavioral": {
      "junior": [
        {
          "id": "ux_beh_jr_1",
          "question": "Tell me about a design feedback session where your proposal was criticized. How did you react?",
          "hint": "Focus on separating personal feelings from design, active questioning, and iterating.",
          "keywords": ["feedback", "criticize", "iterate", "empathy", "listening"],
          "sampleAnswer": "My checkout flow design was called confusing. I actively questioned the stakeholders to isolate the pain points, simplified the inputs, and iterated."
        }
      ],
      "mid": [
        {
          "id": "ux_beh_mid_1",
          "question": "Describe a project where user research directly changed your design roadmap.",
          "hint": "Discuss usability testing, findings, user behaviors, and design changes.",
          "keywords": ["research", "usability test", "findings", "behavior", "redesign"],
          "sampleAnswer": "We assumed users wanted filters. Usability tests showed they struggled to find search instead. We pivoted to prioritize search-bar visibility over filters."
        }
      ],
      "senior": [
        {
          "id": "ux_beh_sr_1",
          "question": "How do you align multiple product departments on a shared design system or UX philosophy?",
          "hint": "Discuss UI tokens, component libraries, design system governance, and cross-functional workshops.",
          "keywords": ["design system", "tokens", "governance", "components", "alignment"],
          "sampleAnswer": "I held workshops, set up reusable Figma tokens tied directly to code components, and formed a cross-functional system review council."
        }
      ]
    },
    "technical": {
      "junior": [
        {
          "id": "ux_tech_jr_1",
          "question": "What are the core contrast ratio requirements in WCAG 2.1 AA?",
          "hint": "Discuss contrast ratios for normal text, large text, and interactive elements.",
          "keywords": ["WCAG", "contrast ratio", "AA", "text", "accessibility"],
          "sampleAnswer": "WCAG 2.1 AA requires a contrast ratio of at least 4.5:1 for normal text and 3:1 for large text and interactive components."
        }
      ],
      "mid": [
        {
          "id": "ux_tech_mid_1",
          "question": "Explain the difference between user flows, task flows, and wireframes.",
          "hint": "Discuss visual fidelity, journey maps, and application structures.",
          "keywords": ["user flow", "task flow", "wireframe", "fidelity", "structure"],
          "sampleAnswer": "A task flow maps a single linear path. A user flow includes branching decisions. Wireframes show the layout structure of individual screens."
        }
      ],
      "senior": [
        {
          "id": "ux_tech_sr_1",
          "question": "Describe your process for ensuring accessibility compliance across a complex web portal.",
          "hint": "Discuss screen readers, keyboard navigation, aria-attributes, audits, and automated checks.",
          "keywords": ["accessibility", "screen reader", "keyboard", "aria", "audit", "compliance"],
          "sampleAnswer": "I audit semantic tags, test keyboard focus, verify aria labels with screen readers, and include automated accessibility linters in the build pipeline."
        }
      ]
    },
    "system-design": {
      "junior": [
        {
          "id": "ux_sys_jr_1",
          "question": "What is information architecture, and why is it important?",
          "hint": "Discuss site maps, labeling systems, and reducing cognitive load.",
          "keywords": ["information architecture", "site map", "labels", "cognitive load", "navigation"],
          "sampleAnswer": "Information architecture is organizing site content logically. It uses sitemaps and labeling to reduce cognitive load and simplify navigation."
        }
      ],
      "mid": [
        {
          "id": "ux_sys_mid_1",
          "question": "Design the navigation architecture for an e-commerce platform with over 10,000 distinct product categories.",
          "hint": "Discuss mega-menus, dynamic breadcrumbs, faceted search navigation, and mobile viewports.",
          "keywords": ["mega-menu", "breadcrumbs", "faceted search", "categories", "mobile"],
          "sampleAnswer": "I'd use a mega-menu on desktop, dynamic breadcrumbs, and a faceted search filter panel. On mobile, I'd implement a nested drill-down drawer."
        }
      ],
      "senior": [
        {
          "id": "ux_sys_sr_1",
          "question": "Design the personalization layout dashboard for a multi-user SaaS product. How do you handle configuration permissions?",
          "hint": "Discuss grid layouts, widgets, customization mode, role-based interfaces, and settings sync.",
          "keywords": ["grid", "widgets", "customization", "role-based", "permissions"],
          "sampleAnswer": "I use a card-grid layout where users can add/remove widgets. I toggle customization features based on their role permissions and sync settings."
        }
      ]
    }
  }
};
