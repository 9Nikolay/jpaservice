package com.luxoft.dbapp.converters;

import com.luxoft.dbapp.enums.Rating;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class RatingStringConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDesc();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (Rating rating : Rating.values()) {
            if (rating.getDesc().equals(dbData)) {
                return rating;
            }
        }
        throw new IllegalArgumentException(dbData + " not supported.");
    }

}
