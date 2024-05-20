package com.currency.POJO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRequestPOJO {

    private UUID requestId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]X")
    private LocalDateTime date;
    private String client;
    private String currency;
    private int period;

    @JsonCreator
    public JsonRequestPOJO(@JsonProperty("requestId") final UUID requestId,
                           @JsonProperty("timestamp") final long timestamp,
                           @JsonProperty("client") final String client,
                           @JsonProperty("currency") final String currency,
                           @JsonProperty("period") final int period) {
        this.requestId = requestId;
        this.date = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp*1000), ZoneId.systemDefault());
        this.client = client;
        this.currency = currency;
        this.period = period;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getClient() {
        return client;
    }

    public String getCurrency() {
        return currency;
    }

    public int getPeriod() {
        return period;
    }
}
