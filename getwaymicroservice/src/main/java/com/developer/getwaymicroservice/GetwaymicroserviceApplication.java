package com.developer.getwaymicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
//perimite que outros servicos conhecam esse gateway
@EnableDiscoveryClient
public class GetwaymicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetwaymicroserviceApplication.class, args);
	}

	//bean responsável pelo roteamento entre os servicos
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		//retornando as rotas possível (dos meus servicos/microservicos)
		return builder
				.routes()
				/*o .route() define a rota para um detemrinado microservico;
				* para isso, ele recebe um lambda expression, que recebe um objeto da rota
				* com esse objeto definimos com route.path("x") que toda corespondecia dessa rota sera direcionada para a uri do balanceador de carga */
					.route(route -> route.path("/clients/**").uri("lb://clientmicroservice"))
					.route(route -> route.path("/cards/**").uri("lb://cardmiscroservice"))
					.route(route -> route.path("/credit-appraiser/**").uri("lb://creditappraisermicroservice"))
				.build();
	}

}
