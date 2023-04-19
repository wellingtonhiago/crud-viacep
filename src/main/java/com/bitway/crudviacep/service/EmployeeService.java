package com.bitway.crudviacep.service;

import com.bitway.crudviacep.model.Address;
import com.bitway.crudviacep.model.Client;
import com.bitway.crudviacep.model.Employee;

import java.math.BigDecimal;
import java.util.Map;

public class EmployeeService {

    public static Employee update(Employee employee, Map<String, Object> updates) {

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key) {
                case "cpf":
                    employee.setCpf((String) value);
                    break;
                case "name":
                    employee.setName((String) value);
                    break;
                case "email":
                    employee.setEmail((String) value);
                    break;
                case "salary":
                    employee.setSalary((BigDecimal) value);
                    break;
                case "cep":
                    employee.setCep((Address) value);
                    break;

            }
        }

        return employee;
    }
}
