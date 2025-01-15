package com.developer.creditappraisermicroservice.infra;

import com.developer.creditappraisermicroservice.domain.model.AprovedCard;
import com.developer.creditappraisermicroservice.domain.model.Card;
import com.developer.creditappraisermicroservice.domain.model.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "cardmiscroservice", path = "/cards")
public interface CardResources {

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClientCard>> getCardByCpfClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "incomeValue")
    public ResponseEntity<List<Card>> findByIncomeLessThanEqual(@RequestParam("incomeValue") Long incomeValue);
}
