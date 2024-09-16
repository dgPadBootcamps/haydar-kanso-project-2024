Project Overview
Employee Control is a web-based employee attendance management system. It allows employees to register, check-in, check-out, and track their attendance. Admins can monitor attendance, reset all records, and manage users.

Technologies Used
Java 17: Core language for the application.
Spring Boot 3.0: Framework used for building the web application.
Spring Security for authentication and authorization.
Thymeleaf for building the front-end UI.
Spring Data JPA for database management.
MySQL: Relational database to store user and attendance data.
Maven: Dependency management and project build tool.
HTML/CSS: For user interface design.
BCrypt: For password encryption and security.
Features Implemented
User Registration and Login: Users can register and log in with secure authentication.
Check-in/Check-out: Users can record their attendance via check-in and check-out.
Attendance Management: The application tracks daily attendance.
Admin Functionality: Admin can reset all attendance records.
Real-Time Clock: The attendance page displays a real-time clock.
Logout: Users can log out from the system securely.
Project Structure
css
Copy code
src/
 └── main/
     └── java/
         └── com/Kanso/EmployeeControlApp/
             ├── controller/
             │   ├── AttendanceController.java
             │   └── UserController.java
             ├── model/
             │   ├── Attendance.java
             │   └── User.java
             ├── repository/
             │   ├── AttendanceRepository.java
             │   └── UserRepository.java
             ├── service/
             │   ├── AttendanceService.java
             │   └── UserService.java
             └── config/
                 └── SecurityConfig.java
Key Packages:
controller: Handles HTTP requests and maps them to appropriate services.
model: Contains the entities for User and Attendance.
repository: Interfaces to interact with the database.
service: Contains business logic for attendance and user management.
config: Manages security configuration, including authentication and authorization rules.
Services and Logic Implementations
AttendanceService
Manages the check-in and check-out process.
Implements logic for resetting all attendance records.
UserService
Manages user registration and password encoding using BCrypt.
Implements Spring Security's UserDetailsService for user authentication.
Database Modeling and Relations
The application uses a relational database (MySQL) with two main entities:

User: Represents an employee with fields such as id, username, password, and role.
Attendance: Tracks check-in and check-out times for each user, linked via a foreign key to the User entity.
Entity Relationship:
One-to-many relationship: A user can have many attendance records.
Authorization and Authentication
BCryptPasswordEncoder is used for secure password hashing.
Role-based access control is implemented, allowing ADMIN users to reset attendance and manage other users.
Security configuration ensures restricted access to endpoints based on roles, with public access only to registration and login pages.
he UI is built using Thymeleaf templates, styled with CSS for a clean and user-friendly experience.
Real-time feedback provided on the attendance page with live clock display.
Clear distinction between different user roles, e.g., Admin vs. Employee.
API Endpoints
HTTP Method	Endpoint	Description	Authorization
GET	/register	Displays user registration page	Public
POST	/register	Registers a new user	Public
GET	/login	Displays login page	Public
POST	/attendance/checkin	Checks in the authenticated user	Authenticated
POST	/attendance/checkout	Checks out the authenticated user	Authenticated
DELETE	/attendance/reset	Resets all attendance records (admin)	Admin