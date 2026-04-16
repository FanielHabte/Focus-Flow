# Focus Flow

A personal task management and focus time web application built with Java and Spring Boot. Designed to improve study discipline and daily productivity by combining task management with focus session tracking.

**Live demo:** https://focus-flow-n8ne.onrender.com

> Note: hosted on Render's free tier — first load after inactivity may take 30–60 seconds to wake up.

---

## What it does

- Create, view, and manage tasks with categories and due dates
- Filter tasks by All, Today, and Completed
- Mark tasks as complete or delete them (soft delete — data is preserved)
- View productivity analytics — completion rate and open task count
- See the nearest upcoming task at a glance

---

## Tech stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0 |
| View layer | Thymeleaf + vanilla CSS |
| Persistence | Spring Data JPA / Hibernate |
| Database | PostgreSQL (hosted on Neon) |
| Deployment | Docker + Render |
| Version control | GitHub |

---

## Architecture

The application follows a strict four-layer architecture:

```
Browser (Thymeleaf HTML)
        ↓
Controller layer — routes HTTP requests, populates model
        ↓
Service layer — business logic, filtering, analytics
        ↓
Repository layer — JPA data access, derived query methods
        ↓
PostgreSQL on Neon (focus_flow schema)
```

Each layer communicates only with the layer directly below it. Controllers never access repositories directly. Business logic never lives in controllers.

---

## Data model

**Task**
- `taskId`, `title`, `description`, `category` (enum), `status` (enum)
- `scheduledStart`, `dueDate`, `createdAt`, `updatedAt`, `completedAt`
- Timestamps set automatically via `@PrePersist` and `@PreUpdate`

**FocusSession** *(data model ready, feature in v2)*
- `sessionId` (UUID), `task` (ManyToOne), `startTime`, `endTime`
- `plannedDurationMinutes`, `actualDurationMinutes`, `sessionStatus` (enum)

**UserSettings** *(data model ready, feature in v2)*
- `settingsId`, `pomodoroDuration`, `shortBreakDuration`, `reminderLeadMinutes`, `musicEnabled`

---

## Key design decisions

**Constructor injection over field injection**
All Spring dependencies are injected via constructor, not `@Autowired` field injection. This makes dependencies explicit, classes immutable, and unit testing straightforward.

**Soft deletes**
Tasks are never permanently deleted. Marking a task as deleted sets its status to `INACTIVE`. This preserves data for future analytics and avoids accidental data loss.

**Enum-typed status and category fields**
`TaskStatus` and `TaskCategory` are enums stored as strings via `@Enumerated(EnumType.STRING)`. This prevents invalid values at the Java level and keeps database values human-readable.

**Single database call pattern**
Analytics methods (`completionPercentage`, `countOfOpenTasks`) fetch all tasks once and compute results in memory rather than making separate database calls per metric.

**Server-side rendering**
Thymeleaf renders complete HTML on the server. No JavaScript framework, no separate API layer, no CORS configuration. JS is used only for client-side filter toggling.

**Named schema**
All tables live under the `focus_flow` schema in PostgreSQL rather than `public`. Cleaner organisation and easier to manage permissions in future multi-tenant scenarios.

---

## Running locally

**Prerequisites**
- Java 21
- A PostgreSQL database (Neon free tier recommended)

**1. Clone the repo**
```bash
git clone https://github.com/yourusername/focus-flow.git
cd focus-flow
```

**2. Create a `.env` file in the project root**
```
DATABASE_URL=jdbc:postgresql://your-neon-host/neondb?sslmode=require&channelBinding=require
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
```

**3. Add environment variables to your IntelliJ run configuration**

Run → Edit Configurations → Environment variables → add the three variables from your `.env` file.

**4. Run the application**
```bash
./gradlew bootRun
```

App starts at `http://localhost:8080`

---

## Deployment

The app is containerised with Docker and deployed on Render. Every push to `main` triggers an automatic redeploy.

**Dockerfile uses a multi-stage build:**
- Stage 1: JDK image compiles the source and builds the JAR
- Stage 2: Lean JRE image copies and runs the JAR

Environment variables (`DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`) are set in Render's dashboard and injected at runtime — never stored in source code.

---

## What's planned for v2

- Focus session timer — start, pause, and complete timed focus sessions tied to tasks
- Overdue task detection — scheduled background job to update task status automatically
- User authentication — Spring Security with session-based login
- Richer analytics dashboard — focus time totals, streaks, category breakdowns
- Recurring tasks
- Mobile-responsive layout improvements

---

## About this project

Focus Flow was built as a hands-on backend engineering portfolio project during a career transition from Business Intelligence Engineering to backend software development. Every architectural decision — from the layered service pattern to the soft delete approach to the named schema — was made deliberately and can be explained in full.

The goal was to build something real, deployed, and maintainable — not a tutorial clone.