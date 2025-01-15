package com.developer.cardmiscroservice.application.representation;

import com.developer.cardmiscroservice.domain.CardOfClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardOfClientResponseRequest {

    private String cardName;
    private String flagCard;
    private BigDecimal basicLimit;

    public static CardOfClientResponseRequest toResponse(CardOfClient clientCard) {
        return new CardOfClientResponseRequest(clientCard.getCard().getName(), clientCard.getCard().getFlagCard().toString(), clientCard.getCard().getBasicLimit());
    }
}
