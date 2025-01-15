package com.developer.creditappraisermicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ClientCard {

    private String name;
    private String flag;
    private BigDecimal basicLimit;
}
