# 🏫 FixMyCampus - A smart Student Report Issue & Tracking System

A comprehensive web-based platform for managing campus maintenance and facility issues. Students can report problems, and administrators can track, manage, and resolve issues efficiently.

## 🎯 Overview

**FixMyCampus** is a Java-based web application designed to streamline campus facility management. It provides a centralized system where students can report maintenance issues (broken furniture, infrastructure problems, etc.) and administrators can review, prioritize, and track the resolution of these issues.

### Core Purpose
Bridge the gap between students experiencing campus problems and maintenance staff by providing a transparent, efficient issue tracking system.

## ✨ Key Features

- 👤 **User Authentication** - Secure login system with role-based access (Student/Admin)
- 📝 **Issue Reporting** - Students can easily report campus problems with descriptions and locations
- 📊 **Admin Dashboard** - Comprehensive view of all reported issues
- 🔄 **Status Tracking** - Real-time issue status updates (Pending → In Progress → Resolved)
- 📍 **Location Mapping** - Issues categorized by campus location and department
- 🏢 **Department Assignment** - Issues assigned to relevant departments
- 📱 **Student Dashboard** - Personal view of reported issues and their status
- 🔐 **Secure Access** - Role-based access control (RBAC) for data security
- 💾 **Data Persistence** - Relational database for reliable data storage

## 🏗️ Project Structure

```
FixMyCampus/
│
├── 📁 src/main/java/                    # Java Source Code
│   ├── 📁 controller/                   # Servlet Controllers
│   │   ├── LoginServlet.java            # User authentication
│   │   ├── LogoutServlet.java           # Session termination
│   │   ├── RegisterServlet.java         # User registration
│   │   ├── ReportIssueServlet.java      # Issue submission
│   │   ├── UpdateStatusServlet.java     # Admin status updates
│   │   └── ViewIssueServlet.java        # Issue retrieval
│   │
│   ├── 📁 model/                        # Data Models (POJOs)
│   │   ├── User.java                    # User entity (id, name, email, password, role)
│   │   └── Issue.java                   # Issue entity (id, title, description, location, status)
│   │
│   ├── 📁 dao/                          # Data Access Objects
│   │   ├── UserDAO.java                 # User database operations
│   │   └── IssueDAO.java                # Issue database operations
│   │
│   └── 📁 util/                         # Utility Classes
│       └── [Shared utilities/helpers]
│
├── 📁 src/main/webapp/                  # Web Application Resources
│   ├── 📁 css/                          # Stylesheets
│   │   └── style.css                    # Global styles
│   │
│   ├── 📁 WEB-INF/                      # Configuration Files
│   │   └── web.xml                      # Servlet mapping & configuration
│   │
│   ├── 📁 META-INF/                     # Metadata
│   │
│   ├── index.jsp                        # Landing/Home page
│   ├── login.jsp                        # User login form
│   ├── register.jsp                     # User registration form
│   ├── studentDashboard.jsp             # Student home dashboard
│   ├── adminDashboard.jsp               # Admin management panel
│   ├── reportIssue.jsp                  # Issue reporting form
│   ├── viewIssues.jsp                   # Issue listing page
│   └── manageIssues.jsp                 # Admin issue management
│
├── .project                             # Eclipse project configuration
├── .classpath                           # Eclipse classpath setup
└── README.md                            # This file
```

## 📦 Technology Stack

| Layer | Technology | Purpose |
|-------|-----------|---------|
| **Backend** | Java (Servlets) | Server-side request handling |
| **Frontend** | JSP (Java Server Pages) | Dynamic web page generation |
| **Styling** | CSS | User interface design |
| **Database** | Relational DB (MySQL/PostgreSQL) | Data persistence |
| **Architecture** | MVC Pattern | Model-View-Controller design |
| **Server** | Apache Tomcat | Java web server container |

## 🔑 Core Components

### 1. **Models (Data Entities)**

#### User Model
```java
- id: int (Primary Key)
- name: String
- email: String
- password: String
- role: String (Student/Admin)
```

#### Issue Model
```java
- id: int (Primary Key)
- title: String
- description: String
- location: String (Classroom, Corridor, Cafeteria, etc.)
- department: String (Maintenance, Electrical, Plumbing, etc.)
- status: String (Pending, In Progress, Resolved)
- studentId: int (Foreign Key - Issue Reporter)
```

### 2. **Controllers (Servlets)**

| Servlet | Purpose | Access |
|---------|---------|--------|
| **LoginServlet** | Authenticate users and create sessions | Public |
| **LogoutServlet** | Terminate user sessions | Authenticated |
| **RegisterServlet** | Create new student accounts | Public |
| **ReportIssueServlet** | Submit new issues (Students only) | Student |
| **ViewIssueServlet** | Retrieve issues with filters | Both |
| **UpdateStatusServlet** | Change issue status (Admins only) | Admin |

### 3. **Data Access Objects (DAOs)**

- **UserDAO**: Handle user registration, login verification, profile management
- **IssueDAO**: Manage CRUD operations for issues, status updates, filtering

### 4. **Views (JSP Pages)**

| Page | Purpose | Access |
|------|---------|--------|
| `index.jsp` | Landing page with login/register options | Public |
| `login.jsp` | User authentication form | Public |
| `register.jsp` | New account creation form | Public |
| `studentDashboard.jsp` | Student home - view their issues | Student |
| `adminDashboard.jsp` | Admin home - manage all issues | Admin |
| `reportIssue.jsp` | Issue submission form | Student |
| `viewIssues.jsp` | List all issues with filters | Both |
| `manageIssues.jsp` | Admin panel for status updates | Admin |

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher
- **Apache Tomcat** 8 or higher
- **Database Server** (MySQL 5.7+ or PostgreSQL 10+)
- **IDE** (Eclipse, IntelliJ IDEA, or VS Code)
- **Maven** (optional, for dependency management)

### Step 1: Clone Repository

```bash
git clone https://github.com/FIX-MY-CAMPUS/FixMyCampus.git
cd FixMyCampus
```

### Step 2: Database Setup

#### Create Database
```sql
CREATE DATABASE fixmycampus;
USE fixmycampus;
```

#### Create Tables
```sql
-- Users Table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('student', 'admin') DEFAULT 'student',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Issues Table
CREATE TABLE issues (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    status ENUM('Pending', 'In Progress', 'Resolved') DEFAULT 'Pending',
    student_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Insert Sample Admin User
INSERT INTO users (name, email, password, role) 
VALUES ('Admin User', 'admin@fixmycampus.com', 'admin123', 'admin');
```

### Step 3: Configure Database Connection

Update database connection details in your DAO classes or create a configuration file:

```java
// Database Configuration
String URL = "jdbc:mysql://localhost:3306/fixmycampus";
String USER = "root";
String PASSWORD = "your_password";
String DRIVER = "com.mysql.cj.jdbc.Driver";
```

### Step 4: Build & Deploy

#### Using Eclipse
1. Right-click project → **Run As** → **Run on Server**
2. Select Apache Tomcat
3. Application runs on `http://localhost:8080/FixMyCampus`

#### Using Maven
```bash
mvn clean install
mvn tomcat:deploy
```

#### Manual Deployment
1. Build WAR file
2. Copy to Tomcat's `webapps/` directory
3. Restart Tomcat server

### Step 5: Access Application

- **Home**: `http://localhost:8080/FixMyCampus/`
- **Admin Login**: Email: `admin@fixmycampus.com`, Password: `admin123`
- **Student Registration**: Create new account via register page

## 📖 User Workflows

### Student Workflow

```
Register → Login → View Dashboard → Report Issue → Track Status → View Resolved Issues
```

**Steps:**
1. Navigate to home page
2. Click "Register" and create account
3. Login with credentials
4. Click "Report Issue"
5. Fill issue details (title, description, location, department)
6. Submit and track progress
7. View issue status on dashboard

### Admin Workflow

```
Login → Admin Dashboard → View All Issues → Update Status → Track Resolution Rate
```

**Steps:**
1. Login with admin credentials
2. Access admin dashboard
3. View all reported issues in a table
4. Click on issues to view details
5. Update status (Pending → In Progress → Resolved)
6. Assign to departments as needed
7. Monitor resolution metrics

## 🔒 Security Features

| Feature | Implementation |
|---------|---|
| **Authentication** | Session-based login with password storage |
| **Authorization** | Role-based access control (Student/Admin) |
| **Session Management** | HttpSession for user tracking |
| **Input Validation** | Server-side form validation |
| **SQL Safety** | Parameterized queries (prepared statements) |
| **Data Persistence** | Encrypted password storage recommended |

## 📋 API-like Endpoints (Servlet Mappings)

| Method | URL | Purpose | Access |
|--------|-----|---------|--------|
| GET | `/` | Home page | Public |
| GET | `/login` | Login form | Public |
| POST | `/login` | Authenticate user | Public |
| GET | `/register` | Registration form | Public |
| POST | `/register` | Create account | Public |
| GET | `/logout` | End session | Authenticated |
| GET | `/studentDashboard` | Student home | Student |
| GET | `/adminDashboard` | Admin home | Admin |
| GET | `/reportIssue` | Report form | Student |
| POST | `/reportIssue` | Submit issue | Student |
| GET | `/viewIssues` | List issues | Both |
| POST | `/updateStatus` | Change status | Admin |

## 🧪 Testing the Application

### Test Users

**Admin Account**
```
Email: admin@fixmycampus.com
Password: admin123
Role: Admin
```

**Test Student Account**
```
Email: student@example.com
Password: pass123
Role: Student
```

### Testing Scenarios

1. **User Registration**
   - Register with valid email
   - Try duplicate email (should fail)
   - Test password validation

2. **Login Flow**
   - Login as admin → redirects to admin dashboard
   - Login as student → redirects to student dashboard
   - Invalid credentials → redirect to login

3. **Issue Reporting**
   - Student reports issue with all fields
   - Verify issue appears in admin dashboard
   - Check default status is "Pending"

4. **Status Updates**
   - Admin updates issue status
   - Verify student sees updated status
   - Track issue through all statuses

5. **Data Filtering**
   - Filter issues by location
   - Filter by department
   - Filter by status

## 📊 Database Schema

### Users Table
```
┌─────────┬──────────────┬─────────┐
│ Column  │ Type         │ Notes   │
├─────────┼──────────────┼─────────┤
│ id      │ INT PRIMARY  │ Auto    │
│ name    │ VARCHAR(100) │ Required│
│ email   │ VARCHAR(100) │ Unique  │
│ password│ VARCHAR(255) │ Required│
│ role    │ ENUM         │ Student/│
│         │              │ Admin   │
└─────────┴──────────────┴─────────┘
```

### Issues Table
```
┌──────────────┬────────────────────┬─────────┐
│ Column       │ Type               │ Notes   │
├──────────────┼────────────────────┼─────────┤
│ id           │ INT PRIMARY        │ Auto    │
│ title        │ VARCHAR(200)       │ Required│
│ description  │ TEXT               │ Required│
│ location     │ VARCHAR(100)       │ Required│
│ department   │ VARCHAR(100)       │ Required│
│ status       │ ENUM (3 values)    │ Default │
│ student_id   │ INT FOREIGN        │ Reference│
│ created_at   │ TIMESTAMP          │ Auto    │
│ updated_at   │ TIMESTAMP          │ Auto    │
└──────────────┴────────────────────┴─────────┘
```

## 🎨 UI Components

### Navigation
- Home link on all pages
- Logout button (when logged in)
- Role-based menu items

### Forms
- Input validation
- Error/success messages
- Responsive design

### Tables
- Issue listing with pagination
- Sortable columns
- Status badges (color-coded)
- Edit/Delete actions

## 🐛 Common Issues & Solutions

| Problem | Solution |
|---------|----------|
| Database connection fails | Check connection string and credentials |
| 404 error on servlet | Verify mapping in web.xml |
| Session not working | Check servlet import of HttpSession |
| CSS not loading | Verify file path in JSP |
| Login redirects to login | Check UserDAO.login() implementation |

## 📈 Future Enhancements

- [ ] Email notifications for issue updates
- [ ] Issue priority levels (Low/Medium/High/Urgent)
- [ ] Image upload for issues
- [ ] Department-wise analytics dashboard
- [ ] Mobile app version
- [ ] Real-time notifications (WebSocket)
- [ ] Advanced filtering & search
- [ ] Issue resolution SLA tracking
- [ ] User rating/feedback system
- [ ] Automated email notifications
- [ ] Export reports to PDF/Excel

## 🤝 Contributing

We welcome contributions! Here's how:

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/new-feature`
3. **Commit** changes: `git commit -m 'Add new feature'`
4. **Push** to branch: `git push origin feature/new-feature`
5. **Open** a Pull Request

### Contribution Guidelines
- Follow Java naming conventions
- Write clean, documented code
- Test all functionality before submitting PR
- Update README if adding major features

## 📝 Coding Standards

- **Class Names**: PascalCase (e.g., `LoginServlet`)
- **Method Names**: camelCase (e.g., `handleRequest()`)
- **Variable Names**: camelCase (e.g., `userId`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DB_URL`)
- **Comments**: Describe "why" not "what"

## 📄 License

This project is open source and available under the MIT License.

## 📞 Support & Contact

- **Organization**: [FIX-MY-CAMPUS](https://github.com/FIX-MY-CAMPUS)
- **Issues**: [Report a Bug](https://github.com/FIX-MY-CAMPUS/FixMyCampus/issues)
- **Discussions**: [Join Discussion](https://github.com/FIX-MY-CAMPUS/FixMyCampus/discussions)

## 📚 Learning Resources

- [Java Servlet Documentation](https://docs.oracle.com/javaee/7/api/javax/servlet/package-summary.html)
- [JSP Tutorial](https://www.oracle.com/technical-resources/articles/java/javaee-jsp-overview.html)
- [JDBC Database Access](https://docs.oracle.com/javase/tutorial/jdbc/)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)
- [MVC Design Pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)

## 📊 Project Statistics

| Metric | Value |
|--------|-------|
| **Total Files** | 20+ |
| **Java Classes** | 8 |
| **JSP Pages** | 8 |
| **Database Tables** | 2 |
| **LOC** | ~1000+ |

## 🎯 Roadmap

### Phase 1 (Current) ✅
- Basic issue reporting
- Admin dashboard
- Status tracking

### Phase 2 (Planned)
- Email notifications
- Advanced search filters
- Analytics dashboard

### Phase 3 (Future)
- Mobile app
- Real-time updates
- Department portals

## ⭐ Features Comparison

| Feature | FixMyCampus | Traditional System |
|---------|-------------|-------------------|
| Easy Issue Reporting | ✅ | ❌ |
| Real-time Status Tracking | ✅ | ❌ |
| Admin Dashboard | ✅ | ❌ |
| 24/7 Availability | ✅ | ❌ |
| Transparent Process | ✅ | ❌ |
| Mobile Friendly | ⏳ | N/A |

---

<div align="center">

**Making Campus Better, One Issue at a Time** 🏫

[⭐ Star this repo](https://github.com/FIX-MY-CAMPUS/FixMyCampus) if you find it useful!

---

Built with ❤️ by the FIX-MY-CAMPUS Team

</div>
