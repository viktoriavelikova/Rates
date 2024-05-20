package com.currency;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParseDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
        final String dateFormatString ="yyyy-MM-dd";;
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormatString);
        return LocalDate.parse(p.getValueAsString(), dateTimeFormatter);
    }
}
