# API Testing Flow (Postman) — Employee Management System

This guide provides a simple, step-by-step flow to test the EMS backend APIs from scratch in Postman. It includes the order of testing, the API URL endpoints, and sample JSON bodies. Use it as a checklist.

Base URL:
- http://localhost:8080 (set as Postman environment variable: baseUrl)

Common Headers:
- Content-Type: application/json
- Authorization: Bearer {{adminToken}} or {{employeeToken}} or {{managerToken}}

Recommended Postman Environment Variables:
- baseUrl = http://localhost:8080
- adminToken = (from Admin login)
- employeeToken = (from Employee login)
- managerToken = (optional, from Manager login)
- departmentId, roleId, leaveTypeId
- employeeUserId, managerUserId (from your system/DB if available)
- employeeId, managerEmployeeId
- attendanceId, leaveRequestId, salaryId, emailNotificationId

Note on IDs:
- When a POST returns an object with id, capture it into a variable in Postman (Tests tab) for later steps.
- For user IDs after registration: if the register API does not return the user id, obtain it via Swagger if available or from the database. In many setups, if Admin was the first user, subsequent registrations increment IDs (e.g., Admin=1, next user=2).

------------------------------------------------------------
1) Authentication (No token required)
------------------------------------------------------------

1.1 Register Admin
- Method: POST
- URL: {{baseUrl}}/api/auth/register
- Body:
{
  "username": "admin",
  "email": "admin@company.com",
  "password": "admin123",
  "role": "ADMIN"
}

1.2 Login as Admin
- Method: POST
- URL: {{baseUrl}}/api/auth/login
- Body:
{
  "usernameOrEmail": "admin",
  "password": "admin123"
}
- Action: Copy token from response to {{adminToken}}

(Optional) 1.3 Register Manager (to act as approver later)
- Method: POST
- URL: {{baseUrl}}/api/auth/register
- Body:
{
  "username": "manager",
  "email": "manager@company.com",
  "password": "manager123",
  "role": "MANAGER"
}

(Optional) 1.4 Login as Manager
- Method: POST
- URL: {{baseUrl}}/api/auth/login
- Body:
{
  "usernameOrEmail": "manager",
  "password": "manager123"
}
- Action: Copy token to {{managerToken}}

------------------------------------------------------------
2) Master Data (Admin only — requires {{adminToken}})
------------------------------------------------------------

2.1 Create Department
- Method: POST
- URL: {{baseUrl}}/api/departments
- Headers: Authorization: Bearer {{adminToken}}
- Body:
{
  "name": "Information Technology"
}
- Capture: departmentId

2.2 Create Role
- Method: POST
- URL: {{baseUrl}}/api/roles
- Headers: Authorization: Bearer {{adminToken}}
- Body:
{
  "roleName": "Software Developer"
}
- Capture: roleId

2.3 Create Leave Type
- Method: POST
- URL: {{baseUrl}}/api/leave-types
- Headers: Authorization: Bearer {{adminToken}}
- Body:
{
  "typeName": "Annual Leave",
  "description": "Yearly vacation leave",
  "maxDaysPerYear": 20,
  "isPaid": true
}
- Capture: leaveTypeId

2.4 Verify Master Data Reads
- GET {{baseUrl}}/api/departments
- GET {{baseUrl}}/api/roles
- GET {{baseUrl}}/api/leave-types

------------------------------------------------------------
3) Users and Employee Profiles
------------------------------------------------------------

3.1 Register Employee User (No token required)
- Method: POST
- URL: {{baseUrl}}/api/auth/register
- Body:
{
  "username": "john_doe",
  "email": "john@company.com",
  "password": "password123",
  "role": "EMPLOYEE"
}
- Note: Obtain this new user's id (employeeUserId) if available (DB/Swagger). Example: 2

3.2 Login as Employee
- Method: POST
- URL: {{baseUrl}}/api/auth/login
- Body:
{
  "usernameOrEmail": "john_doe",
  "password": "password123"
}
- Action: Copy token to {{employeeToken}}

(Optional) 3.3 Create Manager Employee Profile (Admin token)
- Method: POST
- URL: {{baseUrl}}/api/employees
- Headers: Authorization: Bearer {{adminToken}}
- Body:
{
  "user": { "id": {{managerUserId}} },
  "name": "Jane Manager",
  "email": "manager@company.com",
  "phone": "+1000000000",
  "address": "10 Manager St",
  "department": { "id": {{departmentId}} },
  "role": { "id": {{roleId}} },
  "joiningDate": "2025-01-10"
}
- Capture: managerEmployeeId

3.4 Create Employee Profile for john_doe (Admin token)
- Method: POST
- URL: {{baseUrl}}/api/employees
- Headers: Authorization: Bearer {{adminToken}}
- Body:
{
  "user": { "id": {{employeeUserId}} },
  "name": "John Doe",
  "email": "john@company.com",
  "phone": "+1234567890",
  "address": "123 Main St, City",
  "department": { "id": {{departmentId}} },
  "role": { "id": {{roleId}} },
  "joiningDate": "2025-01-15"
}
- Capture: employeeId

3.5 Verify Employee Reads (Admin or Manager token)
- GET {{baseUrl}}/api/employees
- GET {{baseUrl}}/api/employees/{{employeeId}}
- GET {{baseUrl}}/api/employees/department/{{departmentId}}
- GET {{baseUrl}}/api/employees/role/{{roleId}}

3.6 Update Employee (Admin or Manager)
- Method: PUT
- URL: {{baseUrl}}/api/employees/{{employeeId}}
- Body (example change address):
{
  "user": { "id": {{employeeUserId}} },
  "name": "John Doe",
  "email": "john@company.com",
  "phone": "+1234567890",
  "address": "456 Updated Ave, City",
  "department": { "id": {{departmentId}} },
  "role": { "id": {{roleId}} },
  "joiningDate": "2025-01-15"
}

3.7 Update Employee Status (Admin or Manager)
- Method: PATCH
- URL: {{baseUrl}}/api/employees/{{employeeId}}/status?status=ACTIVE

------------------------------------------------------------
4) Attendance (Employee token for own actions)
------------------------------------------------------------

4.1 Punch In (Employee)
- Method: POST
- URL: {{baseUrl}}/api/attendance/punch-in/{{employeeId}}
- Headers: Authorization: Bearer {{employeeToken}}
- Capture: attendanceId (from response)

4.2 Get Attendance by Employee and Date (Employee/Admin/Manager)
- Method: GET
- URL: {{baseUrl}}/api/attendance/employee/{{employeeId}}/date/2025-01-15

4.3 Punch Out (Employee)
- Method: POST
- URL: {{baseUrl}}/api/attendance/punch-out/{{employeeId}}
- Headers: Authorization: Bearer {{employeeToken}}

4.4 Get All Attendance for Employee
- GET {{baseUrl}}/api/attendance/employee/{{employeeId}}

4.5 Get Attendance by Date Range
- GET {{baseUrl}}/api/attendance/employee/{{employeeId}}/range?startDate=2025-01-01&endDate=2025-01-31

4.6 Get Monthly Attendance
- GET {{baseUrl}}/api/attendance/employee/{{employeeId}}/monthly?year=2025&month=1

4.7 Get Daily Attendance (All Employees) — Admin/Manager
- GET {{baseUrl}}/api/attendance/daily/2025-01-15

4.8 Get All Attendance Records — Admin/Manager
- GET {{baseUrl}}/api/attendance/all

4.9 Update Attendance (Admin/Manager)
- Method: PUT
- URL: {{baseUrl}}/api/attendance/{{attendanceId}}
- Body:
{
  "punchInTime": "2025-01-15T09:00:00",
  "punchOutTime": "2025-01-15T17:30:00",
  "status": "PRESENT"
}

4.10 Delete Attendance (Admin/Manager)
- Method: DELETE
- URL: {{baseUrl}}/api/attendance/{{attendanceId}}

------------------------------------------------------------
5) Leave Requests
------------------------------------------------------------

5.1 Apply for Leave (Employee)
- Method: POST
- URL: {{baseUrl}}/api/leave-requests
- Headers: Authorization: Bearer {{employeeToken}}
- Body:
{
  "employee": { "id": {{employeeId}} },
  "leaveType": { "id": {{leaveTypeId}} },
  "startDate": "2025-02-01",
  "endDate": "2025-02-05",
  "reason": "Family vacation"
}
- Capture: leaveRequestId

5.2 Get All Leave Requests (Admin/Manager)
- GET {{baseUrl}}/api/leave-requests
- Headers: Authorization: Bearer {{adminToken}} or {{managerToken}}

5.3 Get Leave Requests by Employee
- GET {{baseUrl}}/api/leave-requests/employee/{{employeeId}}

5.4 Approve Leave Request (Admin/Manager)
- Method: PUT
- URL: {{baseUrl}}/api/leave-requests/{{leaveRequestId}}/approve/{{managerEmployeeId}}
- Note: approverId should be a valid employeeId of an approver (e.g., Manager). If you didn’t create a manager employee, create one and capture managerEmployeeId.

5.5 Reject Leave Request (Admin/Manager)
- Method: PUT
- URL: {{baseUrl}}/api/leave-requests/{{leaveRequestId}}/reject/{{managerEmployeeId}}

5.6 Delete Leave Request (Admin)
- Method: DELETE
- URL: {{baseUrl}}/api/leave-requests/{{leaveRequestId}}

------------------------------------------------------------
6) Salaries (Admin only)
------------------------------------------------------------

6.1 Create Salary Record
- Method: POST
- URL: {{baseUrl}}/api/salaries
- Headers: Authorization: Bearer {{adminToken}}
- Body:
{
  "employee": { "id": {{employeeId}} },
  "baseSalary": 75000.00,
  "allowances": 5000.00,
  "deductions": 2000.00,
  "netSalary": 78000.00,
  "effectiveDate": "2025-01-01",
  "isActive": true
}
- Capture: salaryId

6.2 Get All Salaries
- GET {{baseUrl}}/api/salaries

6.3 Get Salary by ID
- GET {{baseUrl}}/api/salaries/{{salaryId}}

6.4 Get Salaries by Employee
- GET {{baseUrl}}/api/salaries/employee/{{employeeId}}

6.5 Update Salary
- Method: PUT
- URL: {{baseUrl}}/api/salaries/{{salaryId}}
- Body:
{
  "employee": { "id": {{employeeId}} },
  "baseSalary": 76000.00,
  "allowances": 5500.00,
  "deductions": 2200.00,
  "netSalary": 79300.00,
  "effectiveDate": "2025-02-01",
  "isActive": true
}

6.6 Deactivate Salary
- Method: PUT
- URL: {{baseUrl}}/api/salaries/{{salaryId}}/deactivate

6.7 Delete Salary
- Method: DELETE
- URL: {{baseUrl}}/api/salaries/{{salaryId}}

------------------------------------------------------------
7) Email Notifications (Admin only)
------------------------------------------------------------

7.1 Create Email Notification Log
- Method: POST
- URL: {{baseUrl}}/api/email-notifications
- Headers: Authorization: Bearer {{adminToken}}
- Body:
{
  "recipientEmail": "john@company.com",
  "subject": "Leave Request Approved",
  "body": "Your leave request has been approved.",
  "relatedEntityType": "LEAVE_REQUEST",
  "relatedEntityId": {{leaveRequestId}}
}
- Capture: emailNotificationId

7.2 Get All Notifications
- GET {{baseUrl}}/api/email-notifications

7.3 Get Notification by ID
- GET {{baseUrl}}/api/email-notifications/{{emailNotificationId}}

7.4 Get Notifications by Recipient
- GET {{baseUrl}}/api/email-notifications/recipient/john@company.com

7.5 Delete Notification
- Method: DELETE
- URL: {{baseUrl}}/api/email-notifications/{{emailNotificationId}}

------------------------------------------------------------
8) Department & Role Reads and Cleanup (Admin)
------------------------------------------------------------

8.1 Get Department by ID
- GET {{baseUrl}}/api/departments/{{departmentId}}

8.2 Get Role by ID
- GET {{baseUrl}}/api/roles/{{roleId}}

8.3 Safe Deletes (Only if not referenced)
- DELETE {{baseUrl}}/api/email-notifications/{{emailNotificationId}}
- DELETE {{baseUrl}}/api/salaries/{{salaryId}}
- DELETE {{baseUrl}}/api/leave-requests/{{leaveRequestId}}
- DELETE {{baseUrl}}/api/attendance/{{attendanceId}}
- DELETE {{baseUrl}}/api/employees/{{employeeId}}
- DELETE {{baseUrl}}/api/roles/{{roleId}}
- DELETE {{baseUrl}}/api/departments/{{departmentId}}
- DELETE {{baseUrl}}/api/leave-types/{{leaveTypeId}}
Note: Deleting referenced entities may fail due to foreign key constraints. Perform deletes in reverse dependency order.

------------------------------------------------------------
Negative/Role-Based Tests (Recommended)
------------------------------------------------------------

- Access an Admin-only endpoint with {{employeeToken}} → expect 403 Forbidden.
- Call any protected endpoint without Authorization header → expect 401 Unauthorized.
- Submit invalid data (e.g., negative salary, missing required fields) → expect 400 Bad Request with error payload.
- Attempt Punch Out without prior Punch In → expect proper validation error.
- Approve/Reject leave with a non-approver or invalid approverId → expect forbidden/validation error.

------------------------------------------------------------
Quick Flow Summary (Order)
------------------------------------------------------------

1) Auth: Register Admin → Login Admin
2) Master Data: Create Department → Role → Leave Type → Verify GETs
3) Users/Employees: Register Manager (optional) → Login → Create Manager Employee
4) Users/Employees: Register Employee → Login → Create Employee → Verify GETs → Update/Status
5) Attendance: Punch In → GET by date → Punch Out → GET range/month/daily → Update/Delete
6) Leave: Apply → GET all/by employee → Approve/Reject → Delete
7) Salaries: Create → GET all/by id/by employee → Update → Deactivate → Delete
8) Email Notifications: Create → GET all/by id/by recipient → Delete
9) Cleanup/Reads: Department/Role GETs → Safe Deletes (if needed)

Tip: Use Postman Tests to store IDs and tokens into environment variables automatically for chaining requests.
