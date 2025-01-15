package com.developer.clientmicroservice.application;

import com.developer.clientmicroservice.application.representation.ClientSaveRequest;
import com.developer.clientmicroservice.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    @Autowired
    private final ClientService clientService;

    @GetMapping
    public String status() {
        log.info("This instance was called");
        return HttpStatus.OK.toString();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClientSaveRequest clientSaveRequest) {
        Client client = clientSaveRequest.toModel();
        clientService.save(client);

        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}").buildAndExpand(client.getCpf()).toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity getClient( @RequestParam("cpf") String cpf) {
        Optional<Client> client = clientService.getByCpf(cpf);

        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client.get());
    }
}
