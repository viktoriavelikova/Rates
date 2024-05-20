package com.currency.controller;

import com.currency.POJO.JsonRequestPOJO;
import com.currency.entity.Rates;
import com.currency.service.RatesService;
import com.currency.service.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JsonRatesControllerTest {


    @Test
    public void testGetCurrentInt() {
        // Arrange
        final RatesService mockRatesService = mock(RatesService.class);
        final RequestService mockRequestService = mock(RequestService.class);
        final JsonRatesController controller = new JsonRatesController(mockRatesService, mockRequestService);
        final JsonRequestPOJO jsonRequestPOJO = new JsonRequestPOJO(UUID.randomUUID(), 1715978546, "testClient",
                "EUR", 24);
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Service-Name", "testService");

        final Rates rates = createRates(jsonRequestPOJO.getDate());
        when(mockRatesService.getLatestRates()).thenReturn(rates);

        final ResponseEntity response = controller.getCurrent(jsonRequestPOJO, request);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(rates, response.getBody());
        verify(mockRequestService, times(1)).createRequest(any(), any(), any(), any(), any());
    }

    @Test
    public void testGetHistory() {
        final RatesService mockRatesService = mock(RatesService.class);
        final RequestService mockRequestService = mock(RequestService.class);
        final JsonRatesController controller = new JsonRatesController(mockRatesService, mockRequestService);
        final int timestamp = 1715978546;
        final String testClient = "testClient";
        final String eur = "EUR";
        final JsonRequestPOJO jsonRequestPOJO = new JsonRequestPOJO(UUID.randomUUID(), timestamp, testClient,
                eur, 24);
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Service-Name", "testService");

        final LocalDateTime date = jsonRequestPOJO.getDate();
        final LocalDateTime startPeriod = date.minusHours(jsonRequestPOJO.getPeriod());

        final Rates rates = createRates(date);
        when(mockRatesService.getAllRatesAfterDate(startPeriod)).thenReturn(
                Collections.singletonList(rates));

        final ResponseEntity response = controller.getHistory(jsonRequestPOJO, request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Collections.singletonList(rates), response.getBody());
        verify(mockRequestService, times(1)).createRequest(any(), any(), any(), any(), any());
    }

    private Rates createRates(final LocalDateTime date) {
        return new Rates("EUR", date, date, Collections.emptyMap());
    }
}