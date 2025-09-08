package com.laitusneo.backend.dto;

import java.time.LocalDate;

public class LeaveRequestDTO {
    private Long employeeId;
    private Long leaveTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    // Constructors
    public LeaveRequestDTO() {}

    public LeaveRequestDTO(Long employeeId, Long leaveTypeId, LocalDate startDate, LocalDate endDate, String reason) {
        this.employeeId = employeeId;
        this.leaveTypeId = leaveTypeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    // Getters and Setters
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // Builder pattern method
    public static LeaveRequestDTOBuilder builder() {
        return new LeaveRequestDTOBuilder();
    }

    // Builder class
    public static class LeaveRequestDTOBuilder {
        private Long employeeId;
        private Long leaveTypeId;
        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;

        public LeaveRequestDTOBuilder employeeId(Long employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public LeaveRequestDTOBuilder leaveTypeId(Long leaveTypeId) {
            this.leaveTypeId = leaveTypeId;
            return this;
        }

        public LeaveRequestDTOBuilder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public LeaveRequestDTOBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public LeaveRequestDTOBuilder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public LeaveRequestDTO build() {
            return new LeaveRequestDTO(employeeId, leaveTypeId, startDate, endDate, reason);
        }
    }
}