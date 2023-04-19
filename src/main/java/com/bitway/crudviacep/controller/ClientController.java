package com.bitway.crudviacep.controller;

import com.bitway.crudviacep.model.Client;
import com.bitway.crudviacep.model.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clients;

    @PostMapping("/register")
    public ResponseEntity<Client> register(@RequestBody Client client) {
        return ResponseEntity.ok(clients.save(client));
    }
}
