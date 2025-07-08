package com.api.framework.utils.validator;

import com.api.framework.utils.Annotations;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Annotations.Email, String> {

    /**
        Class này dùng để:
        - Check size email không vượt quá giới hạn
        - Check định dạng email
        - Đảm bảo email chỉ chứa kí tự ASCII
        - Nếu email không hợp lệ, custom message lỗi
     **/

    private int size;
    private String message;

    @Override
    public void initialize(Annotations.Email constraintAnnotation) {
        this.size = constraintAnnotation.size();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        boolean isValid = true;
        if (value.length() > size) {
            isValid = false;
        } else {
            isValid = isValidEmail(value);
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
        return isValid;
    }

    private boolean isValidEmail(String email) {
        if (isAscii(email)) {
            if (email.endsWith(".")) {
                return false;
            }
            Pattern pattern = Pattern.compile("^((\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*([, ])*)*");
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean isAscii(String input) {
        char[] arr = input.toCharArray();
        for (char c : arr) {
            if (c >= 128) {
                return false;
            }
        }
        return true;
    }
}
