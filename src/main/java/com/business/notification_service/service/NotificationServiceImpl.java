package com.business.notification_service.service;

import com.business.notification_service.dto.UserResponse;
import com.business.notification_service.entity.Notification;
import com.business.notification_service.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

import static com.business.notification_service.entity.NotificationType.*;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${bookingServiceUrl}") // URL của Booking Service
    private String bookingServiceUrl;

    @Value("${userServiceUrl}") // URL của User Service
    private String userServiceUrl;

    public NotificationServiceImpl(NotificationRepo notificationRepo, EmailService emailService, RestTemplate restTemplate, @Value("${bookingServiceUrl}") String bookingServiceUrl, @Value("${userServiceUrl}") String userServiceUrl) {
        this.notificationRepo = notificationRepo;
        this.emailService = emailService;
        this.restTemplate = restTemplate;
        this.bookingServiceUrl = bookingServiceUrl;
        this.userServiceUrl = userServiceUrl;
    }

    public void sendNotification(Notification notification) {
        // Lấy userId từ Booking Service
        String booking_url = bookingServiceUrl + "/" + notification.getBookingId() + "/user";
        Integer userId = restTemplate.getForObject(booking_url, Integer.class);

        if (userId != null) {
            // Lấy email từ User Service
            String user_url = userServiceUrl + "/" + userId + "/email";
            String email = restTemplate.getForObject(user_url, String.class);

            if (email != null) {
                // Tạo nội dung email dựa trên NotificationType
                String subject = notification.getNotificationType().getMessageContent();
                String body = buildEmailContent(notification);

                // Gửi email
                sendEmail(email, subject, body);
            }
        }
    }

    private String buildEmailContent(Notification notification) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        switch (notification.getNotificationType()) {
            case BOOKING_CONFIRMATION:
                return String.format(
                        "Chào bạn, đơn đặt bàn của bạn đã được xác nhận. \n\n" +
                                "Thời gian xác nhận: %s\n" +
                                "Thời gian đặt bàn: %s\n" +
                                "Chúng tôi sẽ giữ bàn trong 15 phút kể từ thời gian đặt. Nếu quá thời gian đơn đặt sẽ bị hủy. \n\n" +
                                "Bạn vui lòng chú ý thời gian.",
                        notification.getSendAt().format(formatter),
                        "Thời gian đặt bàn từ Booking Service"
                );
            case BOOKING_REMINDER:
                return String.format(
                        "Nhắc nhở: Bạn có một đơn đặt bàn sắp tới. \n\n" +
                                "Thời gian đặt bàn: %s\n" +
                                "Bạn vui lòng chú ý thời gian.",
                        "Thời gian đặt bàn từ Booking Service"
                );
            case AUTO_CANCELLATION:
                return "Hệ thống tự động hủy đơn đặt bàn do đã hết thời gian giữ bàn.\n\n" +
                            "Nếu bạn muốn đặt bàn lại, vui lòng thực hiện đặt lại trên trang web của chúng tôi.\n\n" +
                            "Xin lỗi vì sự bất tiện này và cảm ơn bạn đã hiểu.";
            default:
                throw new IllegalStateException("Unexpected notification type: " + notification.getNotificationType());
        }
    }

    private void sendEmail(String to, String subject, String body) {
        // Sử dụng JavaMailSender hoặc thư viện tương tự để gửi email
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}
