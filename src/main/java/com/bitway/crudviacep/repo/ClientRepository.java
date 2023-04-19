package com.bitway.crudviacep.repo;

import com.bitway.crudviacep.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    @Query("{cpf:'?0'}")
    Client findByCPF(String cpf);

}
