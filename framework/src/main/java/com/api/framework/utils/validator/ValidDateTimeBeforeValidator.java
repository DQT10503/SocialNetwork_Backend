package com.api.framework.utils.validator;

import com.api.framework.utils.Annotations;
import com.api.framework.utils.DateTimeUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;

public class ValidDateTimeBeforeValidator implements ConstraintValidator<Annotations.ValidDateTimeBefore, Object> {

    private String fromField;
    private String toField;
    private String message;
    private Instant currentTime;

    @Override
    public void initialize(Annotations.ValidDateTimeBefore constraintAnnotation) {
        fromField = constraintAnnotation.fromField();
        toField = constraintAnnotation.toField();
        message = constraintAnnotation.message();
        currentTime = DateTimeUtils.getCurrentTimeUTC();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (ObjectUtils.allNull(value)) {
            return isValid;
        }
        try {
            final String from = BeanUtils.getProperty(value, fromField);
            final String to = BeanUtils.getProperty(value, toField);
            Instant fromDateTime = Instant.parse(from);
            Instant toDateTime = Instant.parse(to);
            if (ObjectUtils.allNull(fromDateTime) || ObjectUtils.allNull(toDateTime) || DateTimeUtils.isBeforeDate(toDateTime, fromDateTime) || DateTimeUtils.isBeforeDate(fromDateTime, currentTime)) {
                isValid = false;
            }
        } catch (Exception e) {
            isValid = false;
        }

        return isValid;
    }
}
