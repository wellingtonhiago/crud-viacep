package com.bitway.crudviacep.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("employee")
public record Employee(
        @Id
        String cpf,
        String name,
        String email,
        BigDecimal salary,
        String cep

) {

}
