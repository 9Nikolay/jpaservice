package com.luxoft.dbapp.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Converter
public class LocalDateTimeStampConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        if (localDateTime == null)
            return null;
        return Timestamp.valueOf(localDateTime.truncatedTo(ChronoUnit.SECONDS));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        if (timestamp == null)
            return null;
        return timestamp.toLocalDateTime().truncatedTo(ChronoUnit.SECONDS);
    }

}
