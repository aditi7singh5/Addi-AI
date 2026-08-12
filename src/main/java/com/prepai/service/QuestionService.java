package com.prepai.service;

import com.prepai.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "questionService")
public class QuestionService {
    private final Map<String, Map<String, Map<String, List<Question>>>> questionBank = new HashMap<>();

    public QuestionService() {
        initializeQuestionBank();
    }

    private void initializeQuestionBank() {
        // --- SOFTWARE-ENGINEER ---
        Map<String, Map<String, List<Question>>> softwareengineerTypes = new HashMap<>();

        // Focus Category: behavioral
        Map<String, List<Question>> softwareengineerBehavioral = new HashMap<>();
        softwareengineerBehavioral.put("junior", Arrays.asList(
            new Question(
                "swe_beh_jr_1",
                "Tell me about a time you faced a technical challenge during a project and how you resolved it.",
                "Focus on your personal contribution, how you researched the problem, and what you learned. Use the STAR method.",
                "In my university capstone project, database queries were failing silently. I used logging to isolate the connection pool issue, adjusted timeout parameters, and resolved it, improving response times by 30%.",
                Arrays.asList("STAR", "debug", "google", "documentation", "learned", "git", "team", "resolved", "mentor")
            ),
            new Question(
                "swe_beh_jr_2",
                "How do you handle receiving critical feedback on your code from a senior developer during a code review?",
                "Show adaptability, eagerness to learn, and that you do not take technical critiques personally.",
                "I view code reviews as a learning opportunity. When a senior dev pointed out an O(N^2) complexity in my PR, I refactored it using a hash map to run in O(N).",
                Arrays.asList("feedback", "learn", "improve", "constructive", "senior", "review", "standards", "best practices")
            ),
            new Question(
                "swe_beh_jr_3",
                "Describe a scenario where you had to work with a teammate who had a very different working style than yours.",
                "Emphasize communication, compromise, and focus on the project's success.",
                "My partner preferred working nights while I worked mornings. We aligned by scheduling daily check-ins at 5 PM to sync our branches and progress.",
                Arrays.asList("communication", "alignment", "compromise", "collaboration", "understand", "listen", "respect")
            ),
            new Question(
                "swe_beh_jr_4",
                "Describe a time you failed to meet a deadline. How did you handle it and what did you learn?",
                "Be honest, take accountability, and focus on how you communicate delays and adjust next steps.",
                "I underestimated a task's scope. I communicated the delay to my lead 2 days early, worked extra hours to mitigate, and now use split task estimates.",
                Arrays.asList("deadline", "delay", "accountability", "planning", "mitigation")
            ),
            new Question(
                "swe_beh_jr_5",
                "Why do you want to join our engineering team? What interests you about our technical architecture?",
                "Show that you researched the team's stack (Spring Boot, dynamic UI) and align with our engineering guidelines.",
                "I want to work with Spring Boot backend services because of their scalability, and I'm eager to learn about real-world REST API design.",
                Arrays.asList("architecture", "stack", "Spring Boot", "learn", "scale")
            )
        ));
        softwareengineerBehavioral.put("mid", Arrays.asList(
            new Question(
                "swe_beh_mid_1",
                "Describe a project you led or owned from start to finish. What difficulties did you encounter and how did you manage them?",
                "Highlight ownership, coordination with others, technical planning, and risk management.",
                "I owned migrating our legacy notification system to an async event-driven service. When Redis queue issues delayed testing, I stood up a local environment, diagnosed resource contention, and rescheduled tasks.",
                Arrays.asList("ownership", "milestone", "refactor", "risk", "communication", "stakeholders", "timeline", "architecture")
            ),
            new Question(
                "swe_beh_mid_2",
                "Tell me about a time you disagreed with a technical decision made by your lead or team. How did you handle it?",
                "Focus on data-driven discussions, respectful disagreement, and aligning with the final team decision.",
                "The team wanted to use MongoDB for a highly relational project. I built a prototype demonstrating foreign key join costs, presented Postgres benchmarks, and we agreed to go with Postgres.",
                Arrays.asList("data-driven", "disagree", "commit", "trade-offs", "alternative", "documentation", "benchmark")
            ),
            new Question(
                "swe_beh_mid_3",
                "How do you balance technical debt against the pressure to deliver new features rapidly?",
                "Discuss trade-offs, making tech debt visible, and negotiation with product managers.",
                "I log tech debt in our backlog. During sprint planning, I negotiate with the PM to allocate 10-15% of velocity to refactoring high-impact areas, preventing our velocity from dropping.",
                Arrays.asList("tech debt", "trade-off", "refactor", "refinement", "negotiation", "impact", "documentation", "velocity")
            ),
            new Question(
                "swe_beh_mid_4",
                "Describe a time you had to onboard a new team member or explain a complex codebase to a peer.",
                "Highlight documentation, pair programming, starting with high-level architecture, and patience.",
                "I created a README checklist, walked them through our architecture diagram, and paired for 1 hour daily on small bugs to help them build confidence.",
                Arrays.asList("onboard", "documentation", "architecture", "mentor", "pair programming")
            ),
            new Question(
                "swe_beh_mid_5",
                "How do you handle scope creep when a product manager requests extra features in the middle of a sprint?",
                "Discuss negotiation, impact analysis, documenting changes, and swapping sprint tasks to maintain velocity.",
                "I evaluate the effort. If critical, I discuss swapping a task of equal weight out of the current sprint with the PM to protect our velocity.",
                Arrays.asList("scope creep", "sprint", "negotiation", "velocity", "backlog", "swap")
            )
        ));
        softwareengineerBehavioral.put("senior", Arrays.asList(
            new Question(
                "swe_beh_sr_1",
                "Tell me about a high-stakes outage or system failure. How did you lead the mitigation and prevent it from happening again?",
                "Show calmness under pressure, clear communication, rapid mitigation, and a blameless post-mortem.",
                "During a peak promotion, checkout CPU spiked to 100%. I coordinated the response channel, initiated traffic-shedding rules, rolled back the deployment, and then authored a post-mortem to add bounds.",
                Arrays.asList("incident", "mitigation", "post-mortem", "monitoring", "rollback", "comms", "root cause", "redundancy")
            ),
            new Question(
                "swe_beh_sr_2",
                "How do you mentor junior engineers and help them grow technically and professionally?",
                "Talk about structured feedback, pair programming, delegating challenging tasks, and fostering a supportive culture.",
                "I set up weekly 1-on-1s, pair program on tricky bugs, and delegate ownership of small sub-systems. I ensure code reviews are encouraging and explain 'why'.",
                Arrays.asList("mentorship", "delegation", "growth", "pair programming", "feedback", "career", "supportive", "empowerment")
            ),
            new Question(
                "swe_beh_sr_3",
                "Tell me about a strategic architectural change you initiated. How did you gain buy-in from both engineers and business stakeholders?",
                "Address cost/benefit analysis, technical alignment, incremental migration plans, and explaining complex concepts to non-technical users.",
                "I proposed moving to a micro-frontend architecture. I wrote a formal RFC, demonstrated a prototype to engineering, presented cost-saving metrics to executives, and led an incremental migration.",
                Arrays.asList("ROI", "incremental migration", "RFC", "stakeholders", "alignment", "legacy", "prototype", "architecture")
            ),
            new Question(
                "swe_beh_sr_4",
                "How do you evaluate and prioritize conflicting requests from multiple engineering team leads?",
                "Discuss business priorities, resource availability, engineering constraints, and architectural alignment.",
                "I map requests to business value and engineering capacity. I organize a consensus meeting with all leads, present a prioritized technical roadmap, and resolve conflicts.",
                Arrays.asList("priority", "conflict", "negotiation", "roadmap", "architecture", "capacity")
            ),
            new Question(
                "swe_beh_sr_5",
                "Describe a time when you had to advocate for a project that was unpopular with business stakeholders.",
                "Focus on ROI, technical sustainability, security, or platform scalability arguments.",
                "We needed to upgrade our core DB engine which stakeholders saw as zero-feature. I presented a risk model showing potential downtime cost vs the upgrade cost, gaining quick approval.",
                Arrays.asList("advocate", "stakeholders", "ROI", "technical health", "security", "scaling")
            )
        ));
        softwareengineerTypes.put("behavioral", softwareengineerBehavioral);

        // Focus Category: technical
        Map<String, List<Question>> softwareengineerTechnical = new HashMap<>();
        softwareengineerTechnical.put("junior", Arrays.asList(
            new Question(
                "swe_tech_jr_1",
                "Can you explain the difference between a Hash Map and a Binary Search Tree, and when you would use each?",
                "Compare search/insert time complexities (O(1) vs O(log n)), ordering guarantees, and memory considerations.",
                "A Hash Map offers O(1) average lookup and insertion time using a hash function, but does not maintain order. A Binary Search Tree offers O(log n) time but keeps keys sorted.",
                Arrays.asList("complexity", "lookup", "hash", "collision", "sorted", "ordered", "average case", "worst case", "O(1)", "O(log n)")
            ),
            new Question(
                "swe_tech_jr_2",
                "What is the event loop in JavaScript, and how does it handle asynchronous execution?",
                "Describe the call stack, callback queue, microtask queue (Promises), and the rendering pipeline.",
                "JavaScript is single-threaded. The event loop monitors the call stack and callback queue. When the stack is empty, it pushes asynchronous callbacks to the stack to be executed.",
                Arrays.asList("call stack", "callback queue", "microtask", "promise", "blocking", "single-threaded", "non-blocking", "event loop")
            ),
            new Question(
                "swe_tech_jr_3",
                "What are the core concepts of Object-Oriented Programming (OOP), and why are they useful?",
                "Mention Encapsulation, Inheritance, Polymorphism, and Abstraction with real-world analogies.",
                "OOP relies on Encapsulation (hiding state), Abstraction (hiding implementation details), Inheritance (reusing code), and Polymorphism (different behavior via shared interfaces).",
                Arrays.asList("encapsulation", "inheritance", "polymorphism", "abstraction", "reuse", "class", "interface", "modifiers")
            ),
            new Question(
                "swe_tech_jr_4",
                "Explain what REST APIs are and list the primary HTTP methods and their usage.",
                "Define REST principles, HTTP methods (GET, POST, PUT, DELETE), and status codes.",
                "REST is an architectural style using stateless HTTP protocols. GET retrieves data, POST creates, PUT updates, and DELETE removes resources.",
                Arrays.asList("REST", "HTTP", "GET", "POST", "PUT", "DELETE", "endpoints")
            ),
            new Question(
                "swe_tech_jr_5",
                "What is the difference between a SQL and NoSQL database? When would you prefer NoSQL?",
                "Compare tabular structured relational databases with schema-less document databases, addressing consistency and horizontal scaling.",
                "SQL is relational with a strict schema and ACID compliance. NoSQL is document-based, schema-less, and scales horizontally, ideal for unstructured logs.",
                Arrays.asList("relational", "schema", "NoSQL", "document", "horizontal scaling", "ACID")
            )
        ));
        softwareengineerTechnical.put("mid", Arrays.asList(
            new Question(
                "swe_tech_mid_1",
                "How do database indexes work, and what are the write-time trade-offs of adding multiple indexes to a table?",
                "Discuss B-Trees, lookup complexity, query execution plan, and the overhead of maintaining indexes during updates.",
                "Indexes act as lookups, usually implemented via B-Trees, allowing binary-like search instead of full-table scans. However, every new index adds overhead on writes because the DB must update the B-Tree.",
                Arrays.asList("B-Tree", "index", "lookup", "scan", "overhead", "write latency", "read optimization", "query optimizer", "composite key")
            ),
            new Question(
                "swe_tech_mid_2",
                "Explain the difference between optimistic and pessimistic locking in databases, and when you would use each.",
                "Address write conflicts, transaction throughput, version numbers, lock duration, and deadlock risk.",
                "Optimistic locking assumes conflicts are rare, using a version field; it checks for changes before committing. Pessimistic locking locks the rows immediately (SELECT FOR UPDATE).",
                Arrays.asList("versioning", "conflict", "throughput", "concurrency", "lock", "deadlock", "isolation level", "optimistic", "pessimistic")
            ),
            new Question(
                "swe_tech_mid_3",
                "What is dependency injection, and how does it improve software testability and maintainability?",
                "Discuss decoupling, unit testing with mocks, dependency inversion, and application configuration flexibility.",
                "Dependency Injection passes dependent objects into a class rather than having the class instantiate them, decoupling classes and allowing easy mocking in unit testing.",
                Arrays.asList("decoupling", "unit test", "mocking", "inversion of control", "IoC", "dependency injection", "interface", "flexible")
            ),
            new Question(
                "swe_tech_mid_4",
                "What is a Connection Pool in database connectivity? Why is it useful, and what happens if it runs out of connections?",
                "Discuss connection reuse, overhead of establishing TCP connections, and pool exhaustion timeouts.",
                "A connection pool keeps database connections open to reuse them, avoiding TCP handshakes. If exhausted, requests block until a connection is returned or times out.",
                Arrays.asList("connection pool", "reuse", "overhead", "exhaustion", "timeout", "HikariCP")
            ),
            new Question(
                "swe_tech_mid_5",
                "Explain the concept of MVC (Model-View-Controller) architecture. How is it implemented in Spring Boot?",
                "Map database entities (Model), static resources/templates (View), and REST endpoints/controllers (Controller).",
                "MVC separates business logic (Model), user interface (View), and input handler (Controller). In Spring Boot, DispatcherServlet routes requests to Controller beans.",
                Arrays.asList("MVC", "controller", "model", "view", "Spring Boot", "DispatcherServlet")
            )
        ));
        softwareengineerTechnical.put("senior", Arrays.asList(
            new Question(
                "swe_tech_sr_1",
                "How would you design a distributed lock service for multiple microservices? What guarantees are needed, and how do you ensure lease expiration?",
                "Explain race conditions, distributed consensus, locks with TTLs (e.g. Redlock), and fencing tokens.",
                "I would use a store like Redis or ZooKeeper with a TTL lease. To prevent client delays from causing lock overlap, I would include fencing tokens checked by the database to reject outdated lock owners.",
                Arrays.asList("consensus", "Redlock", "TTL", "fencing token", "Raft", "zookeeper", "split-brain", "distributed lock", "expiration")
            ),
            new Question(
                "swe_tech_sr_2",
                "Explain the CAP Theorem. How would you choose between AP and CP when designing a distributed financial transaction system vs a social media feed?",
                "Consistency, Availability, Partition Tolerance. Detail trade-offs like double spending vs feed latency.",
                "CAP states that in a partition, you must choose Consistency (CP) or Availability (AP). Financial systems require CP (strong consistency) to prevent balance errors; social feeds prefer AP (high availability).",
                Arrays.asList("CAP theorem", "eventual consistency", "strong consistency", "partition tolerance", "availability", "two-phase commit", "acid", "nosql")
            ),
            new Question(
                "swe_tech_sr_3",
                "Explain the mechanics of Garbage Collection pause profiles in Java virtual machines (JVM). How does G1GC compare to ZGC?",
                "Discuss stop-the-world pauses, concurrent marking, compaction, and heap scaling thresholds.",
                "G1GC splits the heap into regions and compacted concurrently with small pause times. ZGC is concurrent, performing nearly all phases concurrently, keeping pauses under 1ms.",
                Arrays.asList("GC", "JVM", "G1GC", "ZGC", "concurrency", "stop-the-world", "pause time", "throughput")
            ),
            new Question(
                "swe_tech_sr_4",
                "How do distributed consensus algorithms like Raft or Paxos work at a high level? When are they needed?",
                "Discuss leader election, log replication, safety guarantees, split-brain scenario, and quorum sizes.",
                "Raft ensures state machine replication by electing a leader and replicating log entries to a quorum of nodes, safely handling partitions without brain splits.",
                Arrays.asList("consensus", "Raft", "Paxos", "leader", "quorum", "log replication", "split-brain")
            ),
            new Question(
                "swe_tech_sr_5",
                "What is the difference between Synchronous blocking I/O, Asynchronous non-blocking I/O, and Multiplexed I/O?",
                "Discuss select/poll/epoll, socket descriptors, thread context switching, and resource utilization.",
                "Blocking I/O keeps a thread waiting for data. Asynchronous I/O delegates system execution and notifies. Multiplexed I/O uses select/epoll to check multiple sockets on a single thread.",
                Arrays.asList("I/O", "blocking", "asynchronous", "epoll", "thread", "multiplexing", "nio")
            )
        ));
        softwareengineerTypes.put("technical", softwareengineerTechnical);

        // Focus Category: system-design
        Map<String, List<Question>> softwareengineerSystemDesign = new HashMap<>();
        softwareengineerSystemDesign.put("junior", Arrays.asList(
            new Question(
                "swe_sys_jr_1",
                "How would you design a basic URL shortening service (like Bitly)? What are the main components?",
                "Discuss the database schema (long URL, short hash), redirection logic, and caching of popular links.",
                "I would use a database mapping short codes to long URLs. The service generates a base62 hash from an ID. When a request comes in, a web server checks Redis, then redirects with 301/302.",
                Arrays.asList("redirection", "hash", "base62", "cache", "redis", "key-value", "database", "short url", "unique id")
            ),
            new Question(
                "swe_sys_jr_2",
                "What is a Content Delivery Network (CDN), and how does it optimize media loading for global users?",
                "Discuss Edge servers, geolocation, caching TTLs, latency, and origin shield.",
                "A CDN caches static files on edge servers geographically close to users. When requested, media is loaded from the nearest edge, reducing latency and origin server load.",
                Arrays.asList("CDN", "edge server", "latency", "caching", "geolocation", "origin server", "TTL")
            ),
            new Question(
                "swe_sys_jr_3",
                "How do you scale a single database instance horizontally? What are sharding and replication?",
                "Discuss primary-secondary nodes, read scaling, sharding keys, partition rules, and replica lag.",
                "We use replication to clone data to secondary nodes for read scaling, and sharding to partition rows across distinct servers using a shard key for write scaling.",
                Arrays.asList("sharding", "replication", "horizontal scaling", "primary-secondary", "partition", "shard key")
            ),
            new Question(
                "swe_sys_jr_4",
                "Explain the role and trade-offs of using an In-Memory Cache (like Redis or Memcached) in system design.",
                "Discuss eviction policies (LRU), cache invalidation, cache stampede, and cache aside patterns.",
                "In-memory caching stores hot queries for fast O(1) lookups. Trade-offs include managing cache invalidation and database synchronization.",
                Arrays.asList("Redis", "caching", "LRU", "cache aside", "eviction", "invalidation")
            ),
            new Question(
                "swe_sys_jr_5",
                "What is a Message Queue, and why is it used to decouple microservice communications?",
                "Discuss async workers, buffer traffic, peak load handling, and broker pub-sub models.",
                "Message queues publish events asynchronously, decoupling services. The sending service returns immediately while workers process the queue downstream, smoothing peak traffic.",
                Arrays.asList("message queue", "decoupling", "async", "pub-sub", "buffer", "rabbitMQ", "kafka")
            )
        ));
        softwareengineerSystemDesign.put("mid", Arrays.asList(
            new Question(
                "swe_sys_mid_1",
                "Design a rate limiter for an API. What algorithm would you choose, where would you store the state, and how does it scale?",
                "Discuss algorithms (Token Bucket, Sliding Window), state in Redis, and handling concurrency race conditions.",
                "I would use the Token Bucket algorithm with state stored in Redis. To handle concurrent requests, I would run Redis Lua scripts to execute operations atomically, returning a 429 if exceeded.",
                Arrays.asList("token bucket", "sliding window", "redis", "lua script", "concurrency", "distributed", "rate limit", "HTTP 429", "headers")
            ),
            new Question(
                "swe_sys_mid_2",
                "How would you design a distributed web crawler that operates at scale? Address system bottlenecks.",
                "Discuss URL frontier, politeness policies, duplication filters (Bloom filters), and parsing queues.",
                "I would use a distributed URL frontier queue. Politeness rules restrict calls per domain, and Bloom filters check for duplicate pages before parsing.",
                Arrays.asList("crawler", "frontier", "politeness", "Bloom filter", "queues", "IP address", "domain")
            ),
            new Question(
                "swe_sys_mid_3",
                "Design a metric monitoring and alerting system for a cluster of servers (like Prometheus/Grafana).",
                "Discuss pull vs push models, time-series databases (TSDB), aggregation, and alerting rules.",
                "The system pulls metrics periodically from server agents, writing them to a time-series database. An evaluation engine runs rules to fire alerts to Slack/PagerDuty.",
                Arrays.asList("monitoring", "TSDB", "alerting", "pull model", "prometheus", "timeseries", "metrics")
            ),
            new Question(
                "swe_sys_mid_4",
                "Design a collaborative document editing tool (like Google Docs). How do you resolve merge conflicts?",
                "Discuss Operational Transformation (OT) vs Conflict-free Replicated Data Types (CRDT).",
                "I would use Operational Transformation (OT) over WebSockets. The server acts as a central sequencer, transforming offset positions to align concurrent keystrokes.",
                Arrays.asList("collaboration", "Operational Transformation", "OT", "CRDT", "websockets", "concurrency")
            ),
            new Question(
                "swe_sys_mid_5",
                "Design a scale-out notification service capable of sending millions of emails, SMS, and push notifications daily.",
                "Discuss priority queues, third-party provider integrations, rate limiting, and status callbacks.",
                "Requests enter a broker partition. Priority workers read events, call provider APIs (Twilio/SES), track delivery callbacks, and handle failures using dead-letter queues.",
                Arrays.asList("notification", "priority queue", "twilio", "APNS", "callback", "idempotency")
            )
        ));
        softwareengineerSystemDesign.put("senior", Arrays.asList(
            new Question(
                "swe_sys_sr_1",
                "Design a system like Ticketmaster that can handle massive traffic spikes for popular event sales without double booking.",
                "Address concurrency, database isolation level, seat reservation lifecycle, caching, and queues.",
                "I would place a virtual queue in front of the purchase flow to throttle requests. Seat maps are cached in Redis. When a seat is selected, a Redis lock reserves it. The purchase runs with DB row locks.",
                Arrays.asList("virtual queue", "pessimistic locking", "transaction isolation", "redis", "buffer", "concurrency control", "idempotent", "seat lock")
            ),
            new Question(
                "swe_sys_sr_2",
                "Design a distributed video streaming service (like Netflix) at a global scale. How is content delivered and catalog searched?",
                "Discuss video transcoding pipelines, regional CDNs, search indexing with Elasticsearch, and client caching.",
                "Uploaded videos are split into chunks and transcoded into multiple formats. Chunks are cached globally on edge CDNs. Searching is index-routed through Elasticsearch nodes.",
                Arrays.asList("transcoding", "Elasticsearch", "CDN", "global scale", "chunking", "manifest", "availability")
            ),
            new Question(
                "swe_sys_sr_3",
                "Design a distributed storage system (like Amazon S3). How do you ensure high availability, data durability, and consistency?",
                "Discuss replication factor, erasure coding, metadata storage, hashing rings, and gossip protocols.",
                "Files are split into chunks. We use erasure coding to distribute parity chunks across storage units. Metadata is stored in a partitioned, replicated key-value ring.",
                Arrays.asList("durability", "erasure coding", "consistent hashing", "S3", "replication", "metadata", "Gossip")
            ),
            new Question(
                "swe_sys_sr_4",
                "Design a global ride-hailing service (like Uber). How do you handle geospatial indexing and matching algorithms under high write loads?",
                "Discuss Geo-hashing (H3, S2), WebSockets for location updates, and distributed write-ahead caches.",
                "Drivers stream locations via WebSockets. We index locations using S2/H3 geohashes in Redis. A dispatch match service queries local cells to pair riders with drivers.",
                Arrays.asList("geospatial", "H3", "S2", "geohash", "WebSocket", "dispatch", "realtime")
            ),
            new Question(
                "swe_sys_sr_5",
                "Design a feed generation system (like Twitter or Facebook Feed) supporting millions of users. What are push vs pull models?",
                "Discuss timeline caches, home timelines, user timelines, fan-out strategy, and celebrity query policies.",
                "We use a hybrid model. Standard posts fan out (push) to active followers' timeline caches in Redis. Celebrity posts are pulled at read-time to prevent write-amplification spikes.",
                Arrays.asList("feed", "fan-out", "timeline", "push model", "pull model", "redis cache", "hybrid")
            )
        ));
        softwareengineerTypes.put("system-design", softwareengineerSystemDesign);

        questionBank.put("software-engineer", softwareengineerTypes);

        // --- PRODUCT-MANAGER ---
        Map<String, Map<String, List<Question>>> productmanagerTypes = new HashMap<>();

        // Focus Category: behavioral
        Map<String, List<Question>> productmanagerBehavioral = new HashMap<>();
        productmanagerBehavioral.put("junior", Arrays.asList(
            new Question(
                "pm_beh_jr_1",
                "Tell me about a product you use daily. What makes it great, and what is one feature you would add or improve?",
                "Structure with: User persona, value proposition, pain point, feature idea, and success metrics.",
                "I use Spotify daily because of recommendations. To improve it, I would add a local event mapper to notify users when artists they listen to are touring near them, measured by ticket link clicks.",
                Arrays.asList("user pain", "value proposition", "feature improvement", "UX", "engagement", "metrics", "target audience")
            ),
            new Question(
                "pm_beh_jr_2",
                "Tell me about a time you had to coordinate between engineering and design. How did you align them?",
                "Focus on trade-offs, scope definitions, and shared alignment meetings.",
                "I held three-way design reviews, aligning designers on technical limitations and developers on user experience goals to reach compromise.",
                Arrays.asList("coordinate", "align", "engineering", "design", "trade-offs")
            ),
            new Question(
                "pm_beh_jr_3",
                "How do you gather and prioritize user feedback when working on a new product interface?",
                "Discuss interviews, surveys, telemetry, and priority frameworks like MoSCoW.",
                "I run usability tests and gather telemetry. I organize findings, categorize them using the MoSCoW framework, and prioritize high-pain adjustments first.",
                Arrays.asList("feedback", "interviews", "telemetry", "prioritize", "MoSCoW", "usability")
            ),
            new Question(
                "pm_beh_jr_4",
                "Describe a scenario where your product launch met with unexpected negative feedback. How did you react?",
                "Show responsiveness, data-gathering methods, and incremental mitigation.",
                "After launching a checkout shortcut, users complained of accidental clicks. I analyzed drop-off metrics, confirmed the issue, and added a quick confirmation step.",
                Arrays.asList("feedback", "failure", "mitigation", "data", "iteration", "listen")
            ),
            new Question(
                "pm_beh_jr_5",
                "How do you explain the value of a technical refactoring task to non-technical business stakeholders?",
                "Connect technical health to business velocity, page load times, conversion rates, or maintenance costs.",
                "I translate the refactoring of our load queries into business metrics: 'reducing load times by 1 second boosts checkout conversion by 2% and increases sales by $30k.'",
                Arrays.asList("translation", "tech debt", "business value", "conversion", "velocity", "ROI")
            )
        ));
        productmanagerBehavioral.put("mid", Arrays.asList(
            new Question(
                "pm_beh_mid_1",
                "How do you align cross-functional teams (engineering, design, sales) when launching a controversial new feature?",
                "Highlight empathy, sharing user data, transparent alignment meetings, and defining clear shared launch goals.",
                "I build alignment by sharing raw customer research and telemetry data first. I draft a detailed PRD, run collaborative walkthroughs, and establish a shared dashboard of post-launch metrics.",
                Arrays.asList("cross-functional", "alignment", "buy-in", "user data", "PRD", "shared vision", "compromise", "comms")
            ),
            new Question(
                "pm_beh_mid_2",
                "Tell me about a time you had to define MVP scope. What trade-offs did you make to hit a critical launch window?",
                "Discuss core user journeys, essential features vs nice-to-haves, risk management, and phase-2 planning.",
                "We had to launch a loyalty feature in 4 weeks. I cut custom dashboard widgets, focused strictly on barcode scanning and point registration, and scheduled widgets for Phase 2.",
                Arrays.asList("MVP", "scope", "trade-off", "timeline", "core journey", "phase 2", "risk")
            ),
            new Question(
                "pm_beh_mid_3",
                "How do you handle a situation where engineering tells you a committed feature cannot be delivered on schedule?",
                "Focus on root cause analysis, transparent stakeholder management, and scope negotiation.",
                "I meet with engineering to isolate the technical blocker. We review scope trade-offs to see if a lighter version can ship, or prepare a transparent delay note for stakeholders.",
                Arrays.asList("delay", "reschedule", "negotiation", "scope adjustment", "transparency", "replan")
            ),
            new Question(
                "pm_beh_mid_4",
                "How do you decide when to kill a feature that is underperforming relative to initial success goals?",
                "Discuss evaluation periods, user metrics, support overhead, and opportunity cost of iteration.",
                "I monitor feature metrics for 3 months. If adoption remains low while support calls are high, I present a deprecation proposal focusing on the developer capacity saved.",
                Arrays.asList("deprecation", "feature health", "metrics", "opportunity cost", "telemetry", "feedback")
            ),
            new Question(
                "pm_beh_mid_5",
                "Describe how you build a relationship of trust and collaboration with your engineering lead.",
                "Discuss respect for technical constraints, involving them in product planning, and clear boundaries.",
                "I share customer insights early, involve the lead in PRD drafting, and respect their ownership of technical execution while I own product goals.",
                Arrays.asList("trust", "collaboration", "lead", "boundaries", "technical constraints", "shared planning")
            )
        ));
        productmanagerBehavioral.put("senior", Arrays.asList(
            new Question(
                "pm_beh_sr_1",
                "Describe a time you had to make a pivot in product strategy. How did you identify the need, and how did you guide the transition?",
                "Address market conditions, strategic analysis, communicating the pivot to executive leadership, and managing team morale.",
                "Our B2C SaaS growth plateaued, but enterprise requests surged. I analyzed user expansion rates, built a business case showing higher LTV, secured executive approval, and transitioned the roadmap to enterprise features.",
                Arrays.asList("pivot", "strategy", "market research", "executive buy-in", "roadmap", "ROI", "opportunity cost", "vision")
            ),
            new Question(
                "pm_beh_sr_2",
                "How do you formulate a 3-year product vision and align company leaders around a long-term strategic roadmap?",
                "Discuss market trends, macro economics, customer research, executive alignment workshops, and setting OKRs.",
                "I synthesize market research and customer telemetry. I run alignment workshops with executives to establish a core vision, then backport that into yearly OKRs and dynamic roadmaps.",
                Arrays.asList("3-year vision", "strategic roadmap", "executive alignment", "market trends", "OKRs", "planning")
            ),
            new Question(
                "pm_beh_sr_3",
                "Describe a high-stakes conflict between Sales, Product, and Engineering regarding a custom feature request from a key client.",
                "Discuss short-term revenue vs long-term platform health, negotiation frameworks, and standardizing features.",
                "Sales requested a custom integration to close a major account. I negotiated a compromise: instead of custom code, we built a public API that the client could use, standardizing our platform.",
                Arrays.asList("conflict", "sales", "custom requests", "tech debt", "standardization", "negotiation")
            ),
            new Question(
                "pm_beh_sr_4",
                "How do you foster a product culture that values innovation, experimental testing, and data-driven learning?",
                "Discuss A/B testing infrastructure, post-mortems, celebrating failures, and user-centric reviews.",
                "I set up A/B testing budgets, allocate sprint slots for experimental features, and host blameless post-mortems to share learnings from failed tests.",
                Arrays.asList("innovation", "culture", "experimentation", "A/B test", "learning", "data-driven")
            ),
            new Question(
                "pm_beh_sr_5",
                "Describe how you managed a major product crisis (e.g. data breach, massive downtime) that impacted your customer base.",
                "Focus on crisis communication, working with legal/PR/eng, and building trust back post-crisis.",
                "When a security loop leaked user IDs, I coordinated with PR and engineering leads. We patched the gap in 3 hours and published a transparent post-mortem detailing remediation steps.",
                Arrays.asList("crisis", "communication", "reputation", "security", "downtime", "transparency", "alignment")
            )
        ));
        productmanagerTypes.put("behavioral", productmanagerBehavioral);

        // Focus Category: technical
        Map<String, List<Question>> productmanagerTechnical = new HashMap<>();
        productmanagerTechnical.put("junior", Arrays.asList(
            new Question(
                "pm_tech_jr_1",
                "What is an API, and how would you explain its purpose and value to a non-technical marketing teammate?",
                "Use analogies and explain how APIs enable integration and save development time.",
                "An API is like a waiter who takes your order to the kitchen, and returns with your food. It lets different applications talk to each other without knowing how they are built.",
                Arrays.asList("API", "integration", "analogy", "request", "response", "data exchange", "modular", "efficiency")
            ),
            new Question(
                "pm_tech_jr_2",
                "Explain the difference between client-side and server-side execution to a non-technical designer.",
                "Compare operations run in the browser (CSS/JS animations) with database lookups and security on the server.",
                "Client-side is what runs in the user's browser, like rendering buttons or menus. Server-side runs on remote computers, doing heavy lifting like database queries and processing transactions.",
                Arrays.asList("client-side", "server-side", "browser", "database", "security", "responsiveness")
            ),
            new Question(
                "pm_tech_jr_3",
                "What is database normalization, and why does it matter when designing a database structure?",
                "Mention redundancy reduction, tables relationships, and data consistency safeguards.",
                "Normalization is the process of organizing database tables to reduce redundancy and prevent errors, splitting data into related tables using keys to ensure consistency.",
                Arrays.asList("database", "normalization", "redundancy", "consistency", "relation", "foreign keys")
            ),
            new Question(
                "pm_tech_jr_4",
                "What are cookies and local storage? How do they help personalize a user's web session?",
                "Discuss state management, session tokens, security, and storage limits.",
                "Cookies and local storage save user data directly in their browser. Cookies are sent with server requests to verify logins; local storage holds client-side UI configurations.",
                Arrays.asList("cookie", "local storage", "state", "session", "browser data", "personalization")
            ),
            new Question(
                "pm_tech_jr_5",
                "What is a secure connection (HTTPS)? Why is SSL/TLS important for e-commerce transactions?",
                "Discuss encryption, data protection, man-in-the-middle attacks, and checkout safety.",
                "HTTPS encrypts the data flowing between the user's browser and our server. SSL/TLS certificates prevent hackers from intercepting passwords or credit card numbers during checkout.",
                Arrays.asList("HTTPS", "SSL", "TLS", "encryption", "security", "checkout", "trust")
            )
        ));
        productmanagerTechnical.put("mid", Arrays.asList(
            new Question(
                "pm_tech_mid_1",
                "What is the difference between SQL and NoSQL databases? When would you recommend a NoSQL approach?",
                "Compare schema flexibility, scalability (vertical vs horizontal), and transaction ACID guarantees.",
                "SQL is relational, using strict tables and schemas with strong ACID guarantees, ideal for transactions. NoSQL is schema-less, document-oriented, and scales horizontally, ideal for unstructured data feeds.",
                Arrays.asList("SQL", "NoSQL", "relational", "schema", "scaling", "horizontal", "ACID")
            ),
            new Question(
                "pm_tech_mid_2",
                "What is caching? How does caching at different layers (CDN, Redis, Browser) improve application speed?",
                "Discuss cache-hit ratios, latency, resource savings, and cache invalidation policies.",
                "Caching stores copies of data in fast memory. CDN caches static files near users, Browser caches locally, and Redis caches database queries, avoiding database hits.",
                Arrays.asList("cache", "CDN", "Redis", "latency", "refresh", "invalidation", "database load")
            ),
            new Question(
                "pm_tech_mid_3",
                "What is continuous integration and continuous deployment (CI/CD)? How does it affect launch velocities?",
                "Discuss automated pipelines, testing, linting, deployment staging, rollbacks, and developer cycles.",
                "CI/CD automates compiling, testing, and deploying code. When developers push commits, pipelines run tests and deploy to staging/production, boosting velocity.",
                Arrays.asList("CI/CD", "pipeline", "automation", "tests", "release velocity", "rollback")
            ),
            new Question(
                "pm_tech_mid_4",
                "Explain the difference between REST and GraphQL APIs. When would you prefer GraphQL?",
                "Compare over-fetching/under-fetching data, schema queries, endpoints count, and API versioning.",
                "REST has fixed endpoints returning predefined objects. GraphQL has a single endpoint letting clients request exactly the fields they need, reducing payload size.",
                Arrays.asList("REST", "GraphQL", "endpoints", "query schema", "payload size", "fetch efficiency")
            ),
            new Question(
                "pm_tech_mid_5",
                "What is server-side rendering (SSR) vs client-side rendering (CSR)? How do they impact SEO and page load performance?",
                "Discuss search engine indexing, Time to Interactive (TTI), and initial HTML payloads.",
                "SSR compiles the webpage on the server, sending complete HTML to the browser for faster initial indexing and SEO. CSR loads basic HTML and lets JS render it in the browser.",
                Arrays.asList("SSR", "CSR", "SEO", "indexing", "TTI", "load performance", "payload")
            )
        ));
        productmanagerTechnical.put("senior", Arrays.asList(
            new Question(
                "pm_tech_sr_1",
                "How do you evaluate the technical trade-offs of microservices vs monoliths? When is the right time to transition?",
                "Address team scaling, release dependencies, operations complexity, and database partitioning.",
                "Monoliths are easier to build and deploy but slow down large teams. Microservices decouple code bases for independent releases but add networking and operational complexity.",
                Arrays.asList("monolith", "microservices", "complexity", "scaling", "decoupling", "release train")
            ),
            new Question(
                "pm_tech_sr_2",
                "Explain the CAP Theorem and how it influences product choices for distributed transactional data systems vs global content caches.",
                "Detail Consistency vs Availability, write failure rates, transaction integrity, and partition states.",
                "CAP states you can only guarantee two out of Consistency, Availability, and Partition tolerance. Financial transactions choose CP (Consistency) to prevent double-spending; feeds prefer AP (Availability).",
                Arrays.asList("CAP", "consistency", "availability", "partition tolerance", "replication", "write failure")
            ),
            new Question(
                "pm_tech_sr_3",
                "How do you assess the ROI of migrating our core infrastructure to a cloud serverless architecture (e.g. AWS Lambda)?",
                "Discuss cold starts, compute costs, maintenance overhead, vendor lock-in, and unpredictable load spikes.",
                "Serverless cuts operational maintenance and scales automatically. However, high-volume consistent traffic can be more expensive than dedicated servers due to cold starts.",
                Arrays.asList("serverless", "cold start", "compute cost", "maintenance", "AWS Lambda", "ROI", "lock-in")
            ),
            new Question(
                "pm_tech_sr_4",
                "Explain how OAuth 2.0 and Single Sign-On (SSO) authentication work at a high level. How do tokens keep user sessions secure?",
                "Discuss Authorization Servers, Access Tokens, JWTs, scopes, redirects, and session revocation.",
                "SSO authenticates user credentials on a central server. The server issues a signed cryptographically secure Access Token (JWT) that the client passes to microservices.",
                Arrays.asList("OAuth", "SSO", "Access Token", "JWT", "identity provider", "redirect", "security")
            ),
            new Question(
                "pm_tech_sr_5",
                "What are web sockets, polling, and long-polling? How do you select the correct technology for real-time notification architectures?",
                "Discuss connection overhead, server resources, network latency, and duplex streaming.",
                "Polling repeatedly checks for updates, wasting resources. WebSockets open a persistent, bi-directional TCP connection, ideal for low-latency, active communication systems.",
                Arrays.asList("WebSocket", "polling", "long-polling", "real-time", "duplex", "connection overhead")
            )
        ));
        productmanagerTypes.put("technical", productmanagerTechnical);

        // Focus Category: system-design
        Map<String, List<Question>> productmanagerSystemDesign = new HashMap<>();
        productmanagerSystemDesign.put("junior", Arrays.asList(
            new Question(
                "pm_sys_jr_1",
                "Explain the concept of 'microservices' and how it differs from a 'monolithic' application from a product manager's perspective.",
                "Focus on release velocity, risk isolation, team ownership, and maintenance complexity.",
                "A monolith is one giant codebase; if one part breaks, the whole app might crash. Microservices break the app into independent services, allowing faster deployments and isolated failures.",
                Arrays.asList("monolith", "microservice", "decoupling", "deployment velocity", "fault isolation", "independent scaling")
            ),
            new Question(
                "pm_sys_jr_2",
                "What is load balancing? How does it help scale web applications to handle traffic surges?",
                "Explain distribution of traffic, health checks, and traffic redirection.",
                "A load balancer distributes requests across multiple servers. If one server crashes, the balancer routes traffic to healthy servers, preventing downtime.",
                Arrays.asList("load balancer", "scaling", "traffic", "redundancy", "availability", "health check")
            ),
            new Question(
                "pm_sys_jr_3",
                "What is database sharding? How does it solve scaling limits for write-heavy applications?",
                "Discuss horizontal partitioning, shard keys, database size constraints, and latency.",
                "Sharding splits a large database table horizontally across multiple physical servers using a shard key, so write queries are distributed instead of overloading one disk.",
                Arrays.asList("sharding", "database", "write load", "partitioning", "shard key", "horizontal scaling")
            ),
            new Question(
                "pm_sys_jr_4",
                "What is a Content Delivery Network (CDN)? How does it optimize the delivery of product images and videos?",
                "Discuss geographic location, latency, edge servers, and origin servers.",
                "CDNs copy media files to edge servers around the world. When a user opens the app, the media loads from the nearest edge server, reducing loading latency.",
                Arrays.asList("CDN", "edge cache", "latency", "media distribution", "load speed", "origin")
            ),
            new Question(
                "pm_sys_jr_5",
                "Explain the difference between a cache and a database. When should we cache data?",
                "Compare speed (RAM vs Disk), data durability, and caching hot queries.",
                "A database stores persistent data on disk. A cache stores frequently accessed data in fast RAM (e.g. Redis). We cache static profiles to avoid slow DB queries.",
                Arrays.asList("caching", "in-memory", "Redis", "latency", "durability", "hot data")
            )
        ));
        productmanagerSystemDesign.put("mid", Arrays.asList(
            new Question(
                "pm_sys_mid_1",
                "How would you design a simple file upload and storage system (like Dropbox)? What storage layers would you choose?",
                "Compare Object Storage (S3), Relational Databases (Metadata), CDN caching, and asynchronous workers.",
                "I would use Object Storage (AWS S3) for files, a SQL database for file paths and metadata, and an asynchronous queue to trigger file compression.",
                Arrays.asList("object storage", "metadata", "async queue", "CDN", "upload pipeline", "chunking")
            ),
            new Question(
                "pm_sys_mid_2",
                "Design an API Gateway for a suite of mobile apps. What functions (auth, routing, logging) should it handle?",
                "Discuss reverse proxy, token validation, rate limiting, and request routing.",
                "An API Gateway acts as a reverse proxy. It validates access tokens, checks rate limits, logs requests, and routes calls to appropriate microservices.",
                Arrays.asList("API gateway", "routing", "rate limiting", "authorization", "reverse proxy", "logging")
            ),
            new Question(
                "pm_sys_mid_3",
                "Design a system to track real-time delivery locations for a logistics company. What messaging protocols would you use?",
                "Compare WebSockets, HTTP polling, publish-subscribe brokers, and write-heavy database scaling.",
                "Devices stream GPS points via WebSockets to a lightweight ingestion service. Points are queued in Kafka, cached in a Redis geohash, and written in batches to database storage.",
                Arrays.asList("realtime", "WebSocket", "write load", "Redis geohash", "GPS tracking", "broker")
            ),
            new Question(
                "pm_sys_mid_4",
                "Design a scale-out search engine for an e-commerce catalog. How do you ensure search updates are fast and relevant?",
                "Discuss search indexing, Elasticsearch, database replication, and query autocomplete caching.",
                "Catalog data is indexed in Elasticsearch. Write updates to the DB trigger asynchronous index syncs. Autocomplete queries are cached in Redis to minimize search engine queries.",
                Arrays.asList("Elasticsearch", "search index", "replication", "autocomplete", "query routing", "relevance")
            ),
            new Question(
                "pm_sys_mid_5",
                "Design a distributed job scheduler to run millions of delayed tasks (like sending promotional emails).",
                "Discuss task persistence, scheduling queues, worker pulling models, and handling double execution.",
                "Jobs are stored in database tables and indexed in a Redis sorted set by execute time. Workers poll Redis for ready jobs, process tasks, and use lock flags to avoid double running.",
                Arrays.asList("job scheduler", "delay queue", "workers", "idempotency", "redis zset", "broker")
            )
        ));
        productmanagerSystemDesign.put("senior", Arrays.asList(
            new Question(
                "pm_sys_sr_1",
                "Design a high-volume payment processing system (like Stripe) supporting third-party checkouts. How do you guarantee transaction idempotency?",
                "Discuss double charging, idempotency keys, ACID transactions, message brokers, and audit logs.",
                "Clients generate a unique idempotency key for each request. The payment service records keys in a database within an ACID transaction, rejecting duplicates to prevent double charging.",
                Arrays.asList("payment", "idempotency key", "ACID", "audit log", "double charge", "state machine")
            ),
            new Question(
                "pm_sys_sr_2",
                "Design a globally distributed search index (like Google Search). How do you craw pages, index terms, and serve queries?",
                "Discuss page crawling, inverted indexing, query rank (PageRank), and caching localized results.",
                "Distributed web crawlers stream pages to parsers. Term positions are written to an Inverted Index. Queries are routed to localized clusters, fetching ranking scores from PageRank maps.",
                Arrays.asList("web crawler", "inverted index", "PageRank", "distributed query", "edge cache", "ranking")
            ),
            new Question(
                "pm_sys_sr_3",
                "Design a global content delivery platform (like Netflix). How do you handle file encoding, CDN routing, and client bandwidth adaptation?",
                "Discuss media compression pipelines, Geo-DNS CDN routing, dynamic bitrate streaming, and caching regional assets.",
                "Media files are transcoded into multiple bitrates. Geo-DNS resolves user queries to the nearest CDN edge. The video player dynamically adjusts video resolution based on current bandwidth.",
                Arrays.asList("transcoding", "Geo-DNS", "bitrate streaming", "CDN cache", "bandwidth", "origin shield")
            ),
            new Question(
                "pm_sys_sr_4",
                "Design a distributed metrics and tracing platform (like Jaeger/OpenTelemetry) for a microservices cluster.",
                "Discuss trace context injection, span collection, high-volume ingestion, and timeseries databases.",
                "Services attach trace IDs to request headers. Spans are streamed to collectors, processed using a sampling strategy to filter noise, and written to a Cassandra database.",
                Arrays.asList("tracing", "Jaeger", "opentelemetry", "span", "ingestion", "TSDB", "sampling")
            ),
            new Question(
                "pm_sys_sr_5",
                "Design a localized ride-sharing matching engine (like Uber). How do you solve real-time location streaming and dispatch rules?",
                "Discuss Geospatial indexing (H3/S2), real-time WebSockets, partition mapping, and dispatch queues.",
                "Drivers stream coordinates to WebSocket servers, mapping location hashes in Redis. When a ride is requested, dispatchers query local cell blocks and reserve driver IDs using Redis locks.",
                Arrays.asList("Uber matching", "geospatial", "H3", "WebSocket", "dispatch queue", "location stream", "redis lock")
            )
        ));
        productmanagerTypes.put("system-design", productmanagerSystemDesign);

        questionBank.put("product-manager", productmanagerTypes);

        // --- DATA-ANALYST ---
        Map<String, Map<String, List<Question>>> dataanalystTypes = new HashMap<>();

        // Focus Category: behavioral
        Map<String, List<Question>> dataanalystBehavioral = new HashMap<>();
        dataanalystBehavioral.put("junior", Arrays.asList(
            new Question(
                "da_beh_jr_1",
                "How do you explain a complex statistical finding or data visualization to a non-technical business executive?",
                "Focus on the business outcome, skip complex formulas, and lead with the summary.",
                "I lead with the bottom line: 'Improving checkout conversion by 2% increases sales by $50k.' I present a clean bar chart and explain the recommended next action clearly.",
                Arrays.asList("analogy", "executive summary", "business impact", "translation", "visualization", "context", "actionable")
            ),
            new Question(
                "da_beh_jr_2",
                "Tell me about a time you found an error in an database report or dataset. How did you handle it?",
                "Focus on data lineage, identifying source errors, and communicating corrections.",
                "I noticed an anomaly in a weekly sales dashboard where revenue doubled. I traced the lineage, found a duplicate transaction import script, corrected it, and updated the team.",
                Arrays.asList("data error", "cleaning", "lineage", "communication", "correction", "verification")
            ),
            new Question(
                "da_beh_jr_3",
                "How do you handle a situation where a stakeholder disagrees with the results of your analysis?",
                "Discuss showing raw data, reviewing assumptions together, and staying objective.",
                "I set up a call to review our analysis assumptions. I walked them through the SQL query filters and raw datasets, which clarified the constraints and gained their agreement.",
                Arrays.asList("disagreement", "objectivity", "raw data", "assumptions", "collaboration", "review")
            )
        ));
        dataanalystBehavioral.put("mid", Arrays.asList(
            new Question(
                "da_beh_mid_1",
                "How do you prioritize multiple requests for dashboard creation and analysis from different business divisions?",
                "Address business impact evaluation, effort sizing, and negotiating delivery timelines.",
                "I score requests based on potential revenue impact and development effort. I negotiate timelines with stakeholders, keeping key pipelines visible in our shared roadmap.",
                Arrays.asList("prioritization", "stakeholders", "impact", "negotiation", "scope", "delivery")
            ),
            new Question(
                "da_beh_mid_2",
                "Describe a time when your analysis led to a significant business decision or product change.",
                "Explain the analysis method, findings, presentation format, and measurable post-change outcome.",
                "I analyzed our onboarding funnel drop-offs. I showed that 40% of users left at the profile photo step. We made that step optional, which increased overall conversions by 15%.",
                Arrays.asList("analysis impact", "SQL query", "product pivot", "KPI conversion", "recommendation")
            ),
            new Question(
                "da_beh_mid_3",
                "How do you ensure data quality and integrity when merging multiple raw sources for a report?",
                "Discuss join keys verification, check scripts, deduplication, and handling null values.",
                "I run validation queries checking for duplicates on primary keys, verify row match counts post-join, and write cleanup scripts to handle empty or null values.",
                Arrays.asList("data quality", "deduplication", "join keys", "validation", "null values", "ETL")
            )
        ));
        dataanalystBehavioral.put("senior", Arrays.asList(
            new Question(
                "da_beh_sr_1",
                "How do you establish data governance and dashboard standards across a growing organization?",
                "Discuss documentation, cataloging tools, training business users, and maintaining clean KPIs.",
                "I created a global data catalog and a standard KPI library definition. I hold monthly training sessions for business divisions to promote data alignment.",
                Arrays.asList("governance", "standards", "KPI library", "data catalog", "mentorship", "dashboards")
            ),
            new Question(
                "da_beh_sr_2",
                "Describe a time you had to define and track metrics for a completely new, ambiguous business product.",
                "Focus on user goals, mapping funnel events, defining leading indicators, and aligning leadership.",
                "For our new subscription product, I mapped early engagement events (sessions, clicks) to retention. I built a tracking dashboard that accurately predicted future churn rates.",
                Arrays.asList("ambiguity", "metric design", "funnel events", "leading indicators", "alignment", "dashboard")
            ),
            new Question(
                "da_beh_sr_3",
                "How do you advocate for investments in our data infrastructure (e.g. data warehouse migration) to non-technical leaders?",
                "Highlight developer speed, compute cost savings, data reliability, and reducing report lag.",
                "I built a business case comparing our growing query computing costs vs a modern cloud warehouse, demonstrating a 30% cost saving and 10x faster dashboards.",
                Arrays.asList("advocacy", "warehouse", "infrastructure", "ROI", "latency", "maintenance")
            )
        ));
        dataanalystTypes.put("behavioral", dataanalystBehavioral);

        // Focus Category: technical
        Map<String, List<Question>> dataanalystTechnical = new HashMap<>();
        dataanalystTechnical.put("junior", Arrays.asList(
            new Question(
                "da_tech_jr_1",
                "Explain the difference between a INNER JOIN, LEFT JOIN, and outer join in SQL.",
                "Describe row return behaviors, matching key requirements, and null generation.",
                "INNER JOIN returns only rows with matches in both tables. LEFT JOIN returns all rows from the left table and matched rows from the right; unmatched right values return NULL.",
                Arrays.asList("SQL", "join", "inner join", "left join", "null", "matching keys", "records", "database")
            ),
            new Question(
                "da_tech_jr_2",
                "What is database indexing, and how does it speed up queries? Are there trade-offs?",
                "Discuss fast search paths, read acceleration, and write latency overhead.",
                "An index acts like a book index, letting queries find rows quickly without a full table scan. However, it slows down writes because the index must update.",
                Arrays.asList("database", "index", "queries speed", "read optimization", "write overhead", "lookup")
            ),
            new Question(
                "da_tech_jr_3",
                "What are aggregate functions in SQL? Name four and explain what they do.",
                "Discuss SUM, AVG, COUNT, MIN, MAX and the GROUP BY clause requirement.",
                "Aggregate functions perform calculations on multiple rows, returning a single value. SUM totals values, AVG averages, COUNT counts rows, and GROUP BY groups results.",
                Arrays.asList("SQL", "aggregate", "SUM", "AVG", "COUNT", "GROUP BY", "aggregation")
            )
        ));
        dataanalystTechnical.put("mid", Arrays.asList(
            new Question(
                "da_tech_mid_1",
                "Explain how Window Functions work in SQL (e.g., ROW_NUMBER, RANK, SUM over Partition).",
                "Describe sorting rows within partitions, keeping individual rows, and running totals.",
                "Window functions perform calculations across a set of table rows related to the current row, keeping individual row detail instead of collapsing them like GROUP BY.",
                Arrays.asList("window function", "partition by", "order by", "row_number", "rank", "running total")
            ),
            new Question(
                "da_tech_mid_2",
                "What is the difference between structured data, semi-structured data, and unstructured data?",
                "Compare relational SQL databases (structured) with JSON/XML (semi-structured) and audio/video files (unstructured).",
                "Structured data fits in defined SQL tables. Semi-structured has tags or keys (like JSON/XML) but no strict schema. Unstructured has no pre-defined structure (like audio/video).",
                Arrays.asList("structured", "semi-structured", "unstructured", "SQL table", "JSON", "audio video", "schema")
            ),
            new Question(
                "da_tech_mid_3",
                "What is an ETL (Extract, Transform, Load) pipeline? Explain each step.",
                "Discuss raw data extraction, cleaning and mapping, and writing to the target data warehouse.",
                "ETL extracts raw data from source systems, transforms it by cleaning and aligning schemas, and loads it into a target data warehouse for reporting.",
                Arrays.asList("ETL", "extract", "transform", "load", "pipeline", "data warehouse", "cleaning")
            )
        ));
        dataanalystTechnical.put("senior", Arrays.asList(
            new Question(
                "da_tech_sr_1",
                "How do you optimize slow SQL queries that run against tables with millions of rows?",
                "Discuss EXPLAIN plans, index coverage, subquery refactoring, joins optimization, and partitioning.",
                "I run an EXPLAIN query plan to identify table scans. I add indexes on join/filter keys, refactor nested subqueries into CTEs, and partition tables by date.",
                Arrays.asList("optimize query", "EXPLAIN", "indexing", "partitioning", "joins optimization", "subquery")
            ),
            new Question(
                "da_tech_sr_2",
                "Explain the difference between a Data Warehouse, a Data Lake, and a Lakehouse.",
                "Compare processed structured storage (Warehouse) with raw dump repositories (Lake) and modern combined architectures (Lakehouse).",
                "A Data Warehouse stores processed structured data. A Data Lake stores raw dumps of unstructured/semi-structured data. A Lakehouse combines both, running ACID queries on raw storage.",
                Arrays.asList("data warehouse", "data lake", "lakehouse", "structured", "raw storage", "ACID", "delta lake")
            ),
            new Question(
                "da_tech_sr_3",
                "How does columnar storage (like Parquet or Redshift) differ from row-based storage (like Postgres)? When is it useful?",
                "Discuss scan efficiency, data compression, disk I/O savings, and reporting query profiles.",
                "Row-based storage writes rows sequentially, ideal for transaction updates. Columnar storage writes columns sequentially, letting queries read only the needed columns, ideal for reporting.",
                Arrays.asList("columnar storage", "row storage", "parquet", "redshift", "compression", "scan efficiency")
            )
        ));
        dataanalystTypes.put("technical", dataanalystTechnical);

        // Focus Category: system-design
        Map<String, List<Question>> dataanalystSystemDesign = new HashMap<>();
        dataanalystSystemDesign.put("junior", Arrays.asList(
            new Question(
                "da_sys_jr_1",
                "What is a star schema in data warehousing? What are fact tables and dimension tables?",
                "Define fact tables (measurable data) vs dimension tables (descriptive attributes) and query speed benefits.",
                "A star schema organizes data into a central Fact Table linked by foreign keys to descriptive Dimension Tables. It simplifies queries and speeds up analysis.",
                Arrays.asList("star schema", "fact table", "dimension table", "foreign key", "joins", "warehouse", "denormalized")
            ),
            new Question(
                "da_sys_jr_2",
                "Explain the difference between Batch Processing and Stream Processing.",
                "Compare processing scheduled bulk data (batch) with real-time continuous events (stream).",
                "Batch processing runs scheduled jobs on bulk historical data (e.g. daily reports). Stream processing evaluates continuous data in real-time as events occur.",
                Arrays.asList("batch processing", "stream processing", "real-time", "hourly daily", "kafka", "hadoop")
            ),
            new Question(
                "da_sys_jr_3",
                "What is a Data Catalog, and why is it important for self-service analysis?",
                "Discuss metadata, data dictionary, discoverability, and data lineage.",
                "A data catalog is a directory of metadata. It documents tables, fields, and lineage, letting analysts discover and understand datasets easily.",
                Arrays.asList("data catalog", "metadata", "dictionary", "discovery", "lineage", "documentation")
            )
        ));
        dataanalystSystemDesign.put("mid", Arrays.asList(
            new Question(
                "da_sys_mid_1",
                "Design a dashboard caching strategy to optimize load times for hundreds of concurrent users.",
                "Discuss query caching, pre-aggregation tables, Redis cache layer, and dashboard refresh schedules.",
                "I would pre-aggregate report metrics into dedicated summary tables hourly. Dashboard loads query these summary tables directly, caching queries in Redis.",
                Arrays.asList("dashboard cache", "pre-aggregation", "Redis", "query time", "concurrency", "schedule")
            ),
            new Question(
                "da_sys_mid_2",
                "Design a basic data ingestion system to load clickstream logs from web servers into a data warehouse.",
                "Discuss log collector agents, message queue staging (Kafka), transformation tools, and load scripts.",
                "Collector agents (Fluentd) tail web logs, sending events to Kafka. A stream consumer cleans the payload formats and writes files to storage, loading them into the warehouse.",
                Arrays.asList("ingestion", "clickstream", "log collector", "Kafka", "transform", "load", "warehouse")
            ),
            new Question(
                "da_sys_mid_3",
                "What is a Snowflake Schema, and how does it compare to a Star Schema?",
                "Compare normalized dimension tables (Snowflake) with denormalized tables (Star) addressing storage and join overhead.",
                "A Snowflake schema normalizes dimension tables into nested hierarchies, saving disk space. A Star schema denormalizes dimensions, reducing join complexity for faster queries.",
                Arrays.asList("snowflake schema", "star schema", "normalization", "joins", "storage efficiency", "complexity")
            )
        ));
        dataanalystSystemDesign.put("senior", Arrays.asList(
            new Question(
                "da_sys_sr_1",
                "Design a real-time analytics pipeline for an e-commerce dashboard showing active visitor counts and live checkouts.",
                "Discuss WebSocket ingestion, stream processing (Flink/Spark Streaming), in-memory cache counts, and push updates.",
                "Web nodes stream checkout events to Kafka. Spark Streaming processes the events in 10-second windows, updating Redis counters that feed the dashboard via WebSockets.",
                Arrays.asList("realtime pipeline", "stream processing", "Flink", "WebSocket", "redis counter", "ingestion")
            ),
            new Question(
                "da_sys_sr_2",
                "Design a data lineage tracking architecture for a complex corporate data warehouse.",
                "Discuss metadata extraction, SQL parsing, dependency graphs, and lineage visualizations.",
                "We extract execution logs from SQL engines and parse query inputs and outputs. We map these relationships into a graph database to visualize data lineage.",
                Arrays.asList("data lineage", "metadata extraction", "dependency graph", "SQL parsing", "catalog")
            ),
            new Question(
                "da_sys_sr_3",
                "How would you design a distributed query engine (like Presto/Athena) that can run SQL queries across separate databases?",
                "Discuss split queries, coordinator nodes, worker nodes, parallel execution, and connector interfaces.",
                "A coordinator node receives queries, builds a logical plan, and splits tasks. Worker nodes execute sub-queries in parallel across connectors, streaming results back.",
                Arrays.asList("query engine", "Presto", "Athena", "coordinator", "worker", "parallel execution", "connector")
            )
        ));
        dataanalystTypes.put("system-design", dataanalystSystemDesign);

        questionBank.put("data-analyst", dataanalystTypes);

        // --- UX-DESIGNER ---
        Map<String, Map<String, List<Question>>> uxdesignerTypes = new HashMap<>();

        // Focus Category: behavioral
        Map<String, List<Question>> uxdesignerBehavioral = new HashMap<>();
        uxdesignerBehavioral.put("junior", Arrays.asList(
            new Question(
                "ux_beh_jr_1",
                "Walk me through your design process when working on a new feature. How do you start?",
                "Mention steps like: user research, wireframes, user testing, high-fidelity prototypes, and design handoffs.",
                "I start with empathy: interviewing users and looking at data. I create low-fidelity wireframes to iterate on layout quickly, run user tests, build a high-fidelity Figma prototype, and then handoff.",
                Arrays.asList("design process", "user research", "wireframe", "prototype", "testing", "empathy", "ideation", "handoff")
            ),
            new Question(
                "ux_beh_jr_2",
                "How do you handle receiving feedback from product managers that conflicts with your user research findings?",
                "Focus on data sharing, collaborative prototype testing, and aligning user goals with business needs.",
                "I present our usability test video clips and user research data. We discuss the business needs, align, and test a compromise prototype together.",
                Arrays.asList("feedback", "user research", "product manager", "compromise", "data sharing", "usability")
            ),
            new Question(
                "ux_beh_jr_3",
                "Describe a time you had to design a feature with very limited time or design constraints. What did you prioritize?",
                "Discuss prioritizing core user flows, utilizing existing design system libraries, and keeping layouts simple.",
                "With only 3 days, I utilized our existing Figma design system components and focused entirely on the core checkout flow, postponing custom configurations.",
                Arrays.asList("constraints", "priority", "design system", "MVP flow", "simplicity", "speed")
            )
        ));
        uxdesignerBehavioral.put("mid", Arrays.asList(
            new Question(
                "ux_beh_mid_1",
                "How do you collaborate with engineering leads to ensure your design prototypes are technically feasible?",
                "Discuss early handoff reviews, developer inputs on components constraints, and design system sharing.",
                "I involve developers early in my ideation phase. We review draft wireframes in Figma to verify component feasibility and load-time constraints before finalizing designs.",
                Arrays.asList("developer handoff", "feasibility", "collaboration", "figma tokens", "components constraints")
            ),
            new Question(
                "ux_beh_mid_2",
                "Describe a time you ran a user testing session that disproved your initial design assumptions. How did you iterate?",
                "Focus on remaining objective, analyzing user friction, and redesigning the flow based on telemetry.",
                "Users struggled to find our search filters, expecting them at the top. I recorded this friction, moved the filters to the header, and verified the fix in our next test.",
                Arrays.asList("user testing", "friction", "iteration", "redesign", "assumptions", "objective")
            ),
            new Question(
                "ux_beh_mid_3",
                "How do you maintain consistency across a large application? What is your experience with design systems?",
                "Discuss Figma component libraries, token usage, design documentation, and design-code alignment.",
                "I contribute to our shared design system. I maintain reusable Figma libraries with matching CSS tokens, making sure design changes are updated globally.",
                Arrays.asList("consistency", "design system", "Figma library", "tokens", "reusable", "documentation")
            )
        ));
        uxdesignerBehavioral.put("senior", Arrays.asList(
            new Question(
                "ux_beh_sr_1",
                "How do you advocate for UX research budgets and design system investments to executive stakeholders?",
                "Connect UX investments to conversion uplift, developer handoff savings, support call reductions, and brand loyalty.",
                "I present the business case: showing how a unified design system cuts frontend development time by 30% and how our UX redesign boosted conversion by 12%.",
                Arrays.asList("budget advocacy", "ROI", "conversion uplift", "handoff efficiency", "support costs", "stakeholders")
            ),
            new Question(
                "ux_beh_sr_2",
                "How do you lead design strategy for complex, multi-platform product portfolios (web, iOS, Android)?",
                "Discuss native platform patterns, responsive layouts, consistent brand identity, and cross-platform design guidelines.",
                "I define cross-platform design principles in our design system. We align basic layouts and brand components while respecting native iOS and Android patterns.",
                Arrays.asList("strategy", "cross-platform", "native guidelines", "responsive", "identity", "alignment")
            ),
            new Question(
                "ux_beh_sr_3",
                "Describe a major product redesign project you led. How did you manage risk, roll out changes incrementally, and track KPIs?",
                "Discuss legacy audits, phased rollouts, beta feedback channels, and tracking conversion/drop-off metrics.",
                "I audited our checkout flows, launched a beta design to 10% of users, gathered friction metrics, iterated on feedback, and completed a phased rollout.",
                Arrays.asList("redesign", "risk management", "phased rollout", "beta tests", "KPI tracking", "metrics")
            )
        ));
        uxdesignerTypes.put("behavioral", uxdesignerBehavioral);

        // Focus Category: technical
        Map<String, List<Question>> uxdesignerTechnical = new HashMap<>();
        uxdesignerTechnical.put("junior", Arrays.asList(
            new Question(
                "ux_tech_jr_1",
                "What is the difference between UI and UX, and how do they work together?",
                "UI is the layout, styling, and visual elements. UX is the flow, logic, speed, and emotional journey.",
                "UI is the visual interface—the colors, buttons, and typography. UX is the user's overall journey, logic, and emotional experience.",
                Arrays.asList("UI", "UX", "visual design", "user flow", "usability", "interaction", "cohesion", "journey")
            ),
            new Question(
                "ux_tech_jr_2",
                "Explain the concept of 'Visual Hierarchy' and list three design methods to achieve it.",
                "Mention contrast, sizing, whitespace alignment, and focus points.",
                "Visual hierarchy guides the user's eye. It is achieved using font sizing contrast, distinct colors, and ample whitespace to group key information.",
                Arrays.asList("hierarchy", "contrast", "whitespace", "fontsize", "focus point", "scale")
            ),
            new Question(
                "ux_tech_jr_3",
                "What are the web accessibility standards (WCAG), and why do they matter for digital interfaces?",
                "Discuss screen readers, color contrast ratios (4.5:1), keyboard navigation, and inclusivity.",
                "WCAG are guidelines that make web content accessible to people with disabilities. We design with a 4.5:1 contrast ratio and ensure keyboard navigation.",
                Arrays.asList("accessibility", "WCAG", "contrast ratio", "keyboard access", "screen readers", "inclusive")
            )
        ));
        uxdesignerTechnical.put("mid", Arrays.asList(
            new Question(
                "ux_tech_mid_1",
                "Explain the differences and use cases for Wireframes, Mockups, and Prototypes.",
                "Compare low-fidelity layout plans (wireframes), high-fidelity layouts (mockups), and interactive layouts (prototypes).",
                "Wireframes are low-fidelity layouts mapping structures. Mockups add visual styles (colors/type). Prototypes add interactions to test user flows.",
                Arrays.asList("wireframe", "mockup", "prototype", "fidelity", "layout", "interaction", "testing")
            ),
            new Question(
                "ux_tech_mid_2",
                "What is a responsive design system? How do grid layouts, breakpoints, and auto-layouts adapt from mobile to desktop?",
                "Discuss flexbox, responsive grids, breakpoints (320px/768px/1200px), and fluid scaling.",
                "A responsive design system uses fluid grid columns and CSS breakpoints to automatically scale layouts dynamically from mobile screens to wide desktops.",
                Arrays.asList("responsive", "grid", "breakpoints", "auto-layout", "scaling", "fluid")
            ),
            new Question(
                "ux_tech_mid_3",
                "What is user journey mapping, and how does it help isolate friction points in an interface flow?",
                "Discuss persona steps, touchpoints, user thoughts/feelings, and pain points mapping.",
                "A journey map traces the step-by-step actions of a persona. By mapping thoughts and feelings at each touchpoint, we isolate drop-off friction points.",
                Arrays.asList("journey mapping", "friction", "persona", "touchpoint", "pain point", "user flow")
            )
        ));
        uxdesignerTechnical.put("senior", Arrays.asList(
            new Question(
                "ux_tech_sr_1",
                "Explain how you design for cognitive loads. What is Hick's Law, and how does it shape data-heavy interfaces?",
                "Hick's Law (decision time vs choices). Discuss progressive disclosure, chunking, and clear calls to action.",
                "Hick's Law states that more choices increase decision latency. We design data-heavy grids with progressive disclosure and clear layout chunking.",
                Arrays.asList("cognitive load", "Hick's Law", "progressive disclosure", "chunking", "data density", "visual noise")
            ),
            new Question(
                "ux_tech_sr_2",
                "How do you evaluate and implement design tokens to build a design-to-code pipeline between Figma and CSS/SASS?",
                "Discuss design tokens (spacing/colors/type), JSON formats, token transformers, and developer sync.",
                "Design tokens are single-source layout values (colors, spacing). We export token values in JSON format and compile them to CSS/SASS variables.",
                Arrays.asList("design tokens", "Figma API", "JSON sync", "variables", "transformer", "sass variables")
            ),
            new Question(
                "ux_tech_sr_3",
                "Explain the role of A/B testing and multivariate testing in conversion rate optimization (CRO). What metrics do you track?",
                "Discuss test hypotheses, sample sizes, statistically significant results, click-through rates, and drop-off charts.",
                "A/B testing validates design hypotheses. We split user traffic across variants, tracking click-through and funnel conversion rates for statistical significance.",
                Arrays.asList("A/B test", "CRO", "significance", "hypotheses", "click-through", "drop-off", "funnel conversion")
            )
        ));
        uxdesignerTypes.put("technical", uxdesignerTechnical);

        // Focus Category: system-design
        Map<String, List<Question>> uxdesignerSystemDesign = new HashMap<>();
        uxdesignerSystemDesign.put("junior", Arrays.asList(
            new Question(
                "ux_sys_jr_1",
                "What is information architecture, and how does it help users navigate a large content site?",
                "Discuss sitemaps, categorization, mental models, navigation hierarchies, and label designs.",
                "Information architecture is the structure of content. It involves organizing pages and navigation in a way that matches users' mental models, verified through card-sorting tests.",
                Arrays.asList("information architecture", "navigation", "sitemap", "categorization", "mental model", "card sorting", "hierarchy")
            ),
            new Question(
                "ux_sys_jr_2",
                "What is a site map, and how does it map navigation paths for a web portal?",
                "Discuss page nodes, parent-child relationships, main navigation links, and footer links.",
                "A sitemap is a flowchart mapping page hierarchy. It structures main menus and parent-child paths, organizing user navigation flows.",
                Arrays.asList("sitemap", "navigation paths", "structure", "hierarchy", "nodes", "flowchart")
            ),
            new Question(
                "ux_sys_jr_3",
                "What are wireframes, and why are they used to map basic screen layouts?",
                "Discuss structural zones, grid boundaries, content blocks, and omitting color/styling.",
                "Wireframes are low-fidelity drawings mapping structural layout. They define content block positions and alignment rules while omitting colors and styling.",
                Arrays.asList("wireframe", "structure", "layout map", "low fidelity", "content block", "boundaries")
            )
        ));
        uxdesignerSystemDesign.put("mid", Arrays.asList(
            new Question(
                "ux_sys_mid_1",
                "Design an onboarding flow for a mobile app. How do you balance data gathering with friction reduction?",
                "Discuss social logins, progressive profiling, permission requests, and user value demonstrations.",
                "I design a fast onboarding flow using social login. Detailed profiling questions are postponed (progressive profiling), asking for data only when value is shown.",
                Arrays.asList("onboarding", "progressive profiling", "friction reduction", "social login", "welcome screens", "permissions")
            ),
            new Question(
                "ux_sys_mid_2",
                "Design a dashboard navigation structure for a data-dense enterprise tool. What layouts would you choose?",
                "Compare left vertical navigation, collapsible sidebars, global headers, and tab navigation.",
                "I choose a persistent left vertical menu with a collapsible toggle. Sub-pages are grouped into tabbed views in the central workspace, keeping layout clean.",
                Arrays.asList("navigation structure", "vertical menu", "collapsible", "workspace", "tabs", "layout")
            ),
            new Question(
                "ux_sys_mid_3",
                "What is card sorting, and how does it help build intuitive sitemaps?",
                "Discuss user categorization, open vs closed card sorting, and mapping clusters.",
                "Card sorting is a research test where users group topics into categories. Open sorts let users name categories; closed sorts use pre-defined menus, building intuitive sitemaps.",
                Arrays.asList("card sorting", "sitemap", "categorization", "mental models", "open sort", "closed sort")
            )
        ));
        uxdesignerSystemDesign.put("senior", Arrays.asList(
            new Question(
                "ux_sys_sr_1",
                "Design a checkout flow for a global e-commerce app. How do you solve localized pricing, payment methods, and error recovery?",
                "Discuss guest checkout, address autofill integrations, local payment options (e.g. UPI, Pix), and inline validation.",
                "I design a single-screen checkout with guest options. We integrate address autofill, localized payment APIs (UPI/Pix), and inline validation with clear error recovery.",
                Arrays.asList("checkout flow", "localization", "guest checkout", "validation", "error recovery", "autofill")
            ),
            new Question(
                "ux_sys_sr_2",
                "Design a design token delivery pipeline to synchronize style variables from Figma to multi-platform code repositories (Web, iOS, Android).",
                "Discuss token hierarchy (global/alias/component), Style Dictionary compilation, and auto pull requests.",
                "Style variables are stored in Figma. We export tokens in JSON formats. A build script compiles them into CSS/XML/Swift variables and automatically opens pull requests.",
                Arrays.asList("token pipeline", "Style Dictionary", "figma variables", "JSON", "automation", "PR", "platform sync")
            ),
            new Question(
                "ux_sys_sr_3",
                "Design a user-centric dashboard feedback mechanism that logs app store reviews, support tickets, and NPS surveys.",
                "Discuss sentiment tag filters, aggregation dashboards, search indices, and routing alerts to product teams.",
                "Feedback flows to an ingestion queue. We run sentiment filters on reviews, index keywords in Elasticsearch, and route negative feedback alerts directly to product leads.",
                Arrays.asList("feedback pipeline", "NPS", "sentiment analysis", "routing alerts", "dashboard dashboard", "search index")
            )
        ));
        uxdesignerTypes.put("system-design", uxdesignerSystemDesign);

        questionBank.put("ux-designer", uxdesignerTypes);

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

        if (limit > shuffled.size()) {
            return shuffled;
        }
        return shuffled.subList(0, limit);
    }
}
