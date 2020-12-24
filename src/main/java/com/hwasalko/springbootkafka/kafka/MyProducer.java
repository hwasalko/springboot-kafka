package com.hwasalko.springbootkafka.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyProducer {

    private static Producer<String, String> producer;
    private static final String TOPIC_NAME = "my-test-topic";


    private static Logger logger = LoggerFactory.getLogger(MyProducer.class);

    
    // 생성자
    public MyProducer() {
        initKafkaProducer();
    }
    

    // Kafka Producer 초기화
    private void initKafkaProducer(){

        logger.info("\t[Kafka Producer] 초기화 시작 ");

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<>(properties);

        logger.info("\t[Kafka Producer] 초기화 종료 ");

    }
    
    
    public void send(String topic_name, String message) {
        
        logger.info("\t[Kafka Producer] 메세지 전송 시작");

        if( topic_name.length() == 0 )
            topic_name = TOPIC_NAME;
            
        logger.info("\t\t[Kafka Producer] TOPIC => " + topic_name);    
        logger.info("\t\t[Kafka Producer] MESSAGE => " + message);    
        


        ProducerRecord<String, String> record = new ProducerRecord<>( topic_name, message);

        try {
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                    logger.error("\t\t[Kafka Producer] 전송결과 실패 => " + exception.getMessage());    
                }else{
                    logger.info("\t\t[Kafka Producer] 전송결과 성공 => " + metadata.toString() );    
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.flush();
        }

        logger.info("\t[Kafka Producer] 메세지 전송 종료");
        
    }

    
}
