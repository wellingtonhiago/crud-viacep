package com.bitway.crudviacep.controller;

import com.bitway.crudviacep.model.Employee;
import com.bitway.crudviacep.repo.EmployeeRepository;
import com.bitway.crudviacep.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employees;

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        return ResponseEntity.ok(employees.save(employee));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String cpf){
        return ResponseEntity.ok(employees.findByCPF(cpf));
    }

    @PatchMapping("/update/{cpf}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String cpf, @RequestBody Map<String, Object> updates) {
        Employee employee = employees.findByCPF(cpf);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employees.save(EmployeeService.update(employee, updates)));
    }

    @DeleteMapping("/delete/{cpf}")
    public void deleteEmployee(@PathVariable String cpf){
        employees.delete(employees.findByCPF(cpf));
    }

    @GetMapping("/count/{valor}")
    public int count(@PathVariable String valor){
        return employees.findAllByCEP(Collections.singleton(valor)).size();
    }

}
