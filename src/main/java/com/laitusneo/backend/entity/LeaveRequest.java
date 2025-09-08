package com.laitusneo.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id", nullable = false)
    private LeaveType leaveType;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private Employee approvedBy;

    @Column(name = "applied_on")
    private LocalDateTime appliedOn = LocalDateTime.now();

    private LocalDateTime respondedOn;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public LeaveRequest() {}

    public LeaveRequest(Employee employee, LeaveType leaveType, LocalDate startDate, LocalDate endDate, String reason) {
        this.employee = employee;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    public LeaveRequest(Long id, Employee employee, LeaveType leaveType, LocalDate startDate, LocalDate endDate, String reason, Status status, Employee approvedBy, LocalDateTime appliedOn, LocalDateTime respondedOn, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.employee = employee;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.approvedBy = approvedBy;
        this.appliedOn = appliedOn;
        this.respondedOn = respondedOn;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Employee approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(LocalDateTime appliedOn) {
        this.appliedOn = appliedOn;
    }

    public LocalDateTime getRespondedOn() {
        return respondedOn;
    }

    public void setRespondedOn(LocalDateTime respondedOn) {
        this.respondedOn = respondedOn;
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
    public static LeaveRequestBuilder builder() {
        return new LeaveRequestBuilder();
    }

    // Builder class
    public static class LeaveRequestBuilder {
        private Long id;
        private Employee employee;
        private LeaveType leaveType;
        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;
        private Status status = Status.PENDING;
        private Employee approvedBy;
        private LocalDateTime appliedOn = LocalDateTime.now();
        private LocalDateTime respondedOn;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public LeaveRequestBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LeaveRequestBuilder employee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public LeaveRequestBuilder leaveType(LeaveType leaveType) {
            this.leaveType = leaveType;
            return this;
        }

        public LeaveRequestBuilder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public LeaveRequestBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public LeaveRequestBuilder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public LeaveRequestBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public LeaveRequestBuilder approvedBy(Employee approvedBy) {
            this.approvedBy = approvedBy;
            return this;
        }

        public LeaveRequestBuilder appliedOn(LocalDateTime appliedOn) {
            this.appliedOn = appliedOn;
            return this;
        }

        public LeaveRequestBuilder respondedOn(LocalDateTime respondedOn) {
            this.respondedOn = respondedOn;
            return this;
        }

        public LeaveRequestBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public LeaveRequestBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public LeaveRequest build() {
            return new LeaveRequest(id, employee, leaveType, startDate, endDate, reason, status, approvedBy, appliedOn, respondedOn, createdAt, updatedAt);
        }
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Status {
        PENDING, APPROVED, REJECTED
    }
}
