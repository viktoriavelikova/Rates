package com.currency;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class RequestId implements Serializable {

    private UUID id;
    private String endpoint;

    public RequestId() {
    }

    public RequestId(UUID id, String endpoint) {
        this.id = id;
        this.endpoint = endpoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestId requestId = (RequestId) o;
        return Objects.equals(id, requestId.id) && Objects.equals(endpoint, requestId.endpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endpoint);
    }
}
