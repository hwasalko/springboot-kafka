package com.hwasalko.springbootkafka.rest.kafka.service;

import com.hwasalko.springbootkafka.kafka.MyProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    
    @Autowired
    private MyProducer myProducer;

    // 메세지 Produce
    public void sendKafkaMessage(String message){
        myProducer.send("", message);
    }




}
