package com.hwasalko.springbootkafka.rest.kafka.controller;

import com.hwasalko.springbootkafka.rest.kafka.service.KafkaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    
    private static Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/produce/{message}")
	public String produceMessage(@PathVariable String message) {
        
        kafkaService.sendKafkaMessage(message);
        
        return "Kafka Produce Message => " + message;
    }



}
