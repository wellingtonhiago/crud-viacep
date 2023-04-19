package com.bitway.crudviacep.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("client")
public record Client (
        @Id
        String cpf,
        String name,
        String email,
        String cep
         ) {}
