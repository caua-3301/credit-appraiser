package com.developer.clientmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class ClientmicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientmicroserviceApplication.class, args);
	}

}
