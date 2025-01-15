package com.developer.creditappraisermicroservice.infra.msqueue;

import com.developer.creditappraisermicroservice.domain.model.DataCardEmission;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequiredCardEmissionPublisher {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queueCardsToEmission;

    public void requireCard(DataCardEmission dataCardEmission) throws JsonProcessingException {
        String json = convertIntoJson(dataCardEmission);
        //adicionando na fila o json do cartão a ser emitido
        rabbitTemplate.convertAndSend(queueCardsToEmission.getName(), json);
    }

    private String convertIntoJson(DataCardEmission data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);

        return json;
    }

}
