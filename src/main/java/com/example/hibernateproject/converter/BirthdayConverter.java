package com.example.hibernateproject.converter;

import com.example.hibernateproject.entity.Birthday;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.util.Optional;

@Converter(autoApply = true)
public class BirthdayConverter implements AttributeConverter<Birthday, Date> {

    @Override
    public Date convertToDatabaseColumn(Birthday birthday) {
        return Optional.ofNullable(birthday)
                .map(b -> b.localDate())
                .map(b -> Date.valueOf(b))
                .orElse(null);
    }

    @Override
    public Birthday convertToEntityAttribute(Date dbDate) {
        return Optional.ofNullable(dbDate)
                .map(date -> date.toLocalDate())
                .map(date -> new Birthday(date))
                .orElse(null);
    }
}
