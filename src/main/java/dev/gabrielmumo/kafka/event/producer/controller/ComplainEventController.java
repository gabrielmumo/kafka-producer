package dev.gabrielmumo.kafka.event.producer.controller;

import dev.gabrielmumo.kafka.event.producer.dto.ComplainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplainEventController {

    Logger log = LoggerFactory.getLogger(ComplainEventController.class);

    @PostMapping("/v1/complains")
    public ResponseEntity<ComplainEvent> putComplainEvent(@RequestBody ComplainEvent complainEvent) {
        log.info("ComplainEvent: {} ", complainEvent);

        return ResponseEntity.status(HttpStatus.CREATED).body(complainEvent);
    }
}
