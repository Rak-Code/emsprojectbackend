package com.laitusneo.backend.service;

import com.laitusneo.backend.entity.EmailNotification;
import com.laitusneo.backend.repository.EmailNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailNotificationService {

    private final EmailNotificationRepository emailNotificationRepository;

    public EmailNotificationService(EmailNotificationRepository emailNotificationRepository) {
        this.emailNotificationRepository = emailNotificationRepository;
    }

    public EmailNotification createNotification(EmailNotification notification) {
        return emailNotificationRepository.save(notification);
    }

    public List<EmailNotification> getAllNotifications() {
        return emailNotificationRepository.findAll();
    }

    public Optional<EmailNotification> getNotificationById(Long id) {
        return emailNotificationRepository.findById(id);
    }

    public List<EmailNotification> getNotificationsByRecipient(String recipientEmail) {
        return emailNotificationRepository.findByRecipientEmail(recipientEmail);
    }

    public List<EmailNotification> getNotificationsByStatus(EmailNotification.SentStatus status) {
        return emailNotificationRepository.findBySentStatus(status);
    }

    public void deleteNotification(Long id) {
        emailNotificationRepository.deleteById(id);
    }
}
