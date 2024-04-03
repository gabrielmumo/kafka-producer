package dev.gabrielmumo.kafka.event.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaEventProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaEventProducerApplication.class, args);
	}

}
