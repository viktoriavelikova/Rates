package com.currency.controller;

import com.currency.service.CurrencyProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final CurrencyProcess currencyProcess;

    @Autowired
    public TestController(CurrencyProcess currencyProcess) {
        this.currencyProcess = currencyProcess;
    }

    @PostMapping("/test")
    public void test(@RequestBody String currencyName) {
        currencyProcess.getLatestCurrencyRate();
    }
}
