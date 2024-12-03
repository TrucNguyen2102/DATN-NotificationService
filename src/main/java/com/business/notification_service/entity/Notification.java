package com.business.notification_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

//    @Column(name = "type")
//    private String type;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private NotificationType notificationType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "send_at")
    private LocalDateTime sendAt;

    @Column(name = "booking_id")
    private Integer bookingId; //khóa ngoại của booking từ booking-service

    public Notification() {

    }

    public Notification(Integer id, String content, NotificationType notificationType, LocalDateTime sendAt, Integer bookingId) {
        this.id = id;
        this.content = content;
        this.notificationType = notificationType;
        this.sendAt = sendAt;
        this.bookingId = bookingId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getSendAt() {
        return sendAt;
    }

    public void setSendAt(LocalDateTime sendAt) {
        this.sendAt = sendAt;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
}
