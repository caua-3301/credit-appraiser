package com.developer.creditappraisermicroservice.application;

import com.developer.creditappraisermicroservice.application.exception.ClientDataNotFound;
import com.developer.creditappraisermicroservice.application.exception.ConectionMicroserviceException;
import com.developer.creditappraisermicroservice.application.exception.RequiredCardException;
import com.developer.creditappraisermicroservice.domain.model.*;
import com.developer.creditappraisermicroservice.infra.CardResources;
import com.developer.creditappraisermicroservice.infra.ClientResources;
import com.developer.creditappraisermicroservice.infra.msqueue.RequiredCardEmissionPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final ClientResources clientResources;
    private final CardResources cardResources;
    private final RequiredCardEmissionPublisher requiredCardEmissionPublisher;

    //método que consultará outros microservicos para obter as informações necessárias
    public ClientSituation findClientSituation(String cpf) throws ClientDataNotFound, ConectionMicroserviceException {

        try {
            ResponseEntity<ClientData> clientData = clientResources.getClient(cpf);
            ResponseEntity<List<ClientCard>> clientCards = cardResources.getCardByCpfClient(cpf);

            //esse builder vem do lombok, ele facilita a criação de objetos
            return ClientSituation.builder().clientData(clientData.getBody()).cards(clientCards.getBody()).build();
            //tratamento de exceção caso o cleinte não esteja registrado
        } catch (FeignException.FeignClientException err) {
            int status =  err.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFound();
            }

            throw new ConectionMicroserviceException(err.getMessage(), status);
        }
    }

    public ReturnAvaliationClient makeAvaliation(String cpf, Long income) throws ClientDataNotFound, ConectionMicroserviceException {
        try {
            //verificando, com base na renda do cleinte, os cartões que se enquadram a ele
            ResponseEntity<ClientData> clientData = clientResources.getClient(cpf);
            ResponseEntity<List<Card>> cardsResult = cardResources.findByIncomeLessThanEqual(income);

            List<Card> cards = cardsResult.getBody();

            List<AprovedCard> aprovedCards = cards.stream().map(card -> {

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal incomeDb = BigDecimal.valueOf(income);
                BigDecimal age = BigDecimal.valueOf(clientData.getBody().getAge());

                BigDecimal factor = age.divide(BigDecimal.valueOf(10));
                BigDecimal aprovedLimit = factor.multiply(basicLimit);

                AprovedCard aprovedCard = new AprovedCard();

                aprovedCard.setFlag(card.getFlag());
                aprovedCard.setName(card.getName());
                aprovedCard.setBasicLimit(aprovedLimit);

                return aprovedCard;

            }).collect(Collectors.toList());

            return new ReturnAvaliationClient(aprovedCards);

        } catch (FeignException.FeignClientException err) {
            int status =  err.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFound();
            }

            throw new ConectionMicroserviceException(err.getMessage(), status);
        }
    }

    public CardEmissionRequired requireCardEmission(DataCardEmission data) {
        try {
            requiredCardEmissionPublisher.requireCard(data);
            String protocol = UUID.randomUUID().toString();
            return new CardEmissionRequired(protocol);
        } catch (Exception err) {
            throw new RequiredCardException();
        }
    }
}
