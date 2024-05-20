package com.currency.controller;

import com.currency.POJO.XmlHistoryPOJO;
import com.currency.POJO.XmlRequestPOJO;
import com.currency.entity.Rates;
import com.currency.service.RatesService;
import com.currency.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class XmlRatesController {

    private final RatesService ratesService;
    private final RequestService requestService;

    public XmlRatesController(RatesService ratesService, RequestService requestService) {
        this.ratesService = ratesService;
        this.requestService = requestService;
    }

    @PostMapping(value = "/xml/command",
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity getCurrent(@RequestBody final XmlRequestPOJO xmlRequestPOJO,
                                     final HttpServletRequest request) {
        try {
            final String serviceName = request.getHeader("Service-Name");
            requestService.createRequest(xmlRequestPOJO.getId(), "xml/command",
                    xmlRequestPOJO.getGet().getConsumer(),
                    LocalDateTime.now(), serviceName);
            final Rates rates = ratesService.getLatestRates();
            return ResponseEntity.ok(rates);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/xml/history",
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity getHistory(@RequestBody final XmlHistoryPOJO xmlHistoryPOJO,
                                     final HttpServletRequest request) {
        try {
            final String serviceName = request.getHeader("Service-Name");
            requestService.createRequest(xmlHistoryPOJO.getId(), "xml/history",
                    xmlHistoryPOJO.getHistory().getConsumer(),
                    LocalDateTime.now(), serviceName);
            final int period = xmlHistoryPOJO.getHistory().getPeriod();
            final LocalDateTime date = LocalDateTime.now();
            final LocalDateTime startPeriod = date.minusHours(period);
            final List<Rates> rates = ratesService.getAllRatesAfterDate(startPeriod);
            return ResponseEntity.ok(rates);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
