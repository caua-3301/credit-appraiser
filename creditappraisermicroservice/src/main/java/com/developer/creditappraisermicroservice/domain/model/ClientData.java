package com.developer.creditappraisermicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientData {

    private String name;
    private String cpf;
    private Integer age;
    private Long id;
}
