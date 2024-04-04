package dev.gabrielmumo.kafka.event.producer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabrielmumo.kafka.event.producer.dto.ComplainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ComplainEventsProducer {

    private static final Logger log = LoggerFactory.getLogger(ComplainEventsProducer.class);

    @Value("${spring.kafka.topic}")
    public String topic;

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ComplainEventsProducer(KafkaTemplate<Integer, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<SendResult<Integer, String>> sendComplainEvent(ComplainEvent complainEvent)
            throws JsonProcessingException {
        var key = complainEvent.complainId();
        var value = objectMapper.writeValueAsString(complainEvent);

        var completableFuture = kafkaTemplate.send(topic, key, value);

        return completableFuture.whenComplete((sendResult, throwable) -> {
            if(throwable != null) {
                handleFailure(key, value, throwable);
            } else {
                handleSuccess(key, value, sendResult);
            }
        });
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> sendResult) {
        log.info("Message: {} was sent successfully. Key: {} & partition: {}",
                value,
                key,
                sendResult.getRecordMetadata().partition()
        );
    }

    private void handleFailure(Integer key, String value, Throwable throwable) {
        log.error("Error sending message: {} with key: {}", value, key, throwable);
    }
}
