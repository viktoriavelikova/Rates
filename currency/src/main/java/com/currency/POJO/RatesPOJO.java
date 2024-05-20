package com.currency.POJO;

import com.currency.ParseDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesPOJO {

    private boolean success;
    private String base;
    @JsonDeserialize(using = ParseDeserializer.class)
    private LocalDate date;
    private LocalDateTime versionDate;
    private Map<String, Double> rates;

    @JsonCreator
    public RatesPOJO(@JsonProperty("success") final boolean success, @JsonProperty("base") final String base,
                     @JsonProperty("date") final LocalDate date,
                     @JsonProperty("timestamp") final long timestamp,
                     @JsonProperty("rates") final Map<String, Double> rates) {
        this.success = success;
        this.base = base;
        this.date = date;
        this.versionDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp * 1000), ZoneId.systemDefault());
        this.rates = rates;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getBase() {
        return base;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
