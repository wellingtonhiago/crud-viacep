package com.bitway.crudviacep.config;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViaCEPConfig {

    @Bean
    public ViaCEPClient viaCEPClient() {
        return new ViaCEPClient();
    }
}
