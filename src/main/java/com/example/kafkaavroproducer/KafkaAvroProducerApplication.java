package com.example.kafkaavroproducer;

import com.example.avro.data.Avro01;
import com.example.kafkaavroproducer.producer.Avro01Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class KafkaAvroProducerApplication implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(KafkaAvroProducerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroProducerApplication.class, args);
	}

	@Autowired
	private Avro01Producer producer;
	@Override
	public void run(String... args) throws Exception {
		// Géneration d'une donnée avro
		var data = Avro01.newBuilder().setActive(false).setFullName("ABC-" + ThreadLocalRandom.current().nextInt())
				.setMaritalStatus("SINGLE").build();
		LOG.info("======= {}", data);
		producer.send(data);
	}
}
