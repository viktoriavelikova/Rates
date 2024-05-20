package com.currency.controller;

import com.currency.POJO.JsonRequestPOJO;
import com.currency.entity.Rates;
import com.currency.service.RatesService;
import com.currency.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class JsonRatesController {

    private final RatesService ratesService;
    private final RequestService requestService;

    public JsonRatesController(final RatesService ratesService, final RequestService requestService) {
        this.ratesService = ratesService;
        this.requestService = requestService;
    }

    @PostMapping("/json/current")
    public ResponseEntity getCurrent(@RequestBody final JsonRequestPOJO jsonRequestPOJO,
                                     final HttpServletRequest request) {
        try {
            final String serviceName = request.getHeader("Service-Name");
            requestService.createRequest(jsonRequestPOJO.getRequestId(),
                    "json/current", jsonRequestPOJO.getClient(),
                    jsonRequestPOJO.getDate(), serviceName);
            final Rates rates = ratesService.getLatestRates();
            return ResponseEntity.ok(rates);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/json/history")
    public ResponseEntity getHistory(@RequestBody final JsonRequestPOJO jsonRequestPOJO,
                                     final HttpServletRequest request) {
        try {
            final String serviceName = request.getHeader("Service-Name");
            requestService.createRequest(jsonRequestPOJO.getRequestId(), "json/history", jsonRequestPOJO.getClient(),
                    jsonRequestPOJO.getDate(), serviceName);
            final int period = jsonRequestPOJO.getPeriod();
            final LocalDateTime date = jsonRequestPOJO.getDate();
            final LocalDateTime startPeriod = date.minusHours(period);
            final List<Rates> rates = ratesService.getAllRatesAfterDate(startPeriod);
            return ResponseEntity.ok(rates);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
