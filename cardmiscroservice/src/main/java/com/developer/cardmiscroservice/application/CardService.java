package com.developer.cardmiscroservice.application;

import com.developer.cardmiscroservice.domain.Card;
import com.developer.cardmiscroservice.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> findByIncomeLessThanEqual(Long limit) {

        BigDecimal incomeBigDecimal = BigDecimal.valueOf(limit);

        //o tipo do parametro deve ser o mesmo do atributo registrado na entidade no JpaRepository
        return cardRepository.findByIncomeLessThanEqual(incomeBigDecimal);
    }
}
