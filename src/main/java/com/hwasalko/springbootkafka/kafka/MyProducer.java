package com.hwasalko.springbootkafka.kafka;

import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyProducer {

    private static Producer<String, String> producer;
    private static final String TOPIC_NAME = "test1";


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
        
        if( topic_name.length() == 0 )
            topic_name = TOPIC_NAME;
            
        ProducerRecord<String, String> record = new ProducerRecord<>( topic_name, message);

        try {
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                    logger.error("\t\t[Kafka Producer] 전송결과 실패 => " + exception.getMessage());    
                }else{
                    logger.info("[Kafka Producer] (topic : " + metadata.topic() + "  ,  offset : " + metadata.offset() + "  ,  partition : " + metadata.partition() + ") => " + message);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.flush();
        }

        
    }


    @Scheduled(fixedRate = 5000)
    private void messageProduceBatch(){
        send("", UUID.randomUUID().toString() );
    }

    
}
