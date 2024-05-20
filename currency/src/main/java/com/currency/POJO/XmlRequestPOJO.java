package com.currency.POJO;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement(name = "command")
public class XmlRequestPOJO {
    private UUID id;
    private Get get;

    public UUID getId() {
        return id;
    }

    public Get getGet() {
        return get;
    }

    @XmlElement
    public void setId(UUID id) {
        this.id = id;
    }

    @XmlElement
    public void setGet(Get get) {
        this.get = get;
    }

    public static class Get {
        private String consumer;
        private String currency;

        public String getConsumer() {
            return consumer;
        }

        public String getCurrency() {
            return currency;
        }

        // getters and setters

        @XmlElement
        public void setConsumer(String consumer) {
            this.consumer = consumer;
        }

        @XmlElement
        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }
}
