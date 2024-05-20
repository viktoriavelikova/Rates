package com.currency.entity;

import com.currency.JpaConverterJson;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "rates")
public class Rates {

    @Column(name = "id")
    @Id
    private UUID id;

    @Column(name = "base")
    private String base;
    @Column(name = "date")
    //    @JdbcTypeCode(TIMESTAMP)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
    @Column(name = "version_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime versionDate;
    @Convert(converter = JpaConverterJson.class)
    @Column(name = "rates",
            length = 5000)
    private Map<String, Double> rates;

    public Rates() {
    }

    public Rates(final String base, final LocalDateTime date, final LocalDateTime versionDate,
                 final Map<String, Double> rates) {
        this.base = base;
        this.date = date;
        this.versionDate = versionDate;
        this.rates = rates;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public String toString() {
        return "Rates{" +
                "id=" + id +
                ", base='" + base + '\'' +
                ", date=" + date +
                ", versionDate=" + versionDate +
                ", rates=" + rates +
                '}';
    }
}
