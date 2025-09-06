package com.laitusneo.backend.service;

import com.laitusneo.backend.entity.Employee;
import com.laitusneo.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public List<Employee> getEmployeesByRole(Long roleId) {
        return employeeRepository.findByRoleId(roleId);
    }

    public Employee updateEmployeeStatus(Long id, String status) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            try {
                Employee.Status statusEnum = Employee.Status.valueOf(status.toUpperCase());
                employee.setStatus(statusEnum);
                employee.setUpdatedAt(java.time.LocalDateTime.now());
                return employeeRepository.save(employee);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            if (updatedEmployee.getName() != null) employee.setName(updatedEmployee.getName());
            if (updatedEmployee.getEmail() != null) employee.setEmail(updatedEmployee.getEmail());
            if (updatedEmployee.getPhone() != null) employee.setPhone(updatedEmployee.getPhone());
            if (updatedEmployee.getAddress() != null) employee.setAddress(updatedEmployee.getAddress());
            if (updatedEmployee.getDepartment() != null) employee.setDepartment(updatedEmployee.getDepartment());
            if (updatedEmployee.getRole() != null) employee.setRole(updatedEmployee.getRole());
            if (updatedEmployee.getJoiningDate() != null) employee.setJoiningDate(updatedEmployee.getJoiningDate());
            employee.setUpdatedAt(java.time.LocalDateTime.now());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
