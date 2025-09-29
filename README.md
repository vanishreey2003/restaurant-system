# Restaurant Ordering & Management System

This repository contains a simple full-stack scaffold:
- Backend: Spring Boot (Java 17, Maven) — provides REST endpoints for menu and orders
- Frontend: React (minimal) — simple UI to browse menu and place orders

## Quick start (backend)

1. Build & run (requires Java 17 & Maven):

```bash
cd backend
mvn spring-boot:run
```
The backend runs on http://localhost:8080 and exposes H2 console at http://localhost:8080/h2-console

2. Bootstrap sample menu (once backend is up):
```bash
curl -X POST http://localhost:8080/api/menu/bootstrap
```

## Quick start (frontend)

1. Install dependencies and run (requires Node.js & npm):

```bash
cd frontend
npm install
npm start
```

The frontend dev server will run at http://localhost:3000 and proxy API requests to http://localhost:8080 (dev).

## GitHub
Initialize a repo and push. See the project scaffold for `.gitignore` and Dockerfile samples.
