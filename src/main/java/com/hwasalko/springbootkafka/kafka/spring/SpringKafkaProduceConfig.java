package com.hwasalko.springbootkafka.kafka.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
public class SpringKafkaProduceConfig {
    
    // 공통 Config
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // See https://kafka.apache.org/documentation/#producerconfigs for more properties
        return props;
    }
    




    //String 메세지 프로듀서
    @Bean
    public KafkaTemplate<String, String> kafkaStringTemplate() {
        return new KafkaTemplate<String, String>(
            new DefaultKafkaProducerFactory<>(producerConfigs())
        );
    }

    // Byte 메세지 프로듀서
    //  - Serializer 를 Overriding
    @Bean
    public KafkaTemplate<String, byte[]> kafkaByteTemplate(ProducerFactory<String, byte[]> pf) {

        return new KafkaTemplate<String, byte[]>(
            new DefaultKafkaProducerFactory<>(producerConfigs()),
            Collections.singletonMap(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class) 
        );

    }

}
