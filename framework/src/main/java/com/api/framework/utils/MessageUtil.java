package com.api.framework.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageUtil {

    @Autowired
    private MessageSource msgSource;

    public String getMessage(String key) {
        return getMessage(key, "");
    }

    public String getMessage(String key, Object... params) {
        List<String> paramStrs = new ArrayList<>();
        for (Object param : params) {
            paramStrs.add(String.valueOf(param));
        }
        return msgSource.getMessage(key, paramStrs.toArray(), LocaleContextHolder.getLocale());
    }

    public String getMessage(FieldError field) {
        return msgSource.getMessage(field, LocaleContextHolder.getLocale());
    }
}
