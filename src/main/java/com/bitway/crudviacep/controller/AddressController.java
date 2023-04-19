package com.bitway.crudviacep.controller;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private ViaCEPClient viaCEPClient;

    public void EnderecoController(ViaCEPClient viaCEPClient) {
        this.viaCEPClient = viaCEPClient;
    }

    @GetMapping("/{cep}")
    public ViaCEPEndereco getEndereco(@PathVariable String cep) throws IOException {
        return viaCEPClient.getEndereco(cep);
    }
}
