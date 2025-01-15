package com.developer.creditappraisermicroservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableRabbit
public class CreditappraisermicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditappraisermicroserviceApplication.class, args);
	}

}
