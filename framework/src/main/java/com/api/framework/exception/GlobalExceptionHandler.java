package com.api.framework.exception;

import com.api.framework.utils.Constants;
import com.api.framework.utils.Utilities;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logbackLogger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception exception) {
        logbackLogger.error(ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(new ErrorDetail(Constants.EXCEPTION_ERROR_CODE, "", ""), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorDetail>> handleBusinessException(BusinessException exception) {
        List<ErrorDetail> errorDetails = new ArrayList<>();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (ObjectUtils.allNotNull(exception.getErrorDetail())) {
            errorDetails.add(exception.getErrorDetail());
            try {
                httpStatus = HttpStatus.valueOf(exception.getErrorDetail().getCode());
            } catch (Exception e) {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } else if (CollectionUtils.isNotEmpty(exception.getErrorDetails())) {
            errorDetails.addAll(exception.getErrorDetails());
        } else {
            try {
                httpStatus = HttpStatus.valueOf(exception.getCode());
            } catch (Exception e) {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
            errorDetails.add(new ErrorDetail(exception.getCode(), exception.getMessage(), exception.getDataInput()));
        }
        return new ResponseEntity<>(errorDetails, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDetail>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(handleArgumentNotValidException(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetail> handleConstraintViolation(ConstraintViolationException exception) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.BAD_REQUEST.value() + "", exception.getMessage(), errors.get(0)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDetail> handleMaxSizeException(MaxUploadSizeExceededException exception) {
        logbackLogger.error("MaxUploadSizeExceededException: File too large!");
        return new ResponseEntity<>(new ErrorDetail(HttpStatus.EXPECTATION_FAILED.value() + "", exception.getMessage(), ""), HttpStatus.EXPECTATION_FAILED);
    }

    private List<ErrorDetail> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorDetail> errors = new ArrayList<>();
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        for (ObjectError objectError : objectErrors) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                errors.add(new ErrorDetail(Constants.FIELD_ERROR_CODE, fieldError.getDefaultMessage(), fieldError.getField()));
            } else {
                errors.add(new ErrorDetail(Constants.FIELD_ERROR_CODE, objectError.getDefaultMessage(), String.valueOf(Utilities.returnNullInException(() -> objectError.getArguments()[1]))));
            }
        }
        return errors;
    }
}
