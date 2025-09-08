package com.laitusneo.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String departmentName;
    private String roleName;
    private LocalDate joiningDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public EmployeeResponseDTO() {}

    public EmployeeResponseDTO(Long id, String name, String email, String phone, String address, String departmentName, String roleName, LocalDate joiningDate, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.departmentName = departmentName;
        this.roleName = roleName;
        this.joiningDate = joiningDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Builder pattern method
    public static EmployeeResponseDTOBuilder builder() {
        return new EmployeeResponseDTOBuilder();
    }

    // Builder class
    public static class EmployeeResponseDTOBuilder {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String address;
        private String departmentName;
        private String roleName;
        private LocalDate joiningDate;
        private String status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public EmployeeResponseDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EmployeeResponseDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EmployeeResponseDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployeeResponseDTOBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public EmployeeResponseDTOBuilder address(String address) {
            this.address = address;
            return this;
        }

        public EmployeeResponseDTOBuilder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        public EmployeeResponseDTOBuilder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        public EmployeeResponseDTOBuilder joiningDate(LocalDate joiningDate) {
            this.joiningDate = joiningDate;
            return this;
        }

        public EmployeeResponseDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public EmployeeResponseDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EmployeeResponseDTOBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public EmployeeResponseDTO build() {
            return new EmployeeResponseDTO(id, name, email, phone, address, departmentName, roleName, joiningDate, status, createdAt, updatedAt);
        }
    }
}