# 🚀 IIPS Innovation, Startup & Incubation Management System

A centralized platform to streamline startup idea management, incubation support, mentor allocation, and funding tracking at IIPS.

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.2
- **ORM:** Spring Data JPA / Hibernate
- **Database:** MySQL
- **Build Tool:** Maven
- **Architecture:** RESTful APIs

## Key Features

- **Startup Management:** Submit, review, approve, and reject startup ideas.
- **Incubation Support:** Manage workspace, legal, technical, and training requests.
- **Mentor Allocation:** Register mentors and assign them to startups.
- **Funding Tracker:** Track grants, seed funding, angel investments, and their status.
- **Dashboard:** View overall incubation activity summaries.

## Getting Started

**Prerequisites:** Java 17, Maven, and MySQL.

Clone the repository:

```bash
git clone https://github.com/YOUR-USERNAME/iips-incubation.git
cd iips-incubation
```

Run the application:

```bash
mvn spring-boot:run
```

**Application URL:**

- API Base URL: `http://localhost:8081/api`

Configure your MySQL database credentials in `src/main/resources/application.properties` before running the application.

## API Modules

| Module | Main Endpoints |
|---|---|
| Startups | `/api/startups` |
| Incubation Support | `/api/support` |
| Mentors | `/api/mentors` |
| Mentor Allocation | `/api/allocations` |
| Funding | `/api/funding` |
| Dashboard | `/api/dashboard` |

## Developers

- **Lakshya Bagora**
- **Gourav Mandloi**

---

Developed to simplify and digitize the startup incubation ecosystem at IIPS.

**Built using Java & Spring Boot.**