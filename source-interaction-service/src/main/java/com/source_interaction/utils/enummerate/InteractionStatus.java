package com.source_interaction.utils.enummerate;

public enum InteractionStatus {
    ACTIVE("Đang hoạt động"),
    INACTIVE("Ngừng hoạt động");
    private String value;

    InteractionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
