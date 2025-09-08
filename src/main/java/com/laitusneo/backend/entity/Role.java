package com.laitusneo.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(Long id, String roleName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.roleName = roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    // Builder class
    public static class RoleBuilder {
        private Long id;
        private String roleName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public RoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        public RoleBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RoleBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Role build() {
            return new Role(id, roleName, createdAt, updatedAt);
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
