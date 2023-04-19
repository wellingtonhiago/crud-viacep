//package com.bitway.crudviacep.service;
//
//import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//@Component
//public class ViaCEPClient {
//
//    private final RestTemplate restTemplate;
//
//    public ViaCEPClient(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public ViaCEPEndereco getEndereco(String cep) {
//        String url = "https://viacep.com.br/ws/" + cep + "/json/";
//        return restTemplate.getForObject(url, ViaCEPEndereco.class);
//    }
//}
