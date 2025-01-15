package com.developer.cardmiscroservice.application;

import com.developer.cardmiscroservice.domain.CardOfClient;
import com.developer.cardmiscroservice.infra.repository.CardOfClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardOfClientService {

    private final CardOfClientRepository cardRepository;

    public List<CardOfClient> getByCpf(String cpf) {
        return cardRepository.findByCpf(cpf);
    }
}
