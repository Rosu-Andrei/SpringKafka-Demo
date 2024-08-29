package com.second.kafkaspringobject.service;

import com.second.kafkaspringobject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Person> kafkaTemplate;
    private final String TOPIC = "practice_topic";

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Person> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Person person) {
        kafkaTemplate.send(TOPIC, person);
    }
}
