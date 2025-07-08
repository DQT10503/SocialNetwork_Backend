package com.api.framework.utils.validator;

import com.api.framework.utils.Annotations;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidValueNumericValidator implements ConstraintValidator<Annotations.ValidValueNumeric, Object> {
    private String fromField;
    private String toField;
    private String message;


    @Override
    public void initialize(Annotations.ValidValueNumeric constraintAnnotation) {
        message = constraintAnnotation.message();
        fromField = constraintAnnotation.fromField();
        toField = constraintAnnotation.toField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (ObjectUtils.allNull(value)) {
            return isValid;
        }
        try {
            final Double from = Double.valueOf(BeanUtils.getProperty(value, fromField));
            final Double to = Double.valueOf(BeanUtils.getProperty(value, toField));
            if (ObjectUtils.allNotNull(from) && ObjectUtils.allNotNull(to) && from.compareTo(to) > 0) {
                isValid = false;
                context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            }
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }
}
