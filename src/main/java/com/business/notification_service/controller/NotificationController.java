package com.business.notification_service.controller;

import com.business.notification_service.dto.NotificationDTO;
import com.business.notification_service.entity.Notification;
import com.business.notification_service.entity.NotificationType;
import com.business.notification_service.repository.NotificationRepo;
import com.business.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepo notificationRepo;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/endpoints")
    public List<Map<String, String>> getEndpoints() {
        return List.of(
                Map.of("service", "notification-service", "method", "POST", "url", "/api/notifications/sendNotification")
        );
    }

    @PostMapping("/sendNotification")
    public ResponseEntity<Void> createNotification(@RequestBody NotificationDTO notificationDTO) {
        try {
            // Tạo thực thể Notification từ DTO
            Notification notification = new Notification();
            notification.setContent(notificationDTO.getContent());
            notification.setNotificationType(NotificationType.valueOf(notificationDTO.getNotificationType()));
            notification.setSendAt(notificationDTO.getSendAt());
            notification.setBookingId(notificationDTO.getBookingId());

            // Lưu thông báo vào cơ sở dữ liệu
            notificationRepo.save(notification);

            notificationService.sendNotification(notification);
//            sendEmail(notification);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

//    private void sendEmail(Notification notification) {
//        notificationService.sendNotification(notification);
//        System.out.println("Sending email for notification: " + notification.getContent());
//    }
}
