package com.cart.lab14.domain;

import com.cart.lab14.infrastructure.JsonStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторій покупців. Зберігає дані у файл customers.txt
 * у форматі "id|name|email" (один покупець — один рядок).
 */
public class CustomerRepository {

    private static final String FILE_NAME = "customers.txt";
    private final JsonStorage storage = new JsonStorage();

    public List<Customer> findAll() {
        List<Customer> result = new ArrayList<>();
        for (String line : storage.readLines(FILE_NAME)) {
            if (line.isBlank()) continue;
            String[] parts = line.split("\\|", -1);
            result.add(new Customer(parts[0], parts[1], parts[2]));
        }
        return result;
    }

    public String generateNextId() {
        return "c" + (findAll().size() + 1);
    }

    public Optional<Customer> findByEmail(String email) {
        for (Customer c : findAll()) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    public void save(Customer customer) {
        String line = customer.getId() + "|" + customer.getName() + "|" + customer.getEmail();
        storage.appendLine(FILE_NAME, line);
    }
}