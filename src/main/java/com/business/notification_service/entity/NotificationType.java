package com.business.notification_service.entity;

public enum NotificationType {
    BOOKING_CONFIRMATION("Xác nhận đặt bàn"),
    BOOKING_REMINDER("Nhắc nhở đặt bàn"),
//    CANCELLATION_WARNING("Cảnh báo hủy"),
    AUTO_CANCELLATION("Tự động hủy"),

    POLICY_VIOLATION("Vi phạm chính sách đặt bàn");

    private final String messageContent;

    NotificationType(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public static NotificationType fromString(String type) {
        for (NotificationType nt : NotificationType.values()) {
            if (nt.name().equalsIgnoreCase(type)) {
                return nt;
            }
        }
        throw new IllegalArgumentException("Unknown notification type: " + type);
    }
}
