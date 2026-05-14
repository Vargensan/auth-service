package org.fix.bill.auth.api.event;

import org.fix.bill.infrastrucute.api.DomainEvent;

import java.util.HashMap;
import java.util.Map;

public class VerificationTokenDTO extends DomainEvent {

    private String eventType;
    private Map<String, Object> payload;

    public VerificationTokenDTO(Builder builder) {
        this.eventType = builder.eventType;
        this.payload = new HashMap<>(builder.payload);
    }

    public String getEventType() {
        return eventType;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String eventType;
        private Map<String, Object> payload = new HashMap<>();

        public Builder withEventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder withNextPayload(String key, Object value) {
            payload.put(key, value);
            return this;
        }

        public VerificationTokenDTO build() {
            return new VerificationTokenDTO(this);
        }

    }

}
