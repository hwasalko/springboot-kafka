package com.hwasalko.springbootkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootKafkaApplication {

	static Logger logger = LoggerFactory.getLogger(SpringApplication.class);

	public static void main(String[] args) {

		logger.info("########### 스프링부트 시작합니다(loading...) ##############");
		SpringApplication.run(SpringbootKafkaApplication.class, args);
		logger.info("########### 스프링부트 준비 완료 !! ##############");
	}

}