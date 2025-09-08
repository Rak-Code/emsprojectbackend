package com.laitusneo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.mail.host")
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("noreply@company.com"); // Set a default from address
            mailSender.send(message);
        } catch (Exception e) {
            // Log the error but don't fail the application
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    public void sendLeaveRequestNotification(String to, String employeeName, String status) {
        String subject = "Leave Request " + status;
        String text = String.format(
            "Dear %s,\n\nYour leave request has been %s.\n\nBest regards,\nHR Team",
            employeeName, status.toLowerCase()
        );
        sendSimpleEmail(to, subject, text);
    }

    public void sendAttendanceAlert(String to, String employeeName, String message) {
        String subject = "Attendance Alert";
        String text = String.format(
            "Dear %s,\n\n%s\n\nBest regards,\nHR Team",
            employeeName, message
        );
        sendSimpleEmail(to, subject, text);
    }
}
