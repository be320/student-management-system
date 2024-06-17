
# Student Management System

## Overview

This project is a Spring Boot application designed to manage student-related activities. It provides a set of RESTful APIs for functionalities such as user authentication, course management, and scheduling. The application uses modern technologies and practices to ensure security, scalability, and maintainability.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [API Endpoints](#api-endpoints)
- [Database Migration](#database-migration)
- [Security](#security)
- [Exception Handling](#exception-handling)
- [Screenshots](#screenshots)

## Features

- **User Management**: Login and Signup functionality.
- **Course Management**: Create, view, register, and cancel course registrations.
- **Schedule Management**: Upload and download course schedules in PDF format.
- **Caching**: Used Redis for caching to improve performance.
- **Database**: Oracle database for data storage with Flyway for migrations.
- **Security**: JWT token-based authentication and authorization with hashed passwords.
- **Exception Handling**: Global exception handling using Controller Advice.

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Maven**
- **Hibernate ORM**
- **Flyway**
- **Docker**
  - Redis
  - Oracle Database
- **JWT (JSON Web Tokens)**
- **Postman**

## Setup Instructions

### Prerequisites

- Docker and Docker Compose
- Java 17
- Maven

### Steps

1. **Run Docker Compose**: This will start the Redis and Oracle Database containers.
   ```sh
   docker-compose up
   ```

2. **Run the Spring Boot Application**: Use Maven to build and run the application.
   ```sh
   mvn spring-boot:run
   ```

3. **Test the APIs**: Use the provided Postman collection to test the APIs.

## API Endpoints

### Authentication

- **Login**: `POST /api/system/auth/login`
- **Signup**: `POST /api/system/auth/signup`

### Courses

- **Create Course**: `POST /api/system/courses`
- **View Courses**: `GET /api/system/courses`
- **Upload Schedule**: `POST /api/system/courses/{courseCode}/upload-schedule`
- **Download Schedule**: `GET /api/system/courses/{courseCode}/download-schedule`

### Course Registration

- **Register for Course**: `POST /api/system/registrations/register`
- **Cancel Course Registration**: `DELETE /api/system/registrations/cancel`

## Database Migration

Flyway is used to manage database migrations. SQL files are located in the `src/main/resources/db/migration` directory. Migrations are automatically applied when the application starts.

## Security

- **JWT Tokens**: Used for authentication and authorization. Each token expires after 5 minutes.
- **Session Management**: Sessions expire after 10 minutes.
- **Password Hashing**: Secure hashing techniques are used to store passwords.

## Exception Handling

Global exception handling is implemented using Controller Advice to provide consistent error responses.

## Screenshots

Screenshots showing test case scenarios from Postman are available in the `postman` folder.