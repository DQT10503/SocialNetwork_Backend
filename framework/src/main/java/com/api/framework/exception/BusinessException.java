package com.api.framework.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    private String code;
    private String message;
    private String dataInput;
    private List<ErrorDetail> errorDetails;
    private ErrorDetail errorDetail;

    public BusinessException() {
        super();
    }

    public BusinessException(String code, String message, String dataInput) {
        super();
        this.code = code;
        this.message = message;
        this.dataInput = dataInput;
    }

    public BusinessException(String errorCode) {
        this.code = errorCode;
        this.message = null;
        this.errorDetails = new ArrayList<>();
        this.errorDetail = null;
    }

    public BusinessException(List<ErrorDetail> errorDetails) {
        this.code = null;
        this.message = null;
        this.dataInput = null;
        this.errorDetails = errorDetails;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
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

    public List<ErrorDetail> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<ErrorDetail> errorDetails) {
        this.errorDetails = errorDetails;
    }

    public ErrorDetail getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }

    @Override
    public String toString() {
        return "BusinessException [code=" + code + ", message=" + message + ", dataInput=" + dataInput + ", errorDetails=" + errorDetails + ", errorDetail=" + errorDetail + "]";
    }
}
