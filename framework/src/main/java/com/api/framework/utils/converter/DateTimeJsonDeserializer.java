package com.api.framework.utils.converter;

import com.api.framework.exception.BusinessException;
import com.api.framework.utils.Constants;
import com.api.framework.utils.enumerator.ErrorCommon;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateTimeJsonDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        DateTimeFormatter fmt;
        Instant result;
        try {
            fmt = DateTimeFormatter.ofPattern(Constants.FULL_DATETIME_FORMAT_WITH_ZONE).withZone(Constants.UTC_ZONE_ID);
            result = Instant.from(fmt.parse(p.getText()));
        } catch (Exception e) {
            try {
                fmt = DateTimeFormatter.ofPattern(Constants.SHORT_DATETIME_FORMAT_HYPHEN).withZone(Constants.UTC_ZONE_ID);
                result = Instant.from(fmt.parse(p.getText()));
            } catch (Exception ex) {
                fmt = DateTimeFormatter.ofPattern(Constants.FULL_DATETIME_FORMAT_HYPHEN).withZone(Constants.UTC_ZONE_ID);
                result = Instant.from(fmt.parse(p.getText()));
            }
        }

        if (Objects.nonNull(result) && (result.isAfter(Constants.MAX_DATE) || result.isBefore(Constants.MIN_DATE))) {
            throw new BusinessException(ErrorCommon.ERR_DATE.name(), ErrorCommon.ERR_DATE.getValue(), p.getText());
        }
        return result;
    }
}
