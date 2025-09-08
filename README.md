# Employee Management System (EMS) Backend

A comprehensive Spring Boot-based REST API for managing employee data, attendance, leave requests, and organizational structure.

## 🚀 Features

- **User Authentication**: JWT-based secure authentication
- **Employee Management**: Complete CRUD operations for employee records
- **Department Management**: Organize employees by departments
- **Role Management**: Define and assign job roles
- **Attendance Tracking**: Clock in/out system with automatic hour calculation
- **Leave Management**: Apply, approve/reject leave requests
- **Salary Management**: Track and manage employee salaries
- **Email Notifications**: System-generated email logs
- **Security**: Role-based access control (ADMIN, MANAGER, EMPLOYEE)
- **API Documentation**: Interactive Swagger UI

## 📋 Technology Stack

- **Framework**: Spring Boot 3.5.5
- **Language**: Java 24
- **Database**: MySQL 8.0
- **Security**: Spring Security + JWT
- **ORM**: Spring Data JPA
- **Documentation**: SpringDoc OpenAPI 2.2.0
- **Build Tool**: Maven
- **Additional**: BCrypt Password Encoding

## 🛠️ Prerequisites

- Java 24
- Maven 3.x
- MySQL 8.0
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## ⚙️ Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd backend
   ```

2. **Set up MySQL Database**
   ```sql
   CREATE DATABASE employee_management_system;
   ```

3. **Configure Database**
   Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/employee_management_system
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   ```

4. **Build and Run**
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```

5. **Access Application**
   - **Base URL**: `http://localhost:8080`
   - **Swagger UI**: `http://localhost:8080/swagger-ui.html`
   - **API Docs**: `http://localhost:8080/v3/api-docs`

## 🔐 Authentication

All API endpoints (except registration and login) require JWT authentication.

### Headers
```
Authorization: Bearer <your-jwt-token>
Content-Type: application/json
```

## 📚 API Endpoints

### 🔑 Authentication

#### Register User
```http
POST /api/auth/register
```

**Request Body:**
```json
{
    "username": "john_doe",
    "email": "john@company.com",
    "password": "password123",
    "role": "EMPLOYEE"
}
```

**Response:**
```
"User registered successfully"
```

**Roles:** `ADMIN`, `MANAGER`, `EMPLOYEE`

---

#### Login
```http
POST /api/auth/login
```

**Request Body:**
```json
{
    "usernameOrEmail": "john_doe",
    "password": "password123"
}
```

**Response:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "username": "john_doe",
    "role": "EMPLOYEE"
}
```

---

### 🏢 Department Management (Admin Only)

#### Create Department
```http
POST /api/departments
```

**Request Body:**
```json
{
    "name": "Information Technology"
}
```

#### Get All Departments
```http
GET /api/departments
```

**Response:**
```json
[
    {
        "id": 1,
        "name": "Information Technology",
        "createdAt": "2025-01-15T10:30:00",
        "updatedAt": "2025-01-15T10:30:00"
    }
]
```

#### Get Department by ID
```http
GET /api/departments/{id}
```

#### Delete Department
```http
DELETE /api/departments/{id}
```

---

### 👤 Role Management (Admin Only)

#### Create Role
```http
POST /api/roles
```

**Request Body:**
```json
{
    "roleName": "Senior Developer"
}
```

#### Get All Roles
```http
GET /api/roles
```

#### Get Role by ID
```http
GET /api/roles/{id}
```

#### Delete Role
```http
DELETE /api/roles/{id}
```

---

### 👥 Employee Management (Admin/Manager)

#### Create Employee
```http
POST /api/employees
```

**Request Body:**
```json
{
    "user": {
        "id": 1
    },
    "name": "John Doe",
    "email": "john@company.com",
    "phone": "+1234567890",
    "address": "123 Main St, City, State",
    "department": {
        "id": 1
    },
    "role": {
        "id": 1
    },
    "joiningDate": "2025-01-15"
}
```

#### Get All Employees
```http
GET /api/employees
```

#### Get Employee by ID
```http
GET /api/employees/{id}
```

#### Update Employee
```http
PUT /api/employees/{id}
```

**Request Body:** (Same as Create Employee)

#### Update Employee Status
```http
PATCH /api/employees/{id}/status?status=INACTIVE
```

**Status Values:** `ACTIVE`, `INACTIVE`

#### Get Employees by Department
```http
GET /api/employees/department/{deptId}
```

#### Get Employees by Role
```http
GET /api/employees/role/{roleId}
```

#### Delete Employee
```http
DELETE /api/employees/{id}
```

---

### ⏰ Attendance Management (All Authenticated Users)

#### Punch In
```http
POST /api/attendance/punch-in/{employeeId}
```

**Response:**
```json
{
    "attendanceId": 1,
    "employeeId": 1,
    "date": "2025-01-15",
    "punchInTime": "2025-01-15T09:00:00",
    "punchOutTime": null,
    "totalHours": null,
    "status": "PRESENT"
}
```

#### Punch Out
```http
POST /api/attendance/punch-out/{employeeId}
```

#### Mark Leave
```http
POST /api/attendance/mark-leave?employeeId=1&date=2025-01-15
```

#### Get Attendance by Employee and Date
```http
GET /api/attendance/employee/{employeeId}/date/{date}
```

#### Get All Employee Attendance
```http
GET /api/attendance/employee/{employeeId}
```

#### Get Attendance by Date Range
```http
GET /api/attendance/employee/{employeeId}/range?startDate=2025-01-01&endDate=2025-01-31
```

#### Get Monthly Attendance
```http
GET /api/attendance/employee/{employeeId}/monthly?year=2025&month=1
```

#### Get Daily Attendance (All Employees)
```http
GET /api/attendance/daily/{date}
```

#### Get All Attendance Records
```http
GET /api/attendance/all
```

#### Update Attendance
```http
PUT /api/attendance/{id}
```

**Request Body:**
```json
{
    "punchInTime": "2025-01-15T09:00:00",
    "punchOutTime": "2025-01-15T17:30:00",
    "status": "PRESENT"
}
```

**Status Values:** `PRESENT`, `ABSENT`, `LEAVE`

#### Delete Attendance
```http
DELETE /api/attendance/{id}
```

---

### 📝 Leave Type Management (Admin Only)

#### Create Leave Type
```http
POST /api/leave-types
```

**Request Body:**
```json
{
    "typeName": "Annual Leave",
    "description": "Yearly vacation leave",
    "maxDaysPerYear": 20,
    "isPaid": true
}
```

#### Get All Leave Types
```http
GET /api/leave-types
```

#### Get Leave Type by ID
```http
GET /api/leave-types/{id}
```

#### Update Leave Type
```http
PUT /api/leave-types/{id}
```

#### Delete Leave Type
```http
DELETE /api/leave-types/{id}
```

---

### 🏖️ Leave Request Management (All Authenticated Users)

#### Apply for Leave
```http
POST /api/leave-requests
```

**Request Body:**
```json
{
    "employee": {
        "id": 1
    },
    "leaveType": {
        "id": 1
    },
    "startDate": "2025-02-01",
    "endDate": "2025-02-05",
    "reason": "Family vacation"
}
```

#### Get All Leave Requests
```http
GET /api/leave-requests
```

#### Get Leave Requests by Employee
```http
GET /api/leave-requests/employee/{employeeId}
```

#### Approve Leave Request
```http
PUT /api/leave-requests/{id}/approve/{approverId}
```

#### Reject Leave Request
```http
PUT /api/leave-requests/{id}/reject/{approverId}
```

#### Delete Leave Request
```http
DELETE /api/leave-requests/{id}
```

---

### 💰 Salary Management (Admin Only)

#### Create Salary Record
```http
POST /api/salaries
```

**Request Body:**
```json
{
    "employee": {
        "id": 1
    },
    "baseSalary": 75000.00,
    "allowances": 5000.00,
    "deductions": 2000.00,
    "netSalary": 78000.00,
    "effectiveDate": "2025-01-01",
    "isActive": true
}
```

#### Get All Salaries
```http
GET /api/salaries
```

#### Get Salary by ID
```http
GET /api/salaries/{id}
```

#### Get Salaries by Employee
```http
GET /api/salaries/employee/{employeeId}
```

#### Update Salary
```http
PUT /api/salaries/{id}
```

#### Deactivate Salary
```http
PUT /api/salaries/{id}/deactivate
```

#### Delete Salary
```http
DELETE /api/salaries/{id}
```

---

### 📧 Email Notification Management (Admin Only)

#### Create Email Notification Log
```http
POST /api/email-notifications
```

**Request Body:**
```json
{
    "recipientEmail": "john@company.com",
    "subject": "Leave Request Approved",
    "body": "Your leave request has been approved.",
    "relatedEntityType": "LEAVE_REQUEST",
    "relatedEntityId": 1
}
```

**Related Entity Types:** `LEAVE_REQUEST`, `SALARY`, `ATTENDANCE`, `OTHER`

#### Get All Notifications
```http
GET /api/email-notifications
```

#### Get Notification by ID
```http
GET /api/email-notifications/{id}
```

#### Get Notifications by Recipient
```http
GET /api/email-notifications/recipient/{email}
```

#### Delete Notification
```http
DELETE /api/email-notifications/{id}
```

---

## 🧪 Testing Guide

### Step 1: Setup Admin Account
```bash
# Register Admin User
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "email": "admin@company.com",
    "password": "admin123",
    "role": "ADMIN"
  }'

# Login as Admin
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "usernameOrEmail": "admin",
    "password": "admin123"
  }'
```

### Step 2: Create Master Data
```bash
# Create Department
curl -X POST http://localhost:8080/api/departments \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Information Technology"
  }'

# Create Role
curl -X POST http://localhost:8080/api/roles \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
    "roleName": "Software Developer"
  }'

# Create Leave Type
curl -X POST http://localhost:8080/api/leave-types \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
    "typeName": "Annual Leave",
    "description": "Yearly vacation",
    "maxDaysPerYear": 20,
    "isPaid": true
  }'
```

### Step 3: Create Employee
```bash
# Register Employee User
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@company.com",
    "password": "password123",
    "role": "EMPLOYEE"
  }'

# Create Employee Profile
curl -X POST http://localhost:8080/api/employees \
  -H "Authorization: Bearer ADMIN_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 2},
    "name": "John Doe",
    "email": "john@company.com",
    "phone": "+1234567890",
    "address": "123 Main St",
    "department": {"id": 1},
    "role": {"id": 1},
    "joiningDate": "2025-01-15"
  }'
```

### Step 4: Test Employee Operations
```bash
# Login as Employee
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "usernameOrEmail": "john_doe",
    "password": "password123"
  }'

# Punch In
curl -X POST http://localhost:8080/api/attendance/punch-in/1 \
  -H "Authorization: Bearer EMPLOYEE_TOKEN_HERE"

# Submit Leave Request
curl -X POST http://localhost:8080/api/leave-requests \
  -H "Authorization: Bearer EMPLOYEE_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
    "employee": {"id": 1},
    "leaveType": {"id": 1},
    "startDate": "2025-02-01",
    "endDate": "2025-02-05",
    "reason": "Family vacation"
  }'
```

## 🗃️ Database Schema

The application automatically creates the following tables:

| Table | Description |
|-------|-------------|
| `users` | User authentication data |
| `employees` | Employee profiles and details |
| `departments` | Organizational departments |
| `roles` | Job roles and positions |
| `attendance` | Daily attendance records |
| `leave_types` | Types of leave available |
| `leave_requests` | Leave applications |
| `salaries` | Employee salary records |
| `email_notifications` | Email notification logs |

## 🔒 Security & Permissions

| Role | Permissions |
|------|------------|
| **ADMIN** | Full access to all endpoints |
| **MANAGER** | Employee management, attendance, leave requests |
| **EMPLOYEE** | Own attendance, leave requests, profile view |

## 📊 Status Codes

| Code | Description |
|------|-------------|
| 200 | Success |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |

## 🚨 Error Handling

All errors return a structured response:

```json
{
    "timestamp": "2025-01-15T10:30:00",
    "message": "Error description",
    "details": "Request details",
    "status": 400
}
```

## 🔄 Future Enhancements

- [ ] File upload for employee documents
- [ ] Advanced reporting and analytics  
- [ ] Email service integration
- [ ] Mobile push notifications
- [ ] Advanced search and filtering
- [ ] Bulk operations
- [ ] Data export functionality
- [ ] Performance monitoring
- [ ] Rate limiting
- [ ] Caching layer

## 📞 Support

For issues and questions:
- Create an issue in the repository
- Contact the development team
- Check the API documentation at `/swagger-ui.html`

## 📄 License

This project is licensed under the MIT License.