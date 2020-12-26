package com.hwasalko.springbootkafka.rest.kafka.controller;

import com.hwasalko.springbootkafka.kafka.spring.MySpringProducer;
import com.hwasalko.springbootkafka.rest.kafka.service.KafkaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    
    
    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private MySpringProducer mySpringProducer;


    
    @GetMapping(value = "/spring/produce/{topic_name}/{message}")
    public void sendMessageToKafkaTopic(
            @PathVariable("topic_name") String topic_name,
            @PathVariable("message") String message
        ) {
        mySpringProducer.sendStringMessage(topic_name, message);
    }



    @GetMapping(value = "/spring/produce/byte/{topic_name}/{message}")
    public void sendByteMessageToKafkaTopic(
            @PathVariable("topic_name") String topic_name,
            @PathVariable("message") String message
        ) {
        mySpringProducer.sendByteMessage(topic_name, message.getBytes() );
    }


    /*
    @GetMapping("/normal/produce/{message}")
	public String produceMessage(@PathVariable String message) {
        
        kafkaService.sendKafkaMessage(message);
        
        return "Kafka Produce Message => " + message;
    }
    */




    


}
