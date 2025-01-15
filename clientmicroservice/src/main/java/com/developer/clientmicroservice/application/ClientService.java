package com.developer.clientmicroservice.application;

import com.developer.clientmicroservice.domain.Client;
import com.developer.clientmicroservice.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
/*essa anotação cria um construtor em tempo de compalição de forma automática levando em conta os atributos
* da classe, nesse caso, está criando para 'clientRepository'*/
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    /*A anotação abaixo informa que a operação é do tipo transacional no banco;
    dessa forma o spring cuida do commit ou rollback de forma automátioca, além do encapsulamento da transação*/
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getByCpf(String cpf) {
        return clientRepository.findByCpf(cpf);
    }
}
