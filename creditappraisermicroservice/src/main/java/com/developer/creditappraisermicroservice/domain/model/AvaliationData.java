package com.developer.creditappraisermicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvaliationData {
    private String cpf;
    private Long income;
}
