package com.business.notification_service.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
