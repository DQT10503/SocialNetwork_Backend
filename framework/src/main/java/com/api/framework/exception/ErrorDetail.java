package com.api.framework.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

// chỉ bao gồm các field có giá trị != null trong JSON, nếu field nào null thì sẽ không xuất hiện trong response
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private String dataInput;

    public ErrorDetail(String code, String message, String dataInput) {
        this.code = code;
        this.message = message;
        this.dataInput = dataInput;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDataInput() {
        return dataInput;
    }

    public void setDataInput(String dataInput) {
        this.dataInput = dataInput;
    }
}
