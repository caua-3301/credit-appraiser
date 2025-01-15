package com.developer.creditappraisermicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnAvaliationClient {
    private List<AprovedCard> aprovedCards;
}
