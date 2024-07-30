# Git Unified Admin Panel

## Project overview

Github & Gitlab Unified Admin Panel

## Description

The project is a web application that provides an administrative panel for managing users and tasks on Github and Gitlab platforms, as well for monitoring commit statistics. The application is devided into frontend and backend parts and is implemented in Java using a microservice architecture.

## Project Goals

The main goals of the project are to create a user-frendly and functional administrative panel that allows:

- Adding users to projects on GitHub and GitLab.
- Automatically collecting and displaying commit statistics.
- Managing tasks and comments.
- Tracking pipeline events.

## Installation

### Prerequisites

- Java 11 or higher
- Node.js and npm
- Docker and Docker Compose

### Backend Setup

Clone the repository:

```bash
git clone https://github.com/your-repo/GitHub-GitLab-Admin-Panel.git
cd GitHub-GitLab-Admin-Panel/backend
```

Run postgres.yml docker compose file:

```bash
docker-compose -f docker/postgres.yml up -d
```

Build and run the backend application:

```bash
./mvnw spring-boot:run
```

Navigate to the frontend directory:

```bash
cd ../frontend
```

Install dependencies and start the frontend application:

```bash
npm install
npm start
```

## Usage

Open your web browser and navigate to [http://localhost:3000](http://localhost:3000).

Use the administrative panel to manage users, tasks, and monitor commit statistics on GitHub and GitLab.
