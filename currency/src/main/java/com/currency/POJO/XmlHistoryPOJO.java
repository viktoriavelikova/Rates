package com.currency.POJO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement(name = "command")
public class XmlHistoryPOJO {

    private UUID id;
    private History history;

    public History getHistory() {
        return history;
    }

    public UUID getId() {
        return id;
    }

    public static class History {

        private String consumer;
        private String currency;
        private int period;

        public String getConsumer() {
            return consumer;
        }

        public String getCurrency() {
            return currency;
        }

        public int getPeriod() {
            return period;
        }
    }
}
