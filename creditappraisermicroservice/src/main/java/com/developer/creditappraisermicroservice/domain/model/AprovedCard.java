package com.developer.creditappraisermicroservice.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AprovedCard {
    private String name;
    private String flag;
    private BigDecimal basicLimit;
}
