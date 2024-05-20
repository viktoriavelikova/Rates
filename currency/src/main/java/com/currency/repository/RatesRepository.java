package com.currency.repository;

import com.currency.entity.Rates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RatesRepository extends JpaRepository<Rates, UUID> {

    Optional<Rates> findFirstByOrderByVersionDateDesc();

    List<Rates> findByVersionDateGreaterThan(final LocalDateTime date);
}

