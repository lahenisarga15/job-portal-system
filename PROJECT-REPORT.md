# Job Portal System — Project Report

## Introduction
The Job Portal System is a web application that brings job applicants and employers together in one platform. Applicants can search available opportunities, submit applications, and track application progress. Employers can publish job listings and review candidates' applications.

## Abstract
CareerSpring is developed using Java and Spring Boot, with Thymeleaf for server-rendered web pages. Spring Security provides login, registration support, password hashing, and role-based access for applicants and employers. Spring Data JPA manages the Users, Jobs, and Applications data. The application provides job search by title, company, or location, job-type filtering, job posting, duplicate-application prevention, and application status updates. A colourful responsive interface makes the application easy to navigate. The project includes an embedded H2 profile for quick demonstrations and a MySQL profile for database-based deployment.

## Tools Used
- Java 17 and Spring Boot
- Maven
- Thymeleaf, HTML, CSS
- Spring Security and BCrypt password encoder
- Spring Data JPA / Hibernate
- H2 database for quick local execution; MySQL profile and schema setup included
- Visual Studio Code or another Java IDE

## Steps Involved in Building the Project
1. Created the Spring Boot Maven project and added web, Thymeleaf, JPA, Security, validation, H2, and MySQL dependencies.
2. Designed the `users`, `jobs`, and `applications` entities and their JPA repositories.
3. Configured registration, login, password hashing, and role-based authorization for employer and applicant accounts.
4. Built Thymeleaf pages for the home page, registration/login, job search and details, employer dashboard, and applicant dashboard.
5. Implemented employer job posting and application review/status updates, and applicant job applications/status tracking.
6. Added keyword search, job-type filtering, sample jobs/demo credentials, and responsive styling.
7. Ran the application locally and prepared H2 quick-start settings plus a MySQL profile.

## Conclusion
The project demonstrates a complete basic job portal workflow for two user roles. It provides secure authentication, job discovery, posting, applying, and application tracking in one application. Future improvements could include resume uploads, email notifications, pagination, and production deployment.
