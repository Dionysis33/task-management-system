# Enterprise Task Management System

<p align="center">
  <strong>Full-stack Java/Spring Boot task management system demonstrating enterprise architecture, authentication, RBAC, CRUD workflows, and MySQL persistence.</strong>
</p>

<p align="center">
  <a href="https://github.com/Dionysis33/task-management-system/actions/workflows/maven.yml">
    <img alt="Java CI with Maven" src="https://github.com/Dionysis33/task-management-system/actions/workflows/maven.yml/badge.svg?branch=master">
  </a>
  <a href="./LICENSE">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg">
  </a>
</p>

<p align="center">
  <a href="#project-overview">
    <img alt="Overview" src="https://img.shields.io/badge/Overview-Project-blue.svg">
  </a>
  <a href="#role-relevance">
    <img alt="Role Relevance" src="https://img.shields.io/badge/Role-Relevance-2ea44f.svg">
  </a>
  <a href="#key-features">
    <img alt="Features" src="https://img.shields.io/badge/Features-Core%20Functionality-purple.svg">
  </a>
  <a href="#tech-stack">
    <img alt="Tech Stack" src="https://img.shields.io/badge/Tech-Stack-orange.svg">
  </a>
  <a href="#architecture-and-design">
    <img alt="Architecture" src="https://img.shields.io/badge/Architecture-N--Tier-blueviolet.svg">
  </a>
  <a href="#how-to-run-locally">
    <img alt="Run Locally" src="https://img.shields.io/badge/Run-Locally-success.svg">
  </a>
  <a href="#demo-access">
    <img alt="Demo Access" src="https://img.shields.io/badge/Demo-Access-yellow.svg">
  </a>
</p>

<p align="center">
  <a href="#tech-stack">
    <img alt="Java 21" src="https://img.shields.io/badge/Java-21-orange.svg">
  </a>
  <a href="#tech-stack">
    <img alt="Spring Boot 3.3" src="https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen.svg">
  </a>
  <a href="#tech-stack">
    <img alt="Spring Security 6" src="https://img.shields.io/badge/Spring%20Security-6-green.svg">
  </a>
  <a href="#tech-stack">
    <img alt="MySQL" src="https://img.shields.io/badge/Database-MySQL-blue.svg">
  </a>
  <a href="#tech-stack">
    <img alt="Maven" src="https://img.shields.io/badge/Build-Maven-red.svg">
  </a>
</p>

<p align="center">
  <a href="./src/">
    <img alt="Source Code" src="https://img.shields.io/badge/Source-Code-3776AB.svg">
  </a>
  <a href="./pom.xml">
    <img alt="Maven pom.xml" src="https://img.shields.io/badge/Maven-pom.xml-C71A36.svg">
  </a>
  <a href="./DEVELOPMENT_LOG.md">
    <img alt="Development Log" src="https://img.shields.io/badge/Development-Log-yellow.svg">
  </a>
  <a href="./Use_Case_Diagram.drawio">
    <img alt="Use Case Diagram" src="https://img.shields.io/badge/Diagram-Use%20Case-purple.svg">
  </a>
  <a href="./ERD.drawio">
    <img alt="ERD" src="https://img.shields.io/badge/Diagram-ERD-informational.svg">
  </a>
  <a href="./Class_Diagram_Diagram.drawio">
    <img alt="Class Diagram" src="https://img.shields.io/badge/Diagram-Class%20Diagram-lightgrey.svg">
  </a>
</p>

<p align="center">
  <a href="#architecture-and-design">
    <img alt="N-Tier Architecture" src="https://img.shields.io/badge/Architecture-N--Tier-blueviolet.svg">
  </a>
  <a href="#architecture-and-design">
    <img alt="DTO Pattern" src="https://img.shields.io/badge/Pattern-DTO-success.svg">
  </a>
  <a href="#architecture-and-design">
    <img alt="Repository Pattern" src="https://img.shields.io/badge/Pattern-Repository-success.svg">
  </a>
  <a href="#main-functionality">
    <img alt="RBAC" src="https://img.shields.io/badge/Security-RBAC-critical.svg">
  </a>
  <a href="#what-i-built">
    <img alt="Portfolio Project" src="https://img.shields.io/badge/Portfolio-Project-2ea44f.svg">
  </a>
</p>

---

## Project Overview

The **Enterprise Task Management System** is a full-stack task management web application built with **Java 21**, **Spring Boot 3.3**, **Spring Security 6**, and **MySQL**.

This project was developed as part of the **SWE6002 - Enterprise Systems Development** module and demonstrates enterprise application architecture, authentication, role-based access control, CRUD workflows, database persistence, and secure data handling using modern Java technologies.

The application is designed to help users organize academic, personal, and work-related tasks through a secure web interface.

---

## Academic Context

**Student:** Dionysis Alexopoulos  
**Module:** SWE6002 - Enterprise Systems Development  
**Supervisor:** Spyros Mavros  

> Note: This repository is presented as both an academic submission and a software engineering portfolio project.

---

## Role Relevance

This repository is relevant for:

- Junior Java Developer roles
- Spring Boot Backend Developer internships
- Graduate Software Engineer positions
- QA / Tester roles involving web applications
- Entry-level backend engineering roles

---

## Key Features

- User registration and login
- Role-Based Access Control with `ROLE_ADMIN` and `ROLE_MEMBER`
- Admin user management
- Task creation, viewing, editing, deletion, searching, and filtering
- MySQL database persistence
- DTO pattern to reduce unsafe direct entity exposure
- N-tier architecture using Controller, Service, and Repository layers
- Spring Security 6 authentication and authorization
- Input validation and structured request handling

---

## Tech Stack

| Area | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.3 |
| Security | Spring Security 6 |
| Database | MySQL |
| Frontend | HTML, CSS |
| Build Tool | Maven |
| Architecture | N-tier architecture |
| Design Patterns | DTO Pattern, Repository Pattern |

---

## Architecture and Design

The application follows an **N-tier architecture** to separate responsibilities and improve maintainability.

### Controller Layer

Handles HTTP requests, validates user input, and routes requests to the appropriate service methods.

### Service Layer

Contains the core business logic of the application, including user management, task management, and authorization rules.

### Repository Layer

Communicates with the MySQL database using the Repository Pattern and provides data access abstraction.

### DTO Pattern

Data Transfer Objects are used to reduce unsafe direct exposure of entity models and help prevent issues such as Mass Assignment.

---

## Demo Access

This project includes local-only demo users for testing Role-Based Access Control.

> Security note: These are demo credentials for local development only. They are not production credentials and should never be reused outside this project.

| Role | Username | Password |
|---|---|---|
| Admin | `admin_demo` | `change-me-local-only` |
| Student Member | `student_demo` | `change-me-local-only` |
| Work User | `work_demo` | `change-me-local-only` |

For real deployments, credentials must be configured through environment variables and should never be committed to the repository.

---

## How to Run Locally

### 1. Clone the Repository

```bash
git clone https://github.com/Dionysis33/task-management-system.git
cd task-management-system
```

### 2. Create a MySQL Database

```sql
CREATE DATABASE task_management;
```

### 3. Configure Database Credentials

Update your local configuration using `application.properties` or environment variables.

Example local configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_management
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
```

> Do not commit real database credentials to GitHub.

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

If you are using Windows PowerShell:

```powershell
.\mvnw spring-boot:run
```

### 5. Open the Application

```text
http://localhost:8080
```

---

## Main Functionality

### User Management

- User registration
- User login
- Admin-level user management
- Role assignment and access control

### Task Management

- Create tasks
- View task list
- Edit existing tasks
- Delete tasks
- Search and filter tasks by category and date

### Security

- Authentication with Spring Security
- Role-Based Access Control
- Separation between admin and member users
- DTO usage to reduce unsafe data exposure

---

## What I Built

- Designed the backend architecture using Controller, Service, and Repository layers
- Implemented authentication and authorization with Spring Security
- Built CRUD functionality for task management
- Added role-based separation between admin and member users
- Designed database entities and relationships
- Used DTOs to reduce unsafe direct entity exposure
- Documented the development process and architecture diagrams

---

## Project Structure

```text
task-management-system/
├── .mvn/
│   └── wrapper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── Class_Diagram_Diagram.drawio
├── DEVELOPMENT_LOG.md
├── ERD.drawio
├── Use_Case_Diagram.drawio
├── README.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```

---

## Architecture Diagrams

The repository includes supporting architecture and design diagrams:

- [Use Case Diagram](./Use_Case_Diagram.drawio)
- [Entity Relationship Diagram](./ERD.drawio)
- [Class Diagram](./Class_Diagram_Diagram.drawio)

These diagrams document the design decisions and structure of the application.

---

## Development Log

The detailed development log covering approximately 2.5 months of work is available here:

[DEVELOPMENT_LOG.md](./DEVELOPMENT_LOG.md)

---

## Screenshots

Add screenshots in a `docs/screenshots/` folder and reference them here.

```md
![Login Page](docs/screenshots/login.png)
![Dashboard](docs/screenshots/dashboard.png)
![Admin Panel](docs/screenshots/admin-panel.png)
```

---

## Limitations and Future Improvements

- Add REST API endpoints
- Add unit and integration tests
- Add Docker Compose for Spring Boot and MySQL
- Add CI pipeline with GitHub Actions
- Add API documentation with Swagger/OpenAPI
- Improve frontend responsiveness
- Add password reset functionality
- Add task priority levels and notifications

---

## License

This project is licensed under the MIT License.

See the [LICENSE](./LICENSE) file for details.
