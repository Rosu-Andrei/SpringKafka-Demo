package com.second.kafkaspringobject.config;

import com.second.kafkaspringobject.model.Person;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public Map<String, Object> configs() {
        Map<String, Object> configProp = new HashMap<>();
        configProp.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "https://advanced-beetle-11713-eu2-kafka.upstash.io:9092");
        configProp.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProp.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProp.put("sasl.mechanism", "SCRAM-SHA-256");
        configProp.put("security.protocol", "SASL_SSL");
        configProp.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required " +
                "username=\"YWR2YW5jZWQtYmVldGxlLTExNzEzJJWiNPRnLrK7heJN2nqlYP6aBD1NtV1K_Lg\" " +
                "password=\"ZDUyNGM4MTktNGIzZC00ODllLTk0ZWQtNTBiNjAyMTdiNTUx\";");
        return configProp;
    }

    @Bean
    public ProducerFactory<String, Person> producerFactory() {
        return new DefaultKafkaProducerFactory<>(configs());
    }

    @Bean
    public KafkaTemplate<String, Person> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
