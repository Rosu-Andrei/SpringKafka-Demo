package com.second.kafkaspringobject.service;

import com.second.kafkaspringobject.model.Person;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {
    private final String TOPIC = "practice_topic";

    @KafkaListener(topics = TOPIC, groupId = "Group100", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Person person) {
        System.out.println("Received person : " + person);
    }
}
