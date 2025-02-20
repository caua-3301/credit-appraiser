package com.developer.cardmiscroservice.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DataCardEmission {
    private Long idCard;
    private String cpf;
    private String local;
    private BigDecimal basicLimit;
}