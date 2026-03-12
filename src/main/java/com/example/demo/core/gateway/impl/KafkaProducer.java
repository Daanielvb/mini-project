package com.example.demo.core.gateway.impl;

import com.example.demo.core.gateway.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
@Slf4j
@Qualifier("kafka")
public class KafkaProducer implements Producer {

    @Value("${app.topic_name:test-topic}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void produce(String message) {
        log.info("Sending message {} to Kafka topic {}", message, topicName);
        kafkaTemplate.send(topicName, message);
    }
}
