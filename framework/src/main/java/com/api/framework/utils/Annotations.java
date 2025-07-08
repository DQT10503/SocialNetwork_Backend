package com.api.framework.utils;

import com.api.framework.utils.validator.*;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

public @interface Annotations {

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface Operator {
        String value() default "EQUAL";

        String field() default "";

        String fieldOr() default "";
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface IgnoreField {
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface FilterDate {
        String value() default "FROM";

        String field() default "id";
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface FilterTime {
        String value() default "FROM";

        String field() default "id";
    }

    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = {ValidDateTimeBeforeValidator.class})
    public @interface ValidDateTimeBefore {
        String message() default "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc";

        String fromField() default "";

        String toField() default "";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Target({ElementType.FIELD}) //áp dụng trên các Field của Class
    @Retention(RetentionPolicy.RUNTIME) //annotation này sẽ được giữ lại thời điểm runtime và có thể truy cập thông qua reflection
    @Documented //annotation này sẽ xuất hiện trong tài liệu API (javadoc) của class
    public @interface ColumnHeader {
        boolean id() default false;

        String value() default "";

        String style() default "";

        int order() default 0;

        String validLength() default "";

        int minLength() default Integer.MIN_VALUE;

        int maxLength() default Integer.MAX_VALUE;

        long maxValue() default Long.MAX_VALUE;

        long minValue() default Long.MIN_VALUE;

        int decimalPlaces() default 0;

        String columnCheckDuplicate() default "";
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface DateTime {
        String lessThan() default "";

        String greaterThan() default "";
    }

    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = {ValidTimeBeforeValidator.class})
    @Documented
    public @interface ValidTimeBefore {
        String message() default "";

        String fromField() default "";

        String toField() default "";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface ValidValueNumericContainer {
        ValidValueNumeric[] value();

        String message() default "";
    }

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = {ValidValueNumericValidator.class})
    @Documented
    @Repeatable(ValidValueNumericContainer.class)
    public @interface ValidValueNumeric {
        String message() default "Giá trị bắt đầu phải nhỏ hơn giá trị kết thúc";

        String fromField() default "";

        String toField() default "";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface ItemCode {
        String value() default "";

        String valueOnCreate() default "";

        String valueOnUpdate() default "";
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = {EmailValidator.class})
    @Documented
    public @interface Email {
        String message() default "Invalid email";

        int size() default Constants.EMAIL_MAX_LENGTH_DEFAULT;

        String regex() default "";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

}
