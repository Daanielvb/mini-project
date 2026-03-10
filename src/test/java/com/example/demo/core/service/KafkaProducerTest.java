package com.example.demo.core.service;

import com.example.demo.core.gateway.impl.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaProducerTest {

    @Mock
    private KafkaTemplate<String, String> template;

    @InjectMocks
    private KafkaProducer producer;


    @Test
    void shouldPublishMessage(){
        when(template.send(any(), any()))
                .thenReturn(CompletableFuture.completedFuture(
                new SendResult(new ProducerRecord("topic", "message"),
                        new RecordMetadata(
                                new TopicPartition("a", 1), 0L, 0, 0, 0, 0)

                        )));

        producer.produce("test");
    }
}
