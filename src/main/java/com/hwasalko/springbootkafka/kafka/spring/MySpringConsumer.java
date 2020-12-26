package com.hwasalko.springbootkafka.kafka.spring;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MySpringConsumer {
    
    private final Logger logger = LoggerFactory.getLogger(MySpringConsumer.class);


    @KafkaListener(topics = {"test1","test2"}, groupId = "springboot-client")
    public void consumeTest(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        logger.info("#### -> Consumed message -> [topic => " + consumerRecord.topic() + "  ,  offset => " + consumerRecord.offset() + "] " +  consumerRecord.value() );
    }



}
