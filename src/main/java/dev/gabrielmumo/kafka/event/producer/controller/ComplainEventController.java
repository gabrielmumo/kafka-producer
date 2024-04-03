package dev.gabrielmumo.kafka.event.producer.controller;

import dev.gabrielmumo.kafka.event.producer.dto.ComplainEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplainEventController {

    @PostMapping("/v1/complains")
    public ResponseEntity<ComplainEvent> putComplainEvent(@RequestBody ComplainEvent complainEvent) {

        return ResponseEntity.status(HttpStatus.CREATED).body(complainEvent);
    }
}
