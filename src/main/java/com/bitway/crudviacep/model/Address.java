package com.bitway.crudviacep.model;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;


import java.io.IOException;

public class Address {

    private final String cep;
    private final ViaCEPClient viaCEPClient;

    public Address(String cep) {
        this.cep = cep;
        this.viaCEPClient = new ViaCEPClient();
    }

    public String getRua() {
        try {
            ViaCEPEndereco address = viaCEPClient.getEndereco(cep);
            return address.getLogradouro();
        } catch (IOException e) {
            System.err.println("Erro ao obter a rua do endereço: " + e.getMessage());
            return "";
        }
    }

    public String getBairro() {
        try {
            ViaCEPEndereco address = viaCEPClient.getEndereco(cep);
            return address.getBairro();
        } catch (IOException e) {
            System.err.println("Erro ao obter o bairro do endereço: " + e.getMessage());
            return "";
        }
    }

    public String getCidade() {
        try {
            ViaCEPEndereco address = viaCEPClient.getEndereco(cep);
            return address.getLocalidade();
        } catch (IOException e) {
            System.err.println("Erro ao obter a cidade do endereço: " + e.getMessage());
            return "";
        }
    }

    public String getEstado() {
        try {
            ViaCEPEndereco address = viaCEPClient.getEndereco(cep);
            return address.getUf();
        } catch (IOException e) {
            System.err.println("Erro ao obter o estado do endereço: " + e.getMessage());
            return "";
        }
    }

    public String getComplemento() {
        try {
            ViaCEPEndereco address = viaCEPClient.getEndereco(cep);
            return address.getComplemento();
        } catch (IOException e) {
            System.err.println("Erro ao obter o complemento do endereço: " + e.getMessage());
            return "";
        }
    }

    public String getCEP() {
        try {
            ViaCEPEndereco address = viaCEPClient.getEndereco(cep);
            return address.getCep();
        } catch (IOException e) {
            System.err.println("Erro ao obter o CEP do endereço: " + e.getMessage());
            return "";
        }
    }

    public String getIBGE() {
        try {
            ViaCEPEndereco address = viaCEPClient.getEndereco(cep);
            return address.getIbge();
        } catch (IOException e) {
            System.err.println("Erro ao obter o IBGE do endereço: " + e.getMessage());
            return "";
        }
    }
}
