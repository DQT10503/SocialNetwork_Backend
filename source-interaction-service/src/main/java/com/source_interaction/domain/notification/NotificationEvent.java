package com.source_interaction.domain.notification;

public class NotificationEvent {
    private final String topic;
    private final String key;
    private final Object value;

    public NotificationEvent(String topic, String key, Object value) {
        this.topic = topic;
        this.key = key;
        this.value = value;
    }

    public String getTopic() {
        return topic;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NotificationEvent [topic=" + topic + ", key=" + key + ", value=" + value + "]";
    }
}
