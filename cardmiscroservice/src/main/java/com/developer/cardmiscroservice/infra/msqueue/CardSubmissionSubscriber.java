package com.developer.cardmiscroservice.infra.msqueue;

import com.developer.cardmiscroservice.domain.Card;
import com.developer.cardmiscroservice.domain.CardOfClient;
import com.developer.cardmiscroservice.domain.DataCardEmission;
import com.developer.cardmiscroservice.infra.repository.CardOfClientRepository;
import com.developer.cardmiscroservice.infra.repository.CardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//inscrevendo o componente (cosumer) na fila (demonstrando interesse)
@Component
@RequiredArgsConstructor
public class CardSubmissionSubscriber {

    private final CardRepository cardRepository;

    private final CardOfClientRepository cardOfClientRepository;

    //métod que receberá a mensagem da fila
    //anotação que informamos a fila que esse consumer deve ouvir
    @RabbitListener(queues = "${mq.queues.card-emission}")
    public void receiveEmissionRequest(@Payload String payload){

        try {
            ObjectMapper mapper = new ObjectMapper();
            DataCardEmission card = mapper.readValue(payload, DataCardEmission.class);

            Card cardNew = cardRepository.findById(card.getIdCard()).orElseThrow();
            CardOfClient client = new CardOfClient();

            client.setCard(cardNew);
            client.setCpf(card.getCpf());
            client.setBasicLimit(card.getBasicLimit());

            cardOfClientRepository.save(client);

        } catch (JsonProcessingException err) {
            err.printStackTrace();
        }
    }
}
