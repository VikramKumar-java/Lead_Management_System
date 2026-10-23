# Lead Management System

A full-stack web application developed using **Java, Spring Boot, Spring Security, JWT, React.js, and MySQL** to manage customer leads, track their progress, and monitor lead statistics through a dashboard.

## Project Overview

The Lead Management System helps users organize and manage potential customer information efficiently. Registered users can securely log in, create leads, update customer details, track lead statuses, schedule follow-ups, and view dashboard statistics.

This project demonstrates REST API development, JWT-based authentication, database integration, and frontend-backend communication.

## Technology Stack

### Backend
- Java 25
- Spring Boot 4
- Spring Security
- JWT Authentication
- Spring Data JPA / Hibernate
- MySQL
- Maven
- REST APIs

### Frontend
- React.js
- JavaScript
- Bootstrap 5
- Axios
- React Router
- Vite

### Development Tools
- IntelliJ IDEA
- Visual Studio Code
- MySQL Workbench
- Postman
- Git and GitHub

## Features

### 1. User Authentication
- User registration
- Secure login
- Password encryption using BCrypt
- JWT token generation and validation
- Protected API endpoints

### 2. Lead Management
- Add new customer leads
- View all leads belonging to the logged-in user
- View individual lead details
- Update lead information
- Delete leads
- Assign lead statuses
- Maintain follow-up dates and notes

### 3. Dashboard
- Total leads
- New leads
- Contacted leads
- Qualified leads
- Converted leads
- Lost leads

### 4. Security
- JWT-based authentication
- Stateless session management
- User-specific lead access
- Protected REST APIs
- CORS configuration for React integration

## Lead Statuses

| Status | Description |
|---|---|
| NEW | Newly created lead |
| CONTACTED | Customer has been contacted |
| QUALIFIED | Customer shows potential interest |
| CONVERTED | Lead converted into a customer |
| LOST | Lead did not convert |

## Project Structure

```text
Lead-Management-System/
│
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/swan/lead_management_system/
│   │       │   ├── Config/
│   │       │   ├── Controller/
│   │       │   ├── Entity/
│   │       │   ├── Repository/
│   │       │   ├── Security/
│   │       │   ├── Service/
│   │       │   └── dto/
│   │       └── resources/
│   │           └── application.properties
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   ├── index.html
│   ├── package.json
│   └── vite.config.js
│
└── README.md
```

## REST API Endpoints

### Authentication APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate user and obtain JWT |

### Lead APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/leads` | Create a lead |
| GET | `/api/leads` | Retrieve logged-in user's leads |
| GET | `/api/leads/{id}` | Retrieve lead by ID |
| PUT | `/api/leads/{id}` | Update lead |
| DELETE | `/api/leads/{id}` | Delete lead |

### Dashboard API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/dashboard` | Retrieve lead statistics |

Protected endpoints require:

```http
Authorization: Bearer <JWT_TOKEN>
```

## Database

**Database:** MySQL

**Database name:** `lead_management_db`

### Main Entities

**User**
- ID
- Name
- Email
- Password

**Lead**
- ID
- Name
- Email
- Phone Number
- Company
- Service Interested
- Status
- Follow-up Date
- Notes
- Created Date
- Associated User

Each lead is associated with a registered user through a many-to-one relationship.

## Installation and Setup

### Prerequisites

Install:
- JDK 25
- Maven
- MySQL
- Node.js and npm
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/Lead-Management-System.git
cd Lead-Management-System
```

### 2. Configure MySQL

Create the database:

```sql
CREATE DATABASE lead_management_db;
```

Configure your Spring Boot application with your local database credentials and JWT signing secret. Keep these values in environment variables or an untracked local configuration file.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lead_management_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Keep your existing JPA and JWT configuration as required by the backend.

### 3. Run Spring Boot Backend

```bash
cd backend
mvn spring-boot:run
```

Backend URL:

`http://localhost:8080`

### 4. Run React Frontend

Open another terminal:

```bash
cd frontend
npm install
npm run dev
```

On Windows PowerShell, if npm scripts are blocked, use `npm.cmd install` and `npm.cmd run dev`.

Frontend URL:

`http://localhost:5173`

### 5. Use the Application

1. Open the frontend in your browser.
2. Register a user account.
3. Log in with your credentials.
4. Add and manage customer leads.
5. Update lead statuses and follow-up details.
6. View lead statistics on the dashboard.

## API Testing

The backend APIs can be tested using Postman.

Example lead creation request:

**POST** `/api/leads`

```json
{
  "name": "Rahul Sharma",
  "email": "rahul@example.com",
  "phoneNumber": "9876543210",
  "company": "ABC Technologies",
  "serviceInterested": "Website Development",
  "status": "NEW",
  "followUpDate": "2026-10-28",
  "notes": "Interested in website development"
}
```

## Future Enhancements

- Advanced lead search and filtering
- Email reminders for follow-ups
- Analytics and visual charts
- Deployment to a cloud platform

## Developer

**Vikram Kumar**

Java Full Stack Developer

**Skills:** Java, Spring Boot, Spring Security, REST APIs, Spring Data JPA, Hibernate, MySQL, React.js

**LinkedIn:** https://www.linkedin.com/in/vikram-kumar-sangishetti-404ab42aa/

## Project Purpose

Developed as a practical full-stack project to demonstrate backend development, RESTful API design, secure authentication, database management, and React integration.

## License

Created for educational and assignment demonstration purposes. No open-source license has been specified.
