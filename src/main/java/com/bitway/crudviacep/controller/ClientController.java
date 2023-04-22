package com.bitway.crudviacep.controller;

import com.bitway.crudviacep.model.Client;
import com.bitway.crudviacep.repo.ClientRepository;
import com.bitway.crudviacep.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clients;

    @PostMapping
    public ResponseEntity<Client> register(@RequestBody Client client) {
        return ResponseEntity.ok(clients.save(client));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Client> getClient(@PathVariable String cpf){
        return ResponseEntity.ok(clients.findByCPF(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Client> updateClient(@PathVariable String cpf, @RequestBody Map<String, Object> updates) {
        Client client = clients.findByCPF(cpf);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clients.save(ClientService.update(client, updates)));
    }


}



