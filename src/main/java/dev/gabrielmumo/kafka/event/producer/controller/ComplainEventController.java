package dev.gabrielmumo.kafka.event.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.gabrielmumo.kafka.event.producer.dto.ComplainEvent;
import dev.gabrielmumo.kafka.event.producer.producer.ComplainEventsProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplainEventController {
    private static final Logger log = LoggerFactory.getLogger(ComplainEventController.class);

    private final ComplainEventsProducer complainEventsProducer;

    public ComplainEventController(ComplainEventsProducer complainEventsProducer) {
        this.complainEventsProducer = complainEventsProducer;
    }

    @PostMapping("/v1/complains")
    public ResponseEntity<ComplainEvent> putComplainEvent(@RequestBody ComplainEvent complainEvent)
            throws JsonProcessingException {
        log.info("ComplainEvent: {} ", complainEvent);

        complainEventsProducer.sendComplainEvent(complainEvent);

        return ResponseEntity.status(HttpStatus.CREATED).body(complainEvent);
    }
}
