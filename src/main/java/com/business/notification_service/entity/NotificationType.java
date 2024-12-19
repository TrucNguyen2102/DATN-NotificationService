package com.business.notification_service.entity;

public enum NotificationType {
    BOOKING_CONFIRMATION("XÁC NHẬN ĐẶT BÀN"),
    BOOKING_REMINDER("NHẮC NHỞ ĐẶT BÀN"),
//    CANCELLATION_WARNING("Cảnh báo hủy"),
    AUTO_CANCELLATION("TỰ ĐỘNG HỦY"),

    POLICY_VIOLATION("VI PHẠM CHÍNH SÁCH ĐẶT BÀN");

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
