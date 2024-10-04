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

    @Column(name = "type")
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "send_at")
    private LocalDateTime sendAt;

    @Column(name = "booking_id")
    private Integer bookingId; //khóa ngoại của booking từ booking-service

    public Notification() {

    }

    public Notification(Integer id, String content, String type, LocalDateTime sendAt, Integer bookingId) {
        this.id = id;
        this.content = content;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
