package com.source_user_auth.utils;

import com.source_user_auth.utils.validator.PasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

public @interface UserAnnotations {

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = {PasswordMatchesValidator.class})
    @Documented
    public @interface PasswordMatches {
        String message() default "Password and Password Confirm do not match";

        String password() default "";

        String passwordConfirm() default "";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
