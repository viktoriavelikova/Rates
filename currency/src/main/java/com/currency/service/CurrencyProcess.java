package com.currency.service;

import com.currency.POJO.RatesPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CurrencyProcess {

    private final RatesService ratesService;
    private final RabbitTemplate rabbitTemplate;
    @Value("${access.key}")
    private String accessKey;

    public CurrencyProcess(RatesService ratesService, RabbitTemplate rabbitTemplate) {
        this.ratesService = ratesService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(cron = "${scheduling.period}")
    public void getLatestCurrencyRate() {

        final String urlLink =
                "http://data.fixer.io/api/latest?access_key=" + accessKey;
        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlLink))
                .build();
        try {
            final HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            final String body = response.body();
            final ObjectMapper objectMapper = new ObjectMapper();
            final RatesPOJO ratesPOJO = objectMapper.readValue(body, RatesPOJO.class);
            ratesService.createRates(ratesPOJO);
            rabbitTemplate.convertAndSend("rates", body);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
