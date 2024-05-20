package com.currency.service;

import com.currency.POJO.RatesPOJO;
import com.currency.entity.Rates;
import com.currency.repository.RatesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatesService {

    private final RatesRepository ratesRepository;

    public RatesService(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    public Rates createRates(final RatesPOJO ratesPOJO) {
        final LocalDateTime date = ratesPOJO.getDate().atStartOfDay();
        return ratesRepository.save(
                new Rates(ratesPOJO.getBase(), date, ratesPOJO.getVersionDate(), ratesPOJO.getRates()));
    }
    public Rates getLatestRates() {
        return ratesRepository.findFirstByOrderByVersionDateDesc().orElseThrow(() -> new RuntimeException("No rates found"));
    }

    public List<Rates> getAllRatesAfterDate(LocalDateTime date) {
        List<Rates> rates = ratesRepository.findByVersionDateGreaterThan(date);
        if(rates.isEmpty())
            throw new RuntimeException("No rates found");
        return rates;
    }
}
