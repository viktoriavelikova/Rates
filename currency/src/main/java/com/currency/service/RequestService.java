package com.currency.service;

import com.currency.entity.Request;
import com.currency.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request createRequest(final UUID id, final String endpoint, final String consumer, final LocalDateTime date,
                                 final String serviceName) {
        final Optional<Request> requestOptional = requestRepository.findByIdAndServiceName(id, serviceName);
        if (requestOptional.isPresent()) {
            throw new IllegalArgumentException("Request already exists");
        } else {
            return requestRepository.save(new Request(id, endpoint, consumer, date, serviceName));
        }
    }

    public long countByDateBetweenAndServiceName(final LocalDateTime startDate, final LocalDateTime endDate,
                                                 final String serviceName) {
        return requestRepository.countByDateBetweenAndServiceName(startDate, endDate, serviceName);
    }
}
