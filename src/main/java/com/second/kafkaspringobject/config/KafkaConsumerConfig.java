package com.second.kafkaspringobject.config;

import com.second.kafkaspringobject.model.Person;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public Map<String, Object> configsConsumer() {
        Map<String, Object> configProp = new HashMap<>();
        configProp.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "https://advanced-beetle-11713-eu2-kafka.upstash.io:9092");
        configProp.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProp.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProp.put("sasl.mechanism", "SCRAM-SHA-256");
        configProp.put("security.protocol", "SASL_SSL");
        configProp.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required " +
                "username=\"YWR2YW5jZWQtYmVldGxlLTExNzEzJJWiNPRnLrK7heJN2nqlYP6aBD1NtV1K_Lg\" " +
                "password=\"ZDUyNGM4MTktNGIzZC00ODllLTk0ZWQtNTBiNjAyMTdiNTUx\";");
        configProp.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configProp.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return configProp;
    }

    @Bean
    public ConsumerFactory<String, Person> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(configsConsumer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Person> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Person> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
