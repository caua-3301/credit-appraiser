package com.developer.creditappraisermicroservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.card-emission}")
    private String queueCardEmission;

    @Bean
    public Queue queueCardEmissionMe() {
        return new Queue(queueCardEmission, true);
    }
}
