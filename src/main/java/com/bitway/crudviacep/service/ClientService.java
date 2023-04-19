package com.bitway.crudviacep.service;

import com.bitway.crudviacep.model.Address;
import com.bitway.crudviacep.model.Client;

import java.util.Map;

public class ClientService {

    public static Client update(Client client, Map<String, Object> updates) {

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key) {
                case "cpf":
                    client.setCpf((String) value);
                    break;
                case "name":
                    client.setName((String) value);
                    break;
                case "email":
                    client.setEmail((String) value);
                    break;
                case "cep":
                    client.setCep((Address) value);
                    break;
            }
        }

        return client;
    }
}
