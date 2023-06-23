package com.almadavic.securitystandard.config.kafka.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.almadavic.securitystandard.config.kafka.event.UserCreatedEvent;
import com.almadavic.securitystandard.config.kafka.event.UserCreatedEventMapper;
import com.almadavic.securitystandard.config.kafka.service.ProducerKafkaService;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@Configuration
public class ProducerKafkaConfig {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Autowired
    private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    @Bean
    public ProducerFactory<String, UserCreatedEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, UserCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerKafkaService producerKafkaService() {
        return new ProducerKafkaService();
    }

    @Bean
    public UserCreatedEventMapper userCreatedEventMapper() {
        return new UserCreatedEventMapper();
    }
}