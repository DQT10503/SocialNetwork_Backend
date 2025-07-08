package com.source_user_auth.utils.enummerate;

public enum RoleEnum {
    ROLE_USER("role_user")
  , ROLE_ADMIN("role_admin");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
