package com.source_user_auth.utils.validator;

import com.source_user_auth.utils.UserAnnotations.PasswordMatches;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    private String message;
    private String password;
    private String passwordConfirm;

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.password = constraintAnnotation.password();
        this.passwordConfirm = constraintAnnotation.passwordConfirm();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (ObjectUtils.allNull(value)) {
            message = "value is null";
            return isValid;
        }
        try {
            String pw = BeanUtils.getProperty(value, password);
            String pwConfirm = BeanUtils.getProperty(value, passwordConfirm);
            if (StringUtils.isNotEmpty(pw) && StringUtils.isNotEmpty(pwConfirm)) {
                if (!pw.equals(pwConfirm)) {
                    isValid = false;
                }
            }
        } catch (Exception e) {
            isValid = false;
        }

        return isValid;
    }
}
