package com.laitusneo.backend.dto;

import java.time.LocalDate;

public class EmployeeCreateDTO {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Long departmentId;
    private Long roleId;
    private LocalDate joiningDate;

    // Constructors
    public EmployeeCreateDTO() {}

    public EmployeeCreateDTO(Long userId, String name, String email, String phone, String address, Long departmentId, Long roleId, LocalDate joiningDate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.departmentId = departmentId;
        this.roleId = roleId;
        this.joiningDate = joiningDate;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    // Builder pattern method
    public static EmployeeCreateDTOBuilder builder() {
        return new EmployeeCreateDTOBuilder();
    }

    // Builder class
    public static class EmployeeCreateDTOBuilder {
        private Long userId;
        private String name;
        private String email;
        private String phone;
        private String address;
        private Long departmentId;
        private Long roleId;
        private LocalDate joiningDate;

        public EmployeeCreateDTOBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public EmployeeCreateDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EmployeeCreateDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployeeCreateDTOBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public EmployeeCreateDTOBuilder address(String address) {
            this.address = address;
            return this;
        }

        public EmployeeCreateDTOBuilder departmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public EmployeeCreateDTOBuilder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public EmployeeCreateDTOBuilder joiningDate(LocalDate joiningDate) {
            this.joiningDate = joiningDate;
            return this;
        }

        public EmployeeCreateDTO build() {
            return new EmployeeCreateDTO(userId, name, email, phone, address, departmentId, roleId, joiningDate);
        }
    }
}