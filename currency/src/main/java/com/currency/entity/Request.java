package com.currency.entity;

import com.currency.RequestId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "request")
@Entity
@IdClass(RequestId.class)
public class Request implements Serializable {

    @Column(name = "id")
    @Id
    private UUID id;
    @Column(name = "endpoint")
    @Id
    private String endpoint;

    @Column(name = "consumer")
    private String consumer;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @Column(name = "service_name")
    private String serviceName;


    public Request() {
    }

    public Request(final UUID id, final String endpoint, final String consumer, final LocalDateTime date,
                   final String serviceName) {
        this.id = id;
        this.endpoint = endpoint;
        this.consumer = consumer;
        this.date = date;
        this.serviceName = serviceName;
    }

    public String getConsumer() {
        return consumer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getServiceName() {
        return serviceName;
    }

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
