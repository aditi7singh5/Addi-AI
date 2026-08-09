package com.prepai.service;

import com.prepai.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final Map<String, Map<String, Map<String, List<Question>>>> questionBank = new HashMap<>();

    public QuestionService() {
        initializeQuestionBank();
    }

    private void initializeQuestionBank() {
        // --- SOFTWARE ENGINEER ---
        Map<String, Map<String, List<Question>>> sweTypes = new HashMap<>();
        
        // SWE Behavioral
        Map<String, List<Question>> sweBeh = new HashMap<>();
        sweBeh.put("junior", Arrays.asList(
            new Question("swe_beh_jr_1", 
                "Tell me about a time you faced a technical challenge during a project and how you resolved it.",
                "Focus on your personal contribution, how you researched the problem, and what you learned. Use the STAR method.",
                "In my university capstone project, database queries were failing silently. I used logging to isolate the connection pool issue, adjusted timeout parameters, and resolved it, improving response times by 30%.",
                Arrays.asList("STAR", "debug", "google", "documentation", "learned", "git", "team", "resolved", "mentor")),
            new Question("swe_beh_jr_2", 
                "How do you handle receiving critical feedback on your code from a senior developer during a code review?",
                "Show adaptability, eagerness to learn, and that you do not take technical critiques personally.",
                "I view code reviews as a learning opportunity. When a senior dev pointed out an O(N^2) complexity in my PR, I refactored it using a hash map to run in O(N).",
                Arrays.asList("feedback", "learn", "improve", "constructive", "senior", "review", "standards", "best practices")),
            new Question("swe_beh_jr_3",
                "Describe a scenario where you had to work with a teammate who had a very different working style than yours.",
                "Emphasize communication, compromise, and focus on the project's success.",
                "My partner preferred working nights while I worked mornings. We aligned by scheduling daily check-ins at 5 PM to sync our branches and progress.",
                Arrays.asList("communication", "alignment", "compromise", "collaboration", "understand", "listen", "respect"))
        ));
        sweBeh.put("mid", Arrays.asList(
            new Question("swe_beh_mid_1",
                "Describe a project you led or owned from start to finish. What difficulties did you encounter and how did you manage them?",
                "Highlight ownership, coordination with others, technical planning, and risk management.",
                "I owned migrating our legacy notification system to an async event-driven service. When Redis queue issues delayed testing, I stood up a local environment, diagnosed resource contention, and rescheduled tasks.",
                Arrays.asList("ownership", "milestone", "refactor", "risk", "communication", "stakeholders", "timeline", "architecture")),
            new Question("swe_beh_mid_2",
                "Tell me about a time you disagreed with a technical decision made by your lead or team. How did you handle it?",
                "Focus on data-driven discussions, respectful disagreement, and aligning with the final team decision.",
                "The team wanted to use MongoDB for a highly relational project. I built a prototype demonstrating foreign key join costs, presented Postgres benchmarks, and we agreed to go with Postgres.",
                Arrays.asList("data-driven", "disagree", "commit", "trade-offs", "alternative", "documentation", "benchmark")),
            new Question("swe_beh_mid_3",
                "How do you balance technical debt against the pressure to deliver new features rapidly?",
                "Discuss trade-offs, making tech debt visible, and negotiation with product managers.",
                "I log tech debt in our backlog. During sprint planning, I negotiate with the PM to allocate 10-15% of velocity to refactoring high-impact areas, preventing our velocity from dropping.",
                Arrays.asList("tech debt", "trade-off", "refactor", "refinement", "negotiation", "impact", "documentation", "velocity"))
        ));
        sweBeh.put("senior", Arrays.asList(
            new Question("swe_beh_sr_1",
                "Tell me about a high-stakes outage or system failure. How did you lead the mitigation and prevent it from happening again?",
                "Show calmness under pressure, clear communication, rapid mitigation, and a blameless post-mortem.",
                "During a peak promotion, checkout CPU spiked to 100%. I coordinated the response channel, initiated traffic-shedding rules, rolled back the deployment, and then authored a post-mortem to add bounds.",
                Arrays.asList("incident", "mitigation", "post-mortem", "monitoring", "rollback", "comms", "root cause", "redundancy")),
            new Question("swe_beh_sr_2",
                "How do you mentor junior engineers and help them grow technically and professionally?",
                "Talk about structured feedback, pair programming, delegating challenging tasks, and fostering a supportive culture.",
                "I set up weekly 1-on-1s, pair program on tricky bugs, and delegate ownership of small sub-systems. I ensure code reviews are encouraging and explain 'why'.",
                Arrays.asList("mentorship", "delegation", "growth", "pair programming", "feedback", "career", "supportive", "empowerment")),
            new Question("swe_beh_sr_3",
                "Tell me about a strategic architectural change you initiated. How did you gain buy-in from both engineers and business stakeholders?",
                "Address cost/benefit analysis, technical alignment, incremental migration plans, and explaining complex concepts to non-technical users.",
                "I proposed moving to a micro-frontend architecture. I wrote a formal RFC, demonstrated a prototype to engineering, presented cost-saving metrics to executives, and led an incremental migration.",
                Arrays.asList("ROI", "incremental migration", "RFC", "stakeholders", "alignment", "legacy", "prototype", "architecture"))
        ));
        sweTypes.put("behavioral", sweBeh);

        // SWE Technical
        Map<String, List<Question>> sweTech = new HashMap<>();
        sweTech.put("junior", Arrays.asList(
            new Question("swe_tech_jr_1",
                "Can you explain the difference between a Hash Map and a Binary Search Tree, and when you would use each?",
                "Compare search/insert time complexities (O(1) vs O(log n)), ordering guarantees, and memory considerations.",
                "A Hash Map offers O(1) average lookup and insertion time using a hash function, but does not maintain order. A Binary Search Tree offers O(log n) time but keeps keys sorted.",
                Arrays.asList("complexity", "lookup", "hash", "collision", "sorted", "ordered", "average case", "worst case", "O(1)", "O(log n)")),
            new Question("swe_tech_jr_2",
                "What is the event loop in JavaScript, and how does it handle asynchronous execution?",
                "Describe the call stack, callback queue, microtask queue (Promises), and the rendering pipeline.",
                "JavaScript is single-threaded. The event loop monitors the call stack and callback queue. When the stack is empty, it pushes asynchronous callbacks to the stack to be executed.",
                Arrays.asList("call stack", "callback queue", "microtask", "promise", "blocking", "single-threaded", "non-blocking", "event loop")),
            new Question("swe_tech_jr_3",
                "What are the core concepts of Object-Oriented Programming (OOP), and why are they useful?",
                "Mention Encapsulation, Inheritance, Polymorphism, and Abstraction with real-world analogies.",
                "OOP relies on Encapsulation (hiding state), Abstraction (hiding implementation details), Inheritance (reusing code), and Polymorphism (different behavior via shared interfaces).",
                Arrays.asList("encapsulation", "inheritance", "polymorphism", "abstraction", "reuse", "class", "interface", "modifiers"))
        ));
        sweTech.put("mid", Arrays.asList(
            new Question("swe_tech_mid_1",
                "How do database indexes work, and what are the write-time trade-offs of adding multiple indexes to a table?",
                "Discuss B-Trees, lookup complexity, query execution plan, and the overhead of maintaining indexes during updates.",
                "Indexes act as lookups, usually implemented via B-Trees, allowing binary-like search instead of full-table scans. However, every new index adds overhead on writes because the DB must update the B-Tree.",
                Arrays.asList("B-Tree", "index", "lookup", "scan", "overhead", "write latency", "read optimization", "query optimizer", "composite key")),
            new Question("swe_tech_mid_2",
                "Explain the difference between optimistic and pessimistic locking in databases, and when you would use each.",
                "Address write conflicts, transaction throughput, version numbers, lock duration, and deadlock risk.",
                "Optimistic locking assumes conflicts are rare, using a version field; it checks for changes before committing. Pessimistic locking locks the rows immediately (SELECT FOR UPDATE).",
                Arrays.asList("versioning", "conflict", "throughput", "concurrency", "lock", "deadlock", "isolation level", "optimistic", "pessimistic")),
            new Question("swe_tech_mid_3",
                "What is dependency injection, and how does it improve software testability and maintainability?",
                "Discuss decoupling, unit testing with mocks, dependency inversion, and application configuration flexibility.",
                "Dependency Injection passes dependent objects into a class rather than having the class instantiate them, decoupling classes and allowing easy mocking in unit testing.",
                Arrays.asList("decoupling", "unit test", "mocking", "inversion of control", "IoC", "dependency injection", "interface", "flexible"))
        ));
        sweTech.put("senior", Arrays.asList(
            new Question("swe_tech_sr_1",
                "How would you design a distributed lock service for multiple microservices? What guarantees are needed, and how do you ensure lease expiration?",
                "Explain race conditions, distributed consensus, locks with TTLs (e.g. Redlock), and fencing tokens.",
                "I would use a store like Redis or ZooKeeper with a TTL lease. To prevent client delays from causing lock overlap, I would include fencing tokens checked by the database to reject outdated lock owners.",
                Arrays.asList("consensus", "Redlock", "TTL", "fencing token", "Raft", "zookeeper", "split-brain", "distributed lock", "expiration")),
            new Question("swe_tech_sr_2",
                "Explain the CAP Theorem. How would you choose between AP and CP when designing a distributed financial transaction system vs a social media feed?",
                "Consistency, Availability, Partition Tolerance. Detail trade-offs like double spending vs feed latency.",
                "CAP states that in a partition, you must choose Consistency (CP) or Availability (AP). Financial systems require CP (strong consistency) to prevent balance errors; social feeds prefer AP (high availability).",
                Arrays.asList("CAP theorem", "eventual consistency", "strong consistency", "partition tolerance", "availability", "two-phase commit", "acid", "nosql"))
        ));
        sweTypes.put("technical", sweTech);

        // SWE System Design
        Map<String, List<Question>> sweSys = new HashMap<>();
        sweSys.put("junior", Arrays.asList(
            new Question("swe_sys_jr_1",
                "How would you design a basic URL shortening service (like Bitly)? What are the main components?",
                "Discuss the database schema (long URL, short hash), redirection logic, and caching of popular links.",
                "I would use a database mapping short codes to long URLs. The service generates a base62 hash from an ID. When a request comes in, a web server checks Redis, then redirects with 301/302.",
                Arrays.asList("redirection", "hash", "base62", "cache", "redis", "key-value", "database", "short url", "unique id"))
        ));
        sweSys.put("mid", Arrays.asList(
            new Question("swe_sys_mid_1",
                "Design a rate limiter for an API. What algorithm would you choose, where would you store the state, and how does it scale?",
                "Discuss algorithms (Token Bucket, Sliding Window), state in Redis, and handling concurrency race conditions.",
                "I would use the Token Bucket algorithm with state stored in Redis. To handle concurrent requests, I would run Redis Lua scripts to execute operations atomically, returning a 429 if exceeded.",
                Arrays.asList("token bucket", "sliding window", "redis", "lua script", "concurrency", "distributed", "rate limit", "HTTP 429", "headers"))
        ));
        sweSys.put("senior", Arrays.asList(
            new Question("swe_sys_sr_1",
                "Design a system like Ticketmaster that can handle massive traffic spikes for popular event sales without double booking.",
                "Address concurrency, database isolation level, seat reservation lifecycle, caching, and queues.",
                "I would place a virtual queue in front of the purchase flow to throttle requests. Seat maps are cached in Redis. When a seat is selected, a Redis lock reserves it. The purchase runs with DB row locks.",
                Arrays.asList("virtual queue", "pessimistic locking", "transaction isolation", "redis", "buffer", "concurrency control", "idempotent", "seat lock"))
        ));
        sweTypes.put("system-design", sweSys);
        
        questionBank.put("software-engineer", sweTypes);

        // --- PRODUCT MANAGER ---
        Map<String, Map<String, List<Question>>> pmTypes = new HashMap<>();
        Map<String, List<Question>> pmBeh = new HashMap<>();
        pmBeh.put("junior", Arrays.asList(
            new Question("pm_beh_jr_1",
                "Tell me about a product you use daily. What makes it great, and what is one feature you would add or improve?",
                "Structure with: User persona, value proposition, pain point, feature idea, and success metrics.",
                "I use Spotify daily because of recommendations. To improve it, I would add a local event mapper to notify users when artists they listen to are touring near them, measured by ticket link clicks.",
                Arrays.asList("user pain", "value proposition", "feature improvement", "UX", "engagement", "metrics", "target audience"))
        ));
        pmBeh.put("mid", Arrays.asList(
            new Question("pm_beh_mid_1",
                "How do you align cross-functional teams (engineering, design, sales) when launching a controversial new feature?",
                "Highlight empathy, sharing user data, transparent alignment meetings, and defining clear shared launch goals.",
                "I build alignment by sharing raw customer research and telemetry data first. I draft a detailed PRD, run collaborative walkthroughs, and establish a shared dashboard of post-launch metrics.",
                Arrays.asList("cross-functional", "alignment", "buy-in", "user data", "PRD", "shared vision", "compromise", "comms"))
        ));
        pmBeh.put("senior", Arrays.asList(
            new Question("pm_beh_sr_1",
                "Describe a time you had to make a pivot in product strategy. How did you identify the need, and how did you guide the transition?",
                "Address market conditions, strategic analysis, communicating the pivot to executive leadership, and managing team morale.",
                "Our B2C SaaS growth plateaued, but enterprise requests surged. I analyzed user expansion rates, built a business case showing higher LTV, secured executive approval, and transitioned the roadmap to enterprise features.",
                Arrays.asList("pivot", "strategy", "market research", "executive buy-in", "roadmap", "ROI", "opportunity cost", "vision"))
        ));
        pmTypes.put("behavioral", pmBeh);
        
        // Stub other PM categories
        Map<String, List<Question>> pmTech = new HashMap<>();
        pmTech.put("junior", Arrays.asList(
            new Question("pm_tech_jr_1",
                "What is an API, and how would you explain its purpose and value to a non-technical marketing teammate?",
                "Use analogies and explain how APIs enable integration and save development time.",
                "An API is like a waiter who takes your order to the kitchen, and returns with your food. It lets different applications talk to each other without knowing how they are built.",
                Arrays.asList("API", "integration", "analogy", "request", "response", "data exchange", "modular", "efficiency"))
        ));
        pmTech.put("mid", pmTech.get("junior"));
        pmTech.put("senior", pmTech.get("junior"));
        pmTypes.put("technical", pmTech);
        
        Map<String, List<Question>> pmSys = new HashMap<>();
        pmSys.put("junior", Arrays.asList(
            new Question("pm_sys_jr_1",
                "Explain the concept of 'microservices' and how it differs from a 'monolithic' application from a product manager's perspective.",
                "Focus on release velocity, risk isolation, team ownership, and maintenance complexity.",
                "A monolith is one giant codebase; if one part breaks, the whole app might crash. Microservices break the app into independent services, allowing faster deployments and isolated failures.",
                Arrays.asList("monolith", "microservice", "decoupling", "deployment velocity", "fault isolation", "independent scaling"))
        ));
        pmSys.put("mid", pmSys.get("junior"));
        pmSys.put("senior", pmSys.get("junior"));
        pmTypes.put("system-design", pmSys);
        
        questionBank.put("product-manager", pmTypes);

        // --- DATA ANALYST ---
        Map<String, Map<String, List<Question>>> daTypes = new HashMap<>();
        Map<String, List<Question>> daBeh = new HashMap<>();
        daBeh.put("junior", Arrays.asList(
            new Question("da_beh_jr_1",
                "How do you explain a complex statistical finding or data visualization to a non-technical business executive?",
                "Focus on the business outcome, skip complex formulas, and lead with the summary.",
                "I lead with the bottom line: 'Improving checkout conversion by 2% increases sales by $50k.' I present a clean bar chart and explain the recommended next action clearly.",
                Arrays.asList("analogy", "executive summary", "business impact", "translation", "visualization", "context", "actionable"))
        ));
        daBeh.put("mid", daBeh.get("junior"));
        daBeh.put("senior", daBeh.get("junior"));
        daTypes.put("behavioral", daBeh);

        Map<String, List<Question>> daTech = new HashMap<>();
        daTech.put("junior", Arrays.asList(
            new Question("da_tech_jr_1",
                "Explain the difference between a INNER JOIN, LEFT JOIN, and outer join in SQL.",
                "Describe row return behaviors, matching key requirements, and null generation.",
                "INNER JOIN returns only rows with matches in both tables. LEFT JOIN returns all rows from the left table and matched rows from the right; unmatched right values return NULL.",
                Arrays.asList("SQL", "join", "inner join", "left join", "null", "matching keys", "records", "database"))
        ));
        daTech.put("mid", daTech.get("junior"));
        daTech.put("senior", daTech.get("junior"));
        daTypes.put("technical", daTech);

        Map<String, List<Question>> daSys = new HashMap<>();
        daSys.put("junior", Arrays.asList(
            new Question("da_sys_jr_1",
                "What is a star schema in data warehousing? What are fact tables and dimension tables?",
                "Define fact tables (measurable data) vs dimension tables (descriptive attributes) and query speed benefits.",
                "A star schema organizes data into a central Fact Table linked by foreign keys to descriptive Dimension Tables. It simplifies queries and speeds up analysis.",
                Arrays.asList("star schema", "fact table", "dimension table", "foreign key", "joins", "warehouse", "denormalized"))
        ));
        daSys.put("mid", daSys.get("junior"));
        daSys.put("senior", daSys.get("junior"));
        daTypes.put("system-design", daSys);
        
        questionBank.put("data-analyst", daTypes);

        // --- UX DESIGNER ---
        Map<String, Map<String, List<Question>>> uxTypes = new HashMap<>();
        Map<String, List<Question>> uxBeh = new HashMap<>();
        uxBeh.put("junior", Arrays.asList(
            new Question("ux_beh_jr_1",
                "Walk me through your design process when working on a new feature. How do you start?",
                "Mention steps like: user research, wireframes, user testing, high-fidelity prototypes, and design handoffs.",
                "I start with empathy: interviewing users and looking at data. I create low-fidelity wireframes to iterate on layout quickly, run user tests, build a high-fidelity Figma prototype, and then handoff.",
                Arrays.asList("design process", "user research", "wireframe", "prototype", "testing", "empathy", "ideation", "handoff"))
        ));
        uxBeh.put("mid", uxBeh.get("junior"));
        uxBeh.put("senior", uxBeh.get("junior"));
        uxTypes.put("behavioral", uxBeh);

        Map<String, List<Question>> uxTech = new HashMap<>();
        uxTech.put("junior", Arrays.asList(
            new Question("ux_tech_jr_1",
                "What is the difference between UI and UX, and how do they work together?",
                "UI is the layout, styling, and visual elements. UX is the flow, logic, speed, and emotional journey.",
                "UI is the visual interface—the colors, buttons, and typography. UX is the user's overall journey, logic, and emotional experience.",
                Arrays.asList("UI", "UX", "visual design", "user flow", "usability", "interaction", "cohesion", "journey"))
        ));
        uxTech.put("mid", uxTech.get("junior"));
        uxTech.put("senior", uxTech.get("junior"));
        uxTypes.put("technical", uxTech);

        Map<String, List<Question>> uxSys = new HashMap<>();
        uxSys.put("junior", Arrays.asList(
            new Question("ux_sys_jr_1",
                "What is information architecture, and how does it help users navigate a large content site?",
                "Discuss sitemaps, categorization, mental models, navigation hierarchies, and label designs.",
                "Information architecture is the structure of content. It involves organizing pages and navigation in a way that matches users' mental models, verified through card-sorting tests.",
                Arrays.asList("information architecture", "navigation", "sitemap", "categorization", "mental model", "card sorting", "hierarchy"))
        ));
        uxSys.put("mid", uxSys.get("junior"));
        uxSys.put("senior", uxSys.get("junior"));
        uxTypes.put("system-design", uxSys);
        
        questionBank.put("ux-designer", uxTypes);
    }

    public List<Question> getQuestions(String role, String difficulty, String type, int limit) {
        Map<String, Map<String, List<Question>>> rolePool = questionBank.get(role);
        if (rolePool == null) return Collections.emptyList();

        Map<String, List<Question>> typePool = rolePool.get(type);
        if (typePool == null) return Collections.emptyList();

        List<Question> list = typePool.get(difficulty);
        if (list == null || list.isEmpty()) return Collections.emptyList();

        // Copy and shuffle
        List<Question> shuffled = new ArrayList<>(list);
        Collections.shuffle(shuffled);

        int actualLimit = Math.min(limit, shuffled.size());
        return shuffled.subList(0, actualLimit);
    }
}
