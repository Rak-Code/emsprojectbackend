package com.laitusneo.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_notifications")
public class EmailNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column(name = "recipient_email", nullable = false)
    private String recipientEmail;

    @Column(nullable = false)
    private String subject;

    @Lob
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(name = "sent_status")
    private SentStatus sentStatus = SentStatus.SENT;

    @Column(name = "sent_at")
    private LocalDateTime sentAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private RelatedEntityType relatedEntityType;

    @Column(name = "related_entity_id")
    private Long relatedEntityId;

    // Constructors
    public EmailNotification() {}

    public EmailNotification(String recipientEmail, String subject, String body) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.body = body;
    }

    public EmailNotification(Long id, String recipientEmail, String subject, String body, SentStatus sentStatus, LocalDateTime sentAt, RelatedEntityType relatedEntityType, Long relatedEntityId) {
        this.id = id;
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.body = body;
        this.sentStatus = sentStatus;
        this.sentAt = sentAt;
        this.relatedEntityType = relatedEntityType;
        this.relatedEntityId = relatedEntityId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public SentStatus getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(SentStatus sentStatus) {
        this.sentStatus = sentStatus;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public RelatedEntityType getRelatedEntityType() {
        return relatedEntityType;
    }

    public void setRelatedEntityType(RelatedEntityType relatedEntityType) {
        this.relatedEntityType = relatedEntityType;
    }

    public Long getRelatedEntityId() {
        return relatedEntityId;
    }

    public void setRelatedEntityId(Long relatedEntityId) {
        this.relatedEntityId = relatedEntityId;
    }

    // Builder pattern method
    public static EmailNotificationBuilder builder() {
        return new EmailNotificationBuilder();
    }

    // Builder class
    public static class EmailNotificationBuilder {
        private Long id;
        private String recipientEmail;
        private String subject;
        private String body;
        private SentStatus sentStatus = SentStatus.SENT;
        private LocalDateTime sentAt = LocalDateTime.now();
        private RelatedEntityType relatedEntityType;
        private Long relatedEntityId;

        public EmailNotificationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EmailNotificationBuilder recipientEmail(String recipientEmail) {
            this.recipientEmail = recipientEmail;
            return this;
        }

        public EmailNotificationBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailNotificationBuilder body(String body) {
            this.body = body;
            return this;
        }

        public EmailNotificationBuilder sentStatus(SentStatus sentStatus) {
            this.sentStatus = sentStatus;
            return this;
        }

        public EmailNotificationBuilder sentAt(LocalDateTime sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public EmailNotificationBuilder relatedEntityType(RelatedEntityType relatedEntityType) {
            this.relatedEntityType = relatedEntityType;
            return this;
        }

        public EmailNotificationBuilder relatedEntityId(Long relatedEntityId) {
            this.relatedEntityId = relatedEntityId;
            return this;
        }

        public EmailNotification build() {
            return new EmailNotification(id, recipientEmail, subject, body, sentStatus, sentAt, relatedEntityType, relatedEntityId);
        }
    }

    public enum SentStatus {
        SENT, FAILED
    }

    public enum RelatedEntityType {
        LEAVE_REQUEST, SALARY, ATTENDANCE, OTHER
    }
}
