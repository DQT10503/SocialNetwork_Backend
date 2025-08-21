package com.source_content.utils.enummerate;

public enum PointActionType {
    LIKE(1),
    COMMENT(2),
    SHARE(3);

    private final int value;

    PointActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
