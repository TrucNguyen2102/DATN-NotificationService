package com.business.notification_service.service;

import com.business.notification_service.entity.Notification;

public interface NotificationService{
    void sendNotification(Notification notification);
}
