package com.developer.creditappraisermicroservice.infra;

import com.developer.creditappraisermicroservice.domain.model.ClientCard;
import com.developer.creditappraisermicroservice.domain.model.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//informando o nome do microservice junto com o path
@FeignClient(value = "clientmicroservice", path = "/clients")
public interface ClientResources {

    //para obter um recurso ou um método de outro MS, deve-se realizar a assinatura do recuso abaixo
    @GetMapping(params = "cpf")
    public ResponseEntity<ClientData> getClient(@RequestParam("cpf") String cpf);

}
