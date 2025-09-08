package com.laitusneo.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leave_types")
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id")
    private Long id;

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;

    private String description;

    @Column(name = "max_days_per_year")
    private Integer maxDaysPerYear = 0;

    @Column(name = "is_paid")
    private Boolean isPaid = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public LeaveType() {}

    public LeaveType(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }

    public LeaveType(Long id, String typeName, String description, Integer maxDaysPerYear, Boolean isPaid, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.typeName = typeName;
        this.description = description;
        this.maxDaysPerYear = maxDaysPerYear;
        this.isPaid = isPaid;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxDaysPerYear() {
        return maxDaysPerYear;
    }

    public void setMaxDaysPerYear(Integer maxDaysPerYear) {
        this.maxDaysPerYear = maxDaysPerYear;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
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
    public static LeaveTypeBuilder builder() {
        return new LeaveTypeBuilder();
    }

    // Builder class
    public static class LeaveTypeBuilder {
        private Long id;
        private String typeName;
        private String description;
        private Integer maxDaysPerYear = 0;
        private Boolean isPaid = true;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public LeaveTypeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LeaveTypeBuilder typeName(String typeName) {
            this.typeName = typeName;
            return this;
        }

        public LeaveTypeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public LeaveTypeBuilder maxDaysPerYear(Integer maxDaysPerYear) {
            this.maxDaysPerYear = maxDaysPerYear;
            return this;
        }

        public LeaveTypeBuilder isPaid(Boolean isPaid) {
            this.isPaid = isPaid;
            return this;
        }

        public LeaveTypeBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public LeaveTypeBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public LeaveType build() {
            return new LeaveType(id, typeName, description, maxDaysPerYear, isPaid, createdAt, updatedAt);
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
}
