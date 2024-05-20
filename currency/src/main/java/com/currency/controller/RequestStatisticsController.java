package com.currency.controller;

import com.currency.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RestController
public class RequestStatisticsController {

    private final RequestService requestService;

    public RequestStatisticsController(final RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/statistics")
    public ResponseEntity getStatistics(@RequestParam(value = "Service-Name")final String serviceName,
                                        @RequestParam(value = "fromDate")final String fromDate,
                                        @RequestParam(value = "toDate")final String toDate) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        final LocalDateTime startDate = LocalDateTime.parse(fromDate, formatter);
        final LocalDateTime endDate = LocalDateTime.parse(toDate, formatter);
        long hoursBetween = ChronoUnit.HOURS.between(startDate, endDate);
        final LocalDateTime fiveDaysAgo = LocalDateTime.now().minusDays(5);
        if (startDate.isAfter(fiveDaysAgo) || endDate.isAfter(fiveDaysAgo) || hoursBetween < 24) {
            long countOfRequests = requestService.countByDateBetweenAndServiceName(startDate, endDate, serviceName);
            return ResponseEntity.ok(
                    "The count of requests from " + serviceName + " for period " + fromDate + "-" + toDate +
                            " is " + countOfRequests);
        } else {
            return ResponseEntity.badRequest()
                    .body("The statistics is not available for more than 5 days ago and more than 24 hours");
        }
    }
}
