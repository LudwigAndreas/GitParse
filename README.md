# Git Unified Admin Panel

🚧 Project Status: In Progress 🚧

**Note**: This project is currently under development. Not all features have been implemented yet. Please refer to the Features section for details on what has been completed and what is still in progress.

## Overview

The project is a web application that provides an administrative panel for managing users and tasks on Github and Gitlab platforms, as well for monitoring commit statistics. The application is divided into frontend and backend parts and is implemented in Java using a microservice architecture.

## Features

- [x] Implementation of extensible application backend architecture
- [x] Unification of gitlab and github api
- [x] Adding OAuth2 authentication for access to github and gitlab
- [ ] Saving statistics to database
- [x] Add ability to view statistics as a table
- [ ] Add ability to view statistics as a chart
- [ ] Adding sorting, filtering, pagination for queries

## Architecture

### Components

- [ ] **Frontend** - Vite, ReactJS Frontend application
- [ ] **Backend** - Java Spring Boot application that contains business logic
## Getting Started

### Prerequisites

- **Docker**: Containerization
- **Docker Compose**: Deployment

### Installation

1. Clone the repository

   ```sh
   git clone https://github.com/LudwigAndreas/GitParse.git
   ```

2. Change directory

   ```sh
    cd GitParse
    ```

3. Build the Docker images

   ```sh
   docker-compose build
   ```

4. Start the services

   ```sh
    docker-compose up
    ```

## Services

### Backend

Backend application is used for api unification, saving to database and storing user data. Backend implemented as monolithic service. The architecture was chosen because of the time to develop an MVP.

Backend service can be accessed at [http://localhost:8080](http://localhost:8080)

### Frontend

The frontend is developed on ReactJS, Vite, Tailwind, Shadcn, Radix-ui stack. It allows you to get data representation in the form of tables and charts. For the most part, shadcn components were used for visualization.

Frontend service can be accessed at [http://localhost:3000](http://localhost:3000)

## Configuration

### Environment Variables

The services are configured using environment variables. The environment variables are defined in the `.env` file.

```env
# Backend
API_URL = http://localhost:8080


```

## Deployment

### Docker Compose

The application is containerized using Docker and deployed using Docker Compose. The services are defined in the `docker-compose.yml` file.

```sh
docker-compose up
```

## Usage

Examples of how to use the API endpoints described in the `docs` directory. (**Not implemented yet**)

### Authentication

(**Not implemented yet**)

### API Requests

Documentation for the API endpoints can be found in the `docs` directory. (**Not implemented yet**)

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
