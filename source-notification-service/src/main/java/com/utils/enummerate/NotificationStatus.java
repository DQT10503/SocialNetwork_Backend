package com.utils.enummerate;

public enum NotificationStatus {
    ACTIVE("Đang hoạt động")
  , INACTIVE("Ngừng hoạt động");

    private String value;

    NotificationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
