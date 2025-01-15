package com.developer.cardmiscroservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //essa anotação informa que a string do enum que deve ser mapeda para o banco
    @Enumerated(EnumType.STRING)
    private FlagCard flagCard;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card(String name, FlagCard flagCard, BigDecimal income, BigDecimal basicLimit) {
        this.name = name;
        this.flagCard = flagCard;
        this.income = income;
        this.basicLimit = basicLimit;
    }
}
