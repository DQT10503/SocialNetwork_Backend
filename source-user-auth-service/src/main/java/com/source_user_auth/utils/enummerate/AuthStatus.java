package com.source_user_auth.utils.enummerate;

public enum AuthStatus {

    ACTIVE("Đang hoạt động")
  , INACTIVE("Ngừng hoạt động");

    private String value;

    AuthStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
