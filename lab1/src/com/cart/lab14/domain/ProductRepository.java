package com.cart.lab14.domain;

import com.cart.lab14.infrastructure.JsonStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторій товарів. Зберігає у файл products.txt
 * у форматі "id|name|price|stockQuantity".
 * Якщо файл порожній — заповнює його тестовими даними (seed data).
 */
public class ProductRepository {

    private static final String FILE_NAME = "products.txt";
    private final JsonStorage storage = new JsonStorage();

    public ProductRepository() {
        seedIfEmpty();
    }

    private void seedIfEmpty() {
        if (storage.readLines(FILE_NAME).isEmpty()) {
            save(new Product("p1", "Ноутбук", 25000.0, 5));
            save(new Product("p2", "Мишка", 350.0, 20));
            save(new Product("p3", "Клавіатура", 700.0, 15));
        }
    }

    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        for (String line : storage.readLines(FILE_NAME)) {
            if (line.isBlank()) continue;
            String[] parts = line.split("\\|", -1);
            result.add(new Product(parts[0], parts[1],
                    Double.parseDouble(parts[2]), Integer.parseInt(parts[3])));
        }
        return result;
    }

    public Optional<Product> findById(String id) {
        for (Product p : findAll()) {
            if (p.getId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public void save(Product product) {
        List<Product> all = findAll();
        all.removeIf(p -> p.getId().equals(product.getId()));
        all.add(product);
        List<String> lines = new ArrayList<>();
        for (Product p : all) {
            lines.add(p.getId() + "|" + p.getName() + "|" + p.getPrice() + "|" + p.getStockQuantity());
        }
        storage.writeLines(FILE_NAME, lines);
    }
}