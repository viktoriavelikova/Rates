package com.currency.repository;

import com.currency.RequestId;
import com.currency.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface RequestRepository extends JpaRepository<Request, RequestId> {

    Optional<Request> findByIdAndServiceName(final UUID id, final String serviceName);

    long countByDateBetweenAndServiceName(final LocalDateTime startDate, final LocalDateTime endDate,
                                          final String serviceName);

}
