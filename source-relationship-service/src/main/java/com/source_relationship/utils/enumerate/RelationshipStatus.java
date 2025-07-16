package com.source_relationship.utils.enumerate;

public enum RelationshipStatus {
    ACTIVE("Đang hoạt động")
  , INACTIVE("Ngừng hoạt động");

    private String value;

    RelationshipStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
