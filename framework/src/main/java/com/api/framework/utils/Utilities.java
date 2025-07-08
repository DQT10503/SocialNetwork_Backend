package com.api.framework.utils;

import com.api.framework.domain.image.ImageResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.exception.ErrorDetail;
import com.api.framework.security.BearerContextHolder;
import com.api.framework.utils.Annotations.IgnoreField;
import com.api.framework.utils.Annotations.FilterDate;
import com.api.framework.utils.Annotations.FilterTime;
import com.api.framework.utils.Annotations.Operator;
import com.api.framework.utils.enumerator.Operators;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.google.common.base.CaseFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeTypes;
import org.apache.tika.parser.AutoDetectParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    private static final Logger logger = LoggerFactory.getLogger(Utilities.class);

    private Utilities() {
    }

    /*
        ObjectMapper có thể parse JSON thành Java Object
        => Vì nó dùng cơ chế Reflection: tìm constructor(or getter/setter), đọc value from JSON và bind vào field tương ứng
     */
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Hibernate5Module());

        try {
            return mapper.readValue(mapper.writeValueAsString(source), clazz);
        } catch (Exception e) {
            logger.error("Can't copy properties from {} to {}: {}", source.getClass(), clazz.getName(), e.getMessage());
            return null;
        }
    }

    public static <T> List<T> copyProperties(List<?> source, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Hibernate5Module());

        try {
            CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            return mapper.readValue(mapper.writeValueAsString(source), type);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static <T, K> K updateProperties(T source, K target) {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();
        copyData(source, target, sourceFields, targetFields);
        try {
            Class<?> superClass = source.getClass().getSuperclass();
            sourceFields = superClass.getDeclaredFields();
            copyData(source, target, sourceFields, targetFields);
        } catch (Exception e) {
        }
        return target;
    }

    private static <K, T> void copyData(T source, K target, Field[] sourceFields, Field[] targetFields) {
        for (Field sourceField : sourceFields) {
            try {
                sourceField.setAccessible(true);
                for (Field targetField : targetFields) {
                    try {
                        targetField.setAccessible(true);
                        if (Objects.nonNull(sourceField.get(source)) && sourceField.getName().equals(targetField.getName())
                                && sourceField.getType().equals(targetField.getType())) {
                            targetField.set(target, sourceField.get(source));
                            break;
                        }
                    } catch (IllegalAccessException e) {
                    } finally {
                        targetField.setAccessible(false);
                    }
                }
            } finally {
                sourceField.setAccessible(false);
            }
        }
    }

    public static StringBuilder buildWhereClause(Object o, Map<String, Object> params) {
        StringBuilder whereClause = new StringBuilder();

        Field[] fields = o.getClass().getDeclaredFields();
        Object value = null;
        String fieldName;
        String fieldNameParam;
        for (Field f : fields) {
            String fieldAlias = null;
            try {
                f.setAccessible(true);
                value = f.get(o);
                if (Objects.isNull(value) || value.toString().length() == 0) {
                    continue;
                }
                if (f.isAnnotationPresent(IgnoreField.class)) {
                    continue;
                }
                if (f.isAnnotationPresent(FilterDate.class)) {
                    if ("FROM".equals(f.getDeclaredAnnotation(FilterDate.class).value())) {
                        whereClause.append(" AND " + f.getDeclaredAnnotation(FilterDate.class).field() + " >= :" + f.getName());
                        params.put(f.getName(), value);
                    }
                    if ("TO".equals(f.getDeclaredAnnotation(FilterDate.class).value())) {
                        whereClause.append(" AND " + f.getDeclaredAnnotation(FilterDate.class).field() + " <= :" + f.getName());
                        params.put(f.getName(), value);
                    }
                    if ("GREATER_THAN".equals(f.getDeclaredAnnotation(FilterDate.class).value())) {
                        whereClause.append(" AND " + f.getDeclaredAnnotation(FilterDate.class).field() + " > :" + f.getName());
                        params.put(f.getName(), value);
                    }
                    if ("LESS_THAN".equals(f.getDeclaredAnnotation(FilterDate.class).value())) {
                        whereClause.append(" AND " + f.getDeclaredAnnotation(FilterDate.class).field() + " < :" + f.getName());
                        params.put(f.getName(), value);
                    }
                    continue;
                }
                if (f.isAnnotationPresent(FilterTime.class)) {
                    if ("FROM".equals(f.getDeclaredAnnotation(FilterTime.class).value())) {
                        whereClause.append(" AND cast(" + f.getDeclaredAnnotation(FilterTime.class).field() + " as text) >= :" + f.getName());
                        params.put(f.getName(), value);
                    }
                    if ("TO".equals(f.getDeclaredAnnotation(FilterTime.class).value())) {
                        whereClause.append(" AND cast(" + f.getDeclaredAnnotation(FilterTime.class).field() + " as text) <= :" + f.getName());
                        params.put(f.getName(), value);
                    }
                    continue;
                }
                if (value.getClass().isEnum()) {
                    value = value.toString();
                }
                String operator = Operators.EQUAL.name();
                if (f.isAnnotationPresent(Operator.class)) {
                    operator = f.getDeclaredAnnotation(Operator.class).value();
                    fieldAlias = f.getDeclaredAnnotation(Operator.class).field();
                }
                fieldName = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert(f.getName());
                if (StringUtils.isBlank(fieldAlias)) {
                    fieldAlias = fieldName;
                }
                fieldNameParam = ":" + fieldName;
                if (Operators.IN.equals(Operators.valueOf(operator))) {
                    if ("[]".equals(value.toString())) {
                        continue;
                    }
                    String fieldNameTmp = f.getDeclaredAnnotation(Operator.class).field();
                    if (StringUtils.isNotBlank(fieldNameTmp)) {
                        fieldName = fieldNameTmp;
                    }
                    fieldNameParam = ":" + fieldName + "_in";
                    whereClause.append(" AND " + fieldAlias + " IN " + fieldNameParam);
                    List<Object> values = (List<Object>) value;
                    List<String> valueEnum = new ArrayList<>();
                    boolean isEnum = false;
                    for (Object obj : values) {
                        if (obj.getClass().isEnum()) {
                            isEnum = true;
                            valueEnum.add(obj.toString());
                        }
                    }
                    params.put(fieldName + "_in", isEnum ? valueEnum : value);
                } else if (Operators.LIKE.equals(Operators.valueOf(operator))) {
                    String fieldOR = f.getDeclaredAnnotation(Operator.class).fieldOr();
                    if (StringUtils.isNotBlank(fieldOR)) {
                        whereClause.append(" AND (LOWER(" + fieldAlias + ") LIKE " + fieldNameParam + " OR LOWER(" + fieldOR + ") LIKE " + fieldNameParam + ")");
                    } else {
                        whereClause.append(" AND LOWER(" + fieldAlias + ") LIKE " + fieldNameParam);
                    }
                    params.put(fieldName, "%" + String.valueOf(f.get(o)).toLowerCase() + "%");
                } else {
                    whereClause.append(" AND " + fieldAlias + " = " + fieldNameParam);
                    params.put(fieldName, value);
                }
            } catch (Exception e) {
            } finally {
                f.setAccessible(false);
            }
        }

        return whereClause;
    }

    public static <T> T returnNullInException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            return null;
        }
    }

    public static Map<String, Object> buildMapParamsFromRequest(HttpServletRequest request) {
        Map<String, Object> parameters = new ConcurrentHashMap<>();
        for (String param : request.getParameterMap().keySet()) {
            String camelCaseParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);
            String[] lsParams = request.getParameterValues(param);
            if (Objects.isNull(lsParams) || lsParams.length == 0) {
                continue;
            }
            if (lsParams.length == 1) {
                parameters.put(camelCaseParam, request.getParameterValues(param)[0]);
            } else {
                List<String> params = Arrays.asList(lsParams);
                parameters.put(camelCaseParam, params);
            }
        }
        return parameters;
    }

    public static HttpServletResponse buildErrorResponse(HttpServletResponse res, String errorCode, int statusCode, String message, String input) {
        ErrorDetail errorDetail = new ErrorDetail(errorCode, message, input);
        res.setStatus(statusCode);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper mapper = new ObjectMapper();
        try {
            res.setContentType("application/json; charset=UTF-8");
            res.setCharacterEncoding("UTF-8");
            res.getOutputStream().write(mapper.writeValueAsString(errorDetail).getBytes());
        } catch (Exception e) {
        }
        return res;
    }

    public static Map<String, String> mapColumnHeaderInClazz(Class<?> clazz) {
        Map<String, String> mapCols = new HashMap<>();

        for (Field f : clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(Annotations.ColumnHeader.class)) {
                Annotations.ColumnHeader header = f.getAnnotation(Annotations.ColumnHeader.class);
                if (StringUtils.isNotBlank(header.value())) {
                    mapCols.put(header.value(), f.getName());
                }
            }
        }
        return mapCols;
    }

    public static Map<Integer, String> mapColumnHeaderWithOrderInClazz(Class<?> clazz) {
        Map<Integer, String> mapCols = new HashMap<>();

        for (Field f : clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(Annotations.ColumnHeader.class)) {
                Annotations.ColumnHeader header = f.getAnnotation(Annotations.ColumnHeader.class);
                if (StringUtils.isNotBlank(header.value())) {
                    mapCols.put(header.order(), header.value());
                }
            }
        }
        return mapCols;
    }

    public static Field findField(Class<?> clazz, String fieldName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (Exception e) {
            // do nothing
        }

        if (field != null) {
            return field;
        }

        if (Object.class != clazz.getSuperclass()) {
            field = findField(clazz.getSuperclass(), fieldName);
        } else {
            for (Field f : clazz.getDeclaredFields()) {
                Type type = f.getGenericType();
                if (type instanceof Class) {
                    String[] arr = fieldName.split("\\.");
                    fieldName = arr[arr.length - 1];
                    field = findField((Class<?>) type, fieldName);
                    if (field != null) {
                        break;
                    }
                } else if (fieldName.equals(f.getName())) {
                    field = f;
                    break;
                }
            }
        }

        return field;
    }

    public static boolean isPositiveNumber(String val, int decimalPlaces) {
        String regex = "^[0-9]+\\.?([0-9]{0," + decimalPlaces + "})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(val);
        return matcher.matches();
    }

    public static double getFileSizeInKiloBytes(MultipartFile file) {
        return file.getSize() / 1024;
    }

    public static MultipartFile getFileImageFromList(List<MultipartFile> files) {
        return Utilities.returnNullInException(() -> files.stream()
                .filter(s -> s.getOriginalFilename().toLowerCase().endsWith(Constants.IMAGE_JPG_DOMAIN)
                        || s.getOriginalFilename().toLowerCase().endsWith(Constants.IMAGE_JPEG_DOMAIN)
                        || s.getOriginalFilename().toLowerCase().endsWith(Constants.IMAGE_PNG_DOMAIN)).findFirst().get());
    }

    public static List<MultipartFile> getAllFileImageFromList(List<MultipartFile> files) {
        if (CollectionUtils.isEmpty(files)) {
            return new ArrayList<>();
        }
        List<String> extensions = Arrays.asList(Constants.IMAGE_JPG_DOMAIN, Constants.IMAGE_JPEG_DOMAIN, Constants.IMAGE_PNG_DOMAIN);
        List<MultipartFile> fileAllows = new ArrayList<>();
        files.forEach(s -> {
            if (!Utilities.getRealMimeType(s).startsWith("image/")) {
                return;
            }
            String extension = Constants.DOT + getExtensionFile(s);
            if (!extensions.contains(extension.toLowerCase())) {
                return;
            }
            fileAllows.add(s);
        });
        return fileAllows;
    }

    public static MultipartFile getFileWordFromList(List<MultipartFile> files) {
        return Utilities.returnNullInException(() -> files.stream()
                .filter(s -> s.getOriginalFilename().toLowerCase().endsWith(Constants.WORD_DOMAIN)).findFirst().get());
    }

    public static double doubleFormatter(double val, String format) {
        return Double.parseDouble(new DecimalFormat(format).format(val));
    }

    public static String convertPhoneNumber(String phoneNumber) {
        if (StringUtils.isAllBlank(phoneNumber)) {
            return null;
        }
        phoneNumber = getValueAfterSplit(phoneNumber);
        if (phoneNumber.startsWith("+84")) {
            return phoneNumber;
        }
        return "+84" + phoneNumber.substring(1);
    }

    private static String getValueAfterSplit(String value) {
        value = value.trim();
        if (value.contains(Constants.COMMA)) {
            return value.split(Constants.COMMA)[0].trim();
        }
        if (value.contains(";")) {
            return value.split(";")[0].trim();
        }
        return value;
    }

    public static String getSingleEmail(String email) {
        try {
            if (StringUtils.isAllBlank(email)) {
                return null;
            }
            return getValueAfterSplit(email);
        } catch (Exception e) {
            return null;
        }
    }

    public static Double doubleFormatterTwo(Double val) {
        return Double.parseDouble(new DecimalFormat("#.##").format(val));
    }

    public static List<ImageResponse> buildListImageMinIO(String image) {
        List<ImageResponse> responses = new ArrayList<>();
        if (StringUtils.isAllBlank(image)) {
            return responses;
        }
        String arr[] = image.split(Constants.COMMA);
        for (String item : arr) {
            if (StringUtils.isAllBlank(item)) {
                continue;
            }
            String img[] = item.split(Constants.SLASH);
            ImageResponse imageResponse = new ImageResponse();
            imageResponse.setBucket(img[0]);
            imageResponse.setName(img[1]);
            responses.add(imageResponse);
        }
        return responses;
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        temp = temp.replaceAll("Đ", "D");
        return temp.replaceAll("đ", "d");
    }

    public static String randomFileName(MultipartFile file) {
        String extension = getExtensionFile(file);
        return Instant.now().toEpochMilli() + Constants.UNDERSCORE + UUID.randomUUID() + Constants.DOT + extension;
    }

    private static String getExtensionFile(MultipartFile file) {
        String[] fileFrags = Utilities.removeAccent(file.getOriginalFilename()).split("\\.");
        String extension = fileFrags[fileFrags.length - 1];
        return extension;
    }

    public static String getRealMimeType(MultipartFile file) {
        AutoDetectParser parser = new AutoDetectParser();
        Detector detector = parser.getDetector();
        try {
            Metadata metadata = new Metadata();
            TikaInputStream stream = TikaInputStream.get(file.getInputStream());
            org.apache.tika.mime.MediaType mediaType = detector.detect(stream, metadata);
            return mediaType.toString();
        } catch (IOException e) {
            return MimeTypes.OCTET_STREAM;
        }
    }

    public static List<String> copyPropertiesWithPermission(Object src, Object target, List<String> itemCodes, boolean... isGetData) {
        List<String> copyProps = new ArrayList<>();
        List<String> props = new ArrayList<>();
        if (isGetData.length > 0 && isGetData[0]) {
            props = findGrantedFields(target, itemCodes);
        } else {
            props = findGrantedFields(src, itemCodes);
        }
        Object srcValue;
        Object targetValue;
        List<Field> lstFields = new ArrayList<>(getAllFields(src.getClass()));

        if (CollectionUtils.isEmpty(props)) {
            return copyProps;
        }
        List<String> whiteListFieldsCommon = Arrays.asList("id", "createdBy", "createdAt", "updatedBy", "updatedAt");
        for (Field srcField : lstFields) {
            if (whiteListFieldsCommon.contains(srcField.getName()) || props.contains(srcField.getName())) {
                Field targetField;
                try {
                    targetField = findField(target.getClass(), srcField.getName());
                    if (targetField != null) {
                        srcField.setAccessible(true);
                        targetField.setAccessible(true);

                        if (targetField.getType() == srcField.getType()) {
                            srcValue = srcField.get(src);
                        } else {
                            srcValue = null;
                        }

                        targetValue = targetField.get(target);

                        if ((srcValue == null && targetValue != null) || (srcValue != null && !srcValue.equals(targetValue))) {
                            targetField.set(target, srcValue);
                            copyProps.add(srcField.getName());
                        }
                    }
                } catch (Exception e) {
                    logger.error("copyPropertiesWithPermission: {}", e.getMessage());
                }
            }
        }

        return copyProps;
    }

    private static List<String> findGrantedFields(Object src, List<String> itemCodes) {
        List<String> lstFields = new ArrayList<>();
        Class<? extends Object> clazz = src.getClass();
        Field[] fields = clazz.getDeclaredFields();

        String code = "";
        boolean required;
        List<ErrorDetail> errorDetails = new ArrayList<>();
        Object val;
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(NotNull.class) || f.isAnnotationPresent(NotBlank.class) || f.isAnnotationPresent(NotEmpty.class)) {
                required = true;
            } else {
                required = false;
            }

            if (f.isAnnotationPresent(Annotations.ItemCode.class)) {
                if (HttpMethod.POST.matches(BearerContextHolder.getContext().getHttpMethod()) && StringUtils.isNotBlank(f.getAnnotation(Annotations.ItemCode.class).valueOnCreate())) {
                    code = f.getAnnotation(Annotations.ItemCode.class).valueOnCreate();
                } else if (HttpMethod.PUT.matches(BearerContextHolder.getContext().getHttpMethod()) && StringUtils.isNotBlank(f.getAnnotation(Annotations.ItemCode.class).valueOnUpdate())) {
                    code = f.getAnnotation(Annotations.ItemCode.class).valueOnUpdate();
                }

                if (StringUtils.isBlank(code)) {
                    code = f.getAnnotation(Annotations.ItemCode.class).value();
                }

                try {
                    val = f.get(src);
                } catch (Exception e) {
                    val = null;
                }

                if (required && !itemCodes.contains(code) && val != null) {
                    throw new BusinessException(MessageCode.ERR_403);
                }

                if (itemCodes.contains(code)) {
                    lstFields.add(f.getName());
                }
            }
        }

        if (CollectionUtils.isNotEmpty(errorDetails)) {
            throw new BusinessException(errorDetails);
        }

        return lstFields;
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (ObjectUtils.allNotNull(clazz)) {
            for (Field field : clazz.getDeclaredFields()) {
                fields.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
