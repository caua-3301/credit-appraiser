package com.developer.cardmiscroservice.application.representation;

import com.developer.cardmiscroservice.domain.Card;
import com.developer.cardmiscroservice.domain.FlagCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveResource {

    private String name;
    private FlagCard flagCard;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card toModel() {
        return new Card(name, flagCard, income, basicLimit);
    }
}
