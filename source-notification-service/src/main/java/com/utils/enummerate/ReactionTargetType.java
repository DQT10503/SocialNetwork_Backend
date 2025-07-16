package com.utils.enummerate;

public enum ReactionTargetType {
    REACT_POST("react-post")
  , REACT_COMMENT("react-comment")
  , REACT_SHARE("react-share");

    private String value;

    ReactionTargetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
