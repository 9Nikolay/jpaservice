package com.luxoft.dbapp.converters;

import com.luxoft.dbapp.enums.Rating;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


@Converter
public class RatingStringConverter implements AttributeConverter<Rating, String> {

    private static final Map<String, Rating> STRING_RATING_MAP =
            Arrays.stream(Rating.values()).collect(Collectors.toMap(r -> r.getDesc(), r -> r));

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null) {
            return null;
        }
        return rating.getDesc();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        return STRING_RATING_MAP.get(dbData);
    }

}
