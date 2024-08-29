package com.second.kafkaspringobject.controller;

import com.second.kafkaspringobject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.second.kafkaspringobject.service.KafkaProducerService;

@RestController
@RequestMapping("api/v1")
public class KafkaController {

    private final KafkaProducerService producerService;

    @Autowired
    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Person person) {
        this.producerService.sendMessage(person);
        return new ResponseEntity<>("Message was sent to kafka", HttpStatus.OK);
    }
}
