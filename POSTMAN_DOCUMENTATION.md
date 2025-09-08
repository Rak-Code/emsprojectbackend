# Employee Management System - Postman API Documentation

## Base URL
```
http://localhost:8080
```

## Authentication
Most endpoints require JWT authentication. Include the token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

---

## 1. Authentication APIs

### 1.1 User Registration
**Endpoint:** `POST /api/auth/register`

**Request Body:**
```json
{
  "username": "john_doe",
  "email": "john.doe@company.com",
  "password": "password123",
  "role": "EMPLOYEE"
}
```

**Response:**
```json
"User registered successfully"
```

### 1.2 User Login
**Endpoint:** `POST /api/auth/login`

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
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "john_doe",
  "role": "EMPLOYEE"
}
```

---

## 2. Employee Management APIs

### 2.1 Create Employee
**Endpoint:** `POST /api/employees`

**Request Body:**
```json
{
  "user": {
    "id": 1
  },
  "name": "John Doe",
  "email": "john.doe@company.com",
  "phone": "+1234567890",
  "address": "123 Main St, City, State",
  "department": {
    "id": 1
  },
  "role": {
    "id": 1
  },
  "joiningDate": "2023-01-15"
}
```

### 2.2 Get All Employees
**Endpoint:** `GET /api/employees`

**Response:**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@company.com",
    "status": "ACTIVE",
    "department": {
      "id": 1,
      "name": "IT"
    }
  }
]
```

### 2.3 Get Employee by ID
**Endpoint:** `GET /api/employees/{id}`

### 2.4 Get Employees by Department
**Endpoint:** `GET /api/employees/department/{deptId}`

### 2.5 Get Employees by Role
**Endpoint:** `GET /api/employees/role/{roleId}`

### 2.6 Update Employee
**Endpoint:** `PUT /api/employees/{id}`

### 2.7 Update Employee Status
**Endpoint:** `PATCH /api/employees/{id}/status?status=INACTIVE`

### 2.8 Delete Employee
**Endpoint:** `DELETE /api/employees/{id}`

---

## 3. Attendance Management APIs

### 3.1 Punch In
**Endpoint:** `POST /api/attendance/punch-in/{employeeId}`

**Response:**
```json
{
  "attendanceId": 1,
  "employeeId": 123,
  "date": "2023-09-08",
  "punchInTime": "2023-09-08T09:00:00",
  "status": "PRESENT"
}
```

### 3.2 Punch Out
**Endpoint:** `POST /api/attendance/punch-out/{employeeId}`

### 3.3 Mark Leave
**Endpoint:** `POST /api/attendance/mark-leave?employeeId=123&date=2023-09-09`

### 3.4 Get Attendance by ID
**Endpoint:** `GET /api/attendance/{id}`

### 3.5 Get Employee Attendance
**Endpoint:** `GET /api/attendance/employee/{employeeId}`

### 3.6 Get Attendance by Date Range
**Endpoint:** `GET /api/attendance/employee/{employeeId}/range?startDate=2023-09-01&endDate=2023-09-30`

### 3.7 Get Monthly Attendance
**Endpoint:** `GET /api/attendance/employee/{employeeId}/monthly?year=2023&month=9`

### 3.8 Get Daily Attendance
**Endpoint:** `GET /api/attendance/daily/2023-09-08`

### 3.9 Get Attendance Report
**Endpoint:** `GET /api/attendance/report/employee/{employeeId}?year=2023&month=9`

### 3.10 Get Attendance Statistics
**Endpoint:** `GET /api/attendance/stats/employee/{employeeId}?year=2023&month=9`

**Response:**
```json
{
  "totalDays": 22,
  "presentDays": 20,
  "leaveDays": 1,
  "absentDays": 1
}
```

### 3.11 Update Attendance
**Endpoint:** `PUT /api/attendance/{id}`

### 3.12 Delete Attendance
**Endpoint:** `DELETE /api/attendance/{id}`

---

## 4. Leave Request Management APIs

### 4.1 Apply for Leave
**Endpoint:** `POST /api/leave-requests`

**Request Body:**
```json
{
  "employee": {
    "id": 1
  },
  "leaveType": {
    "id": 1
  },
  "startDate": "2023-09-15",
  "endDate": "2023-09-16",
  "reason": "Family vacation"
}
```

### 4.2 Get All Leave Requests
**Endpoint:** `GET /api/leave-requests`

### 4.3 Get Leave Requests by Employee
**Endpoint:** `GET /api/leave-requests/employee/{employeeId}`

### 4.4 Get Pending Leave Requests
**Endpoint:** `GET /api/leave-requests/pending`

### 4.5 Get Leave Requests by Status
**Endpoint:** `GET /api/leave-requests/status/{status}`

*(status can be: PENDING, APPROVED, REJECTED)*

### 4.6 Approve Leave
**Endpoint:** `PUT /api/leave-requests/{id}/approve/{approverId}`

### 4.7 Reject Leave
**Endpoint:** `PUT /api/leave-requests/{id}/reject/{approverId}`

### 4.8 Delete Leave Request
**Endpoint:** `DELETE /api/leave-requests/{id}`

---

## 5. Department Management APIs

### 5.1 Create Department
**Endpoint:** `POST /api/departments`

**Request Body:**
```json
{
  "name": "Human Resources"
}
```

### 5.2 Get All Departments
**Endpoint:** `GET /api/departments`

### 5.3 Get Department by ID
**Endpoint:** `GET /api/departments/{id}`

### 5.4 Update Department
**Endpoint:** `PUT /api/departments/{id}`

### 5.5 Delete Department
**Endpoint:** `DELETE /api/departments/{id}`

---

## 6. Role Management APIs

### 6.1 Create Role
**Endpoint:** `POST /api/roles`

**Request Body:**
```json
{
  "roleName": "Software Engineer"
}
```

### 6.2 Get All Roles
**Endpoint:** `GET /api/roles`

### 6.3 Get Role by ID
**Endpoint:** `GET /api/roles/{id}`

### 6.4 Update Role
**Endpoint:** `PUT /api/roles/{id}`

### 6.5 Delete Role
**Endpoint:** `DELETE /api/roles/{id}`

---

## 7. Leave Type Management APIs

### 7.1 Create Leave Type
**Endpoint:** `POST /api/leave-types`

**Request Body:**
```json
{
  "typeName": "Annual Leave",
  "description": "Paid annual leave",
  "maxDaysPerYear": 25,
  "isPaid": true
}
```

### 7.2 Get All Leave Types
**Endpoint:** `GET /api/leave-types`

### 7.3 Get Leave Type by ID
**Endpoint:** `GET /api/leave-types/{id}`

### 7.4 Update Leave Type
**Endpoint:** `PUT /api/leave-types/{id}`

### 7.5 Delete Leave Type
**Endpoint:** `DELETE /api/leave-types/{id}`

---

## 8. Salary Management APIs

### 8.1 Create Salary Record
**Endpoint:** `POST /api/salaries`

**Request Body:**
```json
{
  "employee": {
    "id": 1
  },
  "baseSalary": 50000.00,
  "allowances": 5000.00,
  "deductions": 1000.00,
  "effectiveDate": "2023-01-01",
  "isActive": true
}
```

### 8.2 Get All Salaries
**Endpoint:** `GET /api/salaries`

### 8.3 Get Salary by ID
**Endpoint:** `GET /api/salaries/{id}`

### 8.4 Get Salaries by Employee
**Endpoint:** `GET /api/salaries/employee/{employeeId}`

### 8.5 Get Active Salaries
**Endpoint:** `GET /api/salaries/active`

### 8.6 Calculate Salary
**Endpoint:** `GET /api/salaries/calculate/{employeeId}`

### 8.7 Update Salary
**Endpoint:** `PUT /api/salaries/{id}`

### 8.8 Deactivate Salary
**Endpoint:** `PUT /api/salaries/{id}/deactivate`

### 8.9 Delete Salary
**Endpoint:** `DELETE /api/salaries/{id}`

---

## 9. User Management APIs

### 9.1 Create User
**Endpoint:** `POST /api/users`

**Request Body:**
```json
{
  "username": "jane_smith",
  "email": "jane.smith@company.com",
  "password": "password123",
  "role": "EMPLOYEE",
  "isActive": true
}
```

### 9.2 Get All Users
**Endpoint:** `GET /api/users`

### 9.3 Get User by ID
**Endpoint:** `GET /api/users/{id}`

### 9.4 Get User by Username
**Endpoint:** `GET /api/users/username/{username}`

### 9.5 Get User by Email
**Endpoint:** `GET /api/users/email/{email}`

### 9.6 Update User
**Endpoint:** `PUT /api/users/{id}`

### 9.7 Delete User
**Endpoint:** `DELETE /api/users/{id}`

---

## 10. Email Notification APIs

### 10.1 Create Notification
**Endpoint:** `POST /api/email-notifications`

**Request Body:**
```json
{
  "recipientEmail": "employee@company.com",
  "subject": "Leave Approved",
  "message": "Your leave request has been approved.",
  "sentStatus": "PENDING"
}
```

### 10.2 Get All Notifications
**Endpoint:** `GET /api/email-notifications`

### 10.3 Get Notification by ID
**Endpoint:** `GET /api/email-notifications/{id}`

### 10.4 Get Notifications by Recipient
**Endpoint:** `GET /api/email-notifications/recipient/{email}`

### 10.5 Delete Notification
**Endpoint:** `DELETE /api/email-notifications/{id}`

---

## Error Response Format

All error responses follow this format:
```json
{
  "timestamp": "2023-09-08T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Error description",
  "path": "/api/endpoint"
}
```

## Common HTTP Status Codes

- `200 OK` - Request successful
- `201 Created` - Resource created successfully
- `204 No Content` - Request successful, no content returned
- `400 Bad Request` - Invalid request data
- `401 Unauthorized` - Authentication required
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

## Testing Steps

1. **Start the application** and ensure it's running on port 8080
2. **Import this collection** into Postman
3. **Register a user** using the registration endpoint
4. **Login** to get JWT token
5. **Set Authorization header** with Bearer token for authenticated requests
6. **Test CRUD operations** for each module
7. **Test business logic** like attendance punching, leave requests, salary calculations

## Sample Test Flow

1. Register Admin User → Login → Get Token
2. Create Department → Create Role → Create User
3. Create Employee with Department and Role
4. Test Attendance: Punch In → Punch Out
5. Test Leave: Create Leave Type → Apply for Leave
6. Test Salary: Create Salary Record → Calculate Salary
7. Test Reports: Get Attendance Stats, Leave Requests by Status
