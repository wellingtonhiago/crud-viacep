package com.bitway.crudviacep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    String cpf;
    String name;
    String email;
    Address cep;



}
