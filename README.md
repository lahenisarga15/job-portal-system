# CareerSpring — Job Portal System

A complete Java Spring Boot web application based on the **Job Portal System** project in the internship PDF. It supports applicant and employer accounts, secure registration/login, job posting, job search/filtering, applications, and application status tracking.

## Technology
- Java 17, Spring Boot 3.4.5, Maven
- Spring MVC + Thymeleaf templates
- Spring Security with BCrypt password hashing and role-based route protection
- Spring Data JPA
- H2 embedded database for first-run convenience
- MySQL connector and a MySQL profile for the PDF's MySQL requirement
- Responsive custom CSS UI (purple/blue/pink theme)

## Run quickly (no database setup)
Requirements: JDK 17+ and Maven 3.8+.

1. Extract `Job-Portal-System.zip`.
2. Open the extracted `job-portal-system` folder in VS Code.
3. Open a terminal in that folder.
4. Run:

   ```bash
   mvn spring-boot:run
   ```

5. Open **http://localhost:8080** in your browser.

The default profile uses an embedded file-based H2 database, and sample jobs/users are added automatically. No MySQL installation is needed for the first demo. Data is saved under the project's `data` folder.

## Demo credentials
| Role | Email | Password |
|---|---|---|
| Applicant | `applicant@demo.com` | `Applicant@123` |
| Employer | `employer@demo.com` | `Employer@123` |

Use the employer account to post jobs and review/change applicants' statuses. Use the applicant account to apply and track status. You can also register new accounts.

## Run with MySQL
1. Install/start MySQL and create the database (or allow the URL to create it):

   ```sql
   CREATE DATABASE job_portal;
   ```

2. Set database credentials. For example in Bash:

   ```bash
   export DB_USERNAME=root
   export DB_PASSWORD=your_mysql_password
   ```

   In PowerShell:

   ```powershell
   $env:DB_USERNAME="root"
   $env:DB_PASSWORD="your_mysql_password"
   ```

3. Run with the MySQL profile:

   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=mysql
   ```

   Alternatively, run `java -jar target/job-portal-system-1.0.0.jar --spring.profiles.active=mysql` after packaging. JPA creates/updates the `users`, `jobs`, and `applications` tables; demo data is seeded automatically.

## Features
- Applicant/employer roles and BCrypt-protected login
- New account registration with role selection
- Employer dashboard to create and view job posts
- Applicant job listings with keyword search (title/company/location) and job-type filtering
- Job detail page and one application per applicant per job
- Applicant dashboard and application status tracking
- Employer view of applications for its own jobs and status changes: Pending, Shortlisted, Hired, Rejected
- Responsive colourful UI and demo data

## Project structure
```
src/main/java/com/nisarga/jobportal/
  JobPortalApplication.java
  DataLoader.java
  config/SecurityConfig.java
  controller/ (auth, home, jobs, employer, applicant)
  entity/ (AppUser, Job, JobApplication)
  repository/ (JPA repositories)
src/main/resources/
  application.properties             # easy-run H2 profile
  application-mysql.properties       # MySQL profile
  schema-mysql.sql
  templates/                         # Thymeleaf pages
  static/css/style.css
```

## Useful commands
```bash
mvn clean package
mvn spring-boot:run
```

**Security note:** demo credentials are for local demonstration only. Change them before any public deployment. The MySQL database schema is managed by JPA; `schema-mysql.sql` documents database creation.
