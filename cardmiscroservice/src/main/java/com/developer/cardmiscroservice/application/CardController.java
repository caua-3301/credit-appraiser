package com.developer.cardmiscroservice.application;

import com.developer.cardmiscroservice.application.representation.CardSaveResource;
import com.developer.cardmiscroservice.domain.Card;
import com.developer.cardmiscroservice.application.representation.CardOfClientResponseRequest;
import com.developer.cardmiscroservice.domain.CardOfClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardService cardService;

    private final CardOfClientService clientCardService;

    @GetMapping
    public String test() {
        return HttpStatus.OK.toString();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CardSaveResource cardSaveResource) {
        Card card = cardSaveResource.toModel();

        cardService.save(card);

        return ResponseEntity.ok(card);
    }

    //busca com base na renda de um usuário
    @GetMapping(params = "incomeValue")
    public ResponseEntity<List<Card>> findByIncomeLessThanEqual(@RequestParam("incomeValue") Long incomeValue) {

        log.info(String.valueOf(incomeValue));

        List<Card> cards = cardService.findByIncomeLessThanEqual(incomeValue);
        return ResponseEntity.ok(cards);
    }

    //retorna os catões associados ao cpf informado
    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardOfClientResponseRequest>> getCardByCpfClient(@RequestParam("cpf") String cpf) {

        List<CardOfClient> clientCards = clientCardService.getByCpf(cpf);

        List<CardOfClientResponseRequest> clientCardResponses = new ArrayList<>();

        if (!clientCards.isEmpty()) {
            clientCardResponses = clientCardService.getByCpf(cpf).stream().map(CardOfClientResponseRequest::toResponse).collect(Collectors.toList());
        }

        return ResponseEntity.ok(clientCardResponses);
    }
}
