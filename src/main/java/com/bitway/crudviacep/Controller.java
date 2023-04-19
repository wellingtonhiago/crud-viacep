package com.bitway.crudviacep;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping
    public ViaCEPEndereco getAddress() throws IOException {
        ViaCEPClient client = new ViaCEPClient();
        ViaCEPEndereco endereco = client.getEndereco("20930-040");
        return endereco;
    }
}
