package com.developer.cardmiscroservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//permite que o spring configure-o
@EnableRabbit
public class CardmiscroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardmiscroserviceApplication.class, args);
	}

}
