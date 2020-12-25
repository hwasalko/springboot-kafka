package com.hwasalko.springbootkafka.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MyConsumer {
    
    private static Logger logger = LoggerFactory.getLogger(MyConsumer.class);
    

    private static final String TOPIC_NAME = "test1";
    private static final String BOOTSTRAP_SERVER = "localhost:9092";

    Consumer<String, String> consumer;

    // 생성자
    public MyConsumer(){
        initKafkaConsumer();
    }



    // 컨슈머 초기화
    private void initKafkaConsumer() {

        logger.info("[Kafka Consumer] initializing 시작");

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "springboot-client");

        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));
        
        logger.info("[Kafka Consumer] initializing 종료");

    }


    // 메세지 가져오기(1분마다 수행)
    @Scheduled(fixedRate = 1000)
    public void consume(){
        logger.info("---- (Kafka Consumer) Healthcheck !! ----");
        try {
            //do {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("\t => [Kafka Consumer] (topic : " + record.topic() + "  ,  offset : " + record.offset() + "  ,  partition : " + record.partition() + ")  " + record.value() );
                }

                try{
                    consumer.commitSync();
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            //} while (true);
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("[Kafka Consumer]] Exception 발생 => " + e.getMessage());
            // exception
        } finally {
            //consumer.close();
        }

    }

}
