package com.laitusneo.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "base_salary", nullable = false)
    private Double baseSalary;

    private Double allowances = 0.0;

    private Double deductions = 0.0;

    @Column(name = "net_salary")
    private Double netSalary;

    @Column(name = "effective_date", nullable = false)
    private LocalDate effectiveDate;

    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Salary() {}

    public Salary(Employee employee, Double baseSalary, LocalDate effectiveDate) {
        this.employee = employee;
        this.baseSalary = baseSalary;
        this.effectiveDate = effectiveDate;
    }

    public Salary(Long id, Employee employee, Double baseSalary, Double allowances, Double deductions, Double netSalary, LocalDate effectiveDate, Boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.employee = employee;
        this.baseSalary = baseSalary;
        this.allowances = allowances;
        this.deductions = deductions;
        this.netSalary = netSalary;
        this.effectiveDate = effectiveDate;
        this.isActive = isActive;
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

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getAllowances() {
        return allowances;
    }

    public void setAllowances(Double allowances) {
        this.allowances = allowances;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
    public static SalaryBuilder builder() {
        return new SalaryBuilder();
    }

    // Builder class
    public static class SalaryBuilder {
        private Long id;
        private Employee employee;
        private Double baseSalary;
        private Double allowances = 0.0;
        private Double deductions = 0.0;
        private Double netSalary;
        private LocalDate effectiveDate;
        private Boolean isActive = true;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public SalaryBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SalaryBuilder employee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public SalaryBuilder baseSalary(Double baseSalary) {
            this.baseSalary = baseSalary;
            return this;
        }

        public SalaryBuilder allowances(Double allowances) {
            this.allowances = allowances;
            return this;
        }

        public SalaryBuilder deductions(Double deductions) {
            this.deductions = deductions;
            return this;
        }

        public SalaryBuilder netSalary(Double netSalary) {
            this.netSalary = netSalary;
            return this;
        }

        public SalaryBuilder effectiveDate(LocalDate effectiveDate) {
            this.effectiveDate = effectiveDate;
            return this;
        }

        public SalaryBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public SalaryBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SalaryBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Salary build() {
            return new Salary(id, employee, baseSalary, allowances, deductions, netSalary, effectiveDate, isActive, createdAt, updatedAt);
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
