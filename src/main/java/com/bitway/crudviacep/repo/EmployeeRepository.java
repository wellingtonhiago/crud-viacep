package com.bitway.crudviacep.repo;

import com.bitway.crudviacep.model.Client;
import com.bitway.crudviacep.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Query("{cpf:'?0'}")
    Employee findByCPF(String cpf);

    @Query("{cep:'?0'}")
    List<Employee> findAllByCEP(Iterable<String> string);
}
