package com.api.framework.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Constants {

    private Constants() {}

    public static final String ERR_400 = "BAD_REQUEST";
    public static final String ERR_401 = "UNAUTHORIZED";
    public static final String ERR_403 = "FORBIDDEN";
    public static final String ERR_404 = "NOT_FOUND";
    public static final String ERR_415 = "UNSUPPORTED_MEDIA_TYPE";
    public static final String ERR_500 = "INTERNAL_SERVER_ERROR";
    public static final String ERR_DATE = "ERR_DATE";

    public static final int EMAIL_MAX_LENGTH_DEFAULT = 200;
    public static final int PAGE_SIZE_DEFAULT = 20;

    public static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
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

    public static final int MAX_YEAR = 2099;
    public static final int MAX_MONTH = 12;
    public static final int MAX_DAY = 31;
    public static final int MIN_YEAR = 1900;
    public static final int MIN_MONTH = 1;
    public static final int MIN_DAY = 1;
    public static final Instant MAX_DATE = initMaxDate();
    public static final Instant MIN_DATE = initMinDate();

    private static Instant initMaxDate() {
        return LocalDate.of(MAX_YEAR, MAX_MONTH, MAX_DAY).atStartOfDay(Constants.DEFAULT_ZONE_ID).toInstant().plus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS).minusSeconds(1);
    }

    private static Instant initMinDate() {
        return LocalDate.of(MIN_YEAR, MIN_MONTH, MIN_DAY).atStartOfDay(Constants.DEFAULT_ZONE_ID).toInstant();
    }

}
