package dev.gabrielmumo.kafka.event.producer.dto;

public record ComplainEvent(
        Integer complainId,
        ComplainEventType complainEventType,
        String eventMessage
) {
}
