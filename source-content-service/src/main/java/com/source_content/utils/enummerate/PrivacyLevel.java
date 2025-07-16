package com.source_content.utils.enummerate;

public enum PrivacyLevel {
    PUBLIC("Công khai")
  , PRIVATE("Chỉ mình bạn")
  , FRIEND("Bạn bè")
  , CUSTOM("Bạn bè ngoại trừ");

    private String value;

    PrivacyLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
