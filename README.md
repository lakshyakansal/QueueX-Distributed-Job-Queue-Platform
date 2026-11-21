# QueueX — Distributed Job Queue Platform

A runnable, Docker-first distributed job queue built with Java 21, Spring Boot, Kafka, PostgreSQL, Redis and React.

## Features
- REST API to submit, inspect, cancel and retry jobs
- Persistent PostgreSQL job state and execution attempts
- Kafka-based dispatch with a separate worker service
- Redis worker heartbeats and live worker status
- Priority, delayed execution, retry with exponential backoff, dead-letter state
- JWT authentication (register/login) and protected API routes
- React dashboard with job submission, filters, worker status and refresh
- OpenAPI/Swagger, health endpoints, Docker Compose

## Run
Requirements: Docker Desktop / Docker Engine with Compose.

```bash
cp .env.example .env
docker compose up --build
```

- UI: http://localhost:5173
- API: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui.html
- API health: http://localhost:8080/actuator/health

Register from the UI or `POST /api/auth/register` with `{ "email":"demo@example.com", "password":"ChangeMe123!" }`, then log in. Submit a job from the dashboard. The demo worker supports `echo`, `uppercase`, `sleep`, and `fail` job types. For example:

```json
{ "type":"uppercase", "payload":{"text":"hello queuex"}, "priority":8, "maxAttempts":4 }
```

A delayed job accepts `scheduledAt` as an ISO-8601 timestamp. Retry policy uses exponential backoff. After `maxAttempts`, jobs enter `DEAD` and can be manually retried.

## Architecture
`React UI -> Spring Boot API -> PostgreSQL + Kafka -> Worker service -> PostgreSQL`

Redis stores worker heartbeats. The API scheduler periodically republishes due `QUEUED` jobs; workers claim jobs transactionally using a database state transition, so duplicate Kafka deliveries do not result in duplicate execution. Kafka is at-least-once; handlers should be idempotent for external side effects.

## API quick reference
- `POST /api/auth/register`, `POST /api/auth/login`
- `POST /api/jobs`, `GET /api/jobs`, `GET /api/jobs/{id}`
- `POST /api/jobs/{id}/cancel`, `POST /api/jobs/{id}/retry`
- `GET /api/workers`
- `GET /actuator/health`, `/swagger-ui.html`

All `/api/jobs` and `/api/workers` endpoints require `Authorization: Bearer <token>`.

## Local development
Backend: `cd backend && mvn spring-boot:run`
Worker: `cd worker && mvn spring-boot:run`
Frontend: `cd frontend && npm install && npm run dev`

Set the same environment variables as `.env.example`. For production, replace the demo JWT secret, enable TLS, configure Kafka/Postgres/Redis credentials, and add authorization policies appropriate to your tenancy model.
