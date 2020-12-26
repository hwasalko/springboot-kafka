package com.hwasalko.springbootkafka.kafka.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MySpringProducer {

    private static final Logger logger = LoggerFactory.getLogger(MySpringProducer.class);
    
    @Autowired
    private KafkaTemplate<String, String> kafkaStringTemplate;

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaByTemplate;


    public void sendStringMessage(String topic_name, String message) {

        logger.info("#### -> Producing String message -> [topic => " + topic_name + "] " + message );
        kafkaStringTemplate.send(topic_name, message);
        
    }


    public void sendByteMessage(String topic_name, byte[] byte_message) {

        logger.info("#### -> Producing Byte message -> [topic => " + topic_name + "] " + byte_message.toString() );
        kafkaByTemplate.send(topic_name, byte_message);
        
    }

}
