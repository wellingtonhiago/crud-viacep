package com.bitway.crudviacep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
        @Id
        String cpf;
        String name;
        String email;
        BigDecimal salary;
        Address cep;

}
