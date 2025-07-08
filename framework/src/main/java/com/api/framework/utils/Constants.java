package com.api.framework.utils;

import java.time.ZoneId;

public class Constants {

    private Constants() {}

    public static final String ERR_404 = "NOT_FOUND";

    public static final int EMAIL_MAX_LENGTH_DEFAULT = 200;
    public static final int PAGE_SIZE_DEFAULT = 20;

    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Ho_Chi_Minh");
    public static final String FULL_DATETIME_FORMAT_WITH_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String FULL_DATETIME_FORMAT_HYPHEN = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_DATETIME_FORMAT_HYPHEN = "yyyy-MM-dd";

    public static final String SHORT_DATETIME_SLASH = "dd/MM/yyyy";
    public static final String SHORT_DATETIME_FORMAT_SLASH = "dd/MM/yyyy HH:mm:ss";
    public static final String FULL_DATETIME_FORMAT_WITH_ZONE_DDMMYY_SLASH = "dd/MM/yyyy'T'HH:mm:ss.SSS'Z'";
    
    public static final String IMAGE_JPG_DOMAIN = ".jpg";
    public static final String IMAGE_JPEG_DOMAIN = ".jpeg";
    public static final String IMAGE_PNG_DOMAIN = ".png";

    public static final String COMMA = ",";
    public static final String COMMA_SPACE = ", ";
    public static final String DOT = ".";
    public static final String SLASH = "/";
    public static final String UNDERSCORE = "_";


    public static final String WORD_DOMAIN = ".docx";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";

    public static final String EXCEPTION_ERROR_CODE = "9999";
    public static final String FIELD_ERROR_CODE = "1001";

}
