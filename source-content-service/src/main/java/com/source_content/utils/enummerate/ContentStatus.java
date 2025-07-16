package com.source_content.utils.enummerate;

public enum ContentStatus {
    ACTIVE("Đang hoạt động")
  , INACTIVE("Ngưng hoạt động")
  , TEMPORARY("Tạm thời");

    private String value;

    ContentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
