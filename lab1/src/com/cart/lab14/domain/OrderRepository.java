package com.cart.lab14.domain;

import com.cart.lab14.infrastructure.JsonStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторій замовлень. Зберігає у файл orders.txt.
 * Формат рядка: id|customerId|totalSum|status|productId:qty:price;productId:qty:price;...
 */
public class OrderRepository {

    private static final String FILE_NAME = "orders.txt";
    private final JsonStorage storage = new JsonStorage();

    public List<Order> findAll() {
        List<Order> result = new ArrayList<>();
        for (String line : storage.readLines(FILE_NAME)) {
            if (line.isBlank()) continue;
            result.add(parseLine(line));
        }
        return result;
    }

    public Optional<Order> findById(String id) {
        for (Order o : findAll()) {
            if (o.getId().equals(id)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }

    public String generateNextId() {
        return "o" + (findAll().size() + 1);
    }

    public void save(Order order) {
        List<Order> all = findAll();
        all.removeIf(o -> o.getId().equals(order.getId()));
        all.add(order);
        List<String> lines = new ArrayList<>();
        for (Order o : all) {
            lines.add(toLine(o));
        }
        storage.writeLines(FILE_NAME, lines);
    }

    private String toLine(Order order) {
        StringBuilder itemsPart = new StringBuilder();
        for (OrderItem item : order.getItems()) {
            if (itemsPart.length() > 0) itemsPart.append(";");
            itemsPart.append(item.getProductId()).append(":")
                    .append(item.getQuantity()).append(":")
                    .append(item.getUnitPrice());
        }
        return order.getId() + "|" + order.getCustomerId() + "|" + order.getTotalSum()
                + "|" + order.getStatus() + "|" + itemsPart;
    }

    private Order parseLine(String line) {
        String[] parts = line.split("\\|", -1);
        String id = parts[0];
        String customerId = parts[1];
        double totalSum = Double.parseDouble(parts[2]);
        OrderStatus status = OrderStatus.valueOf(parts[3]);

        List<OrderItem> items = new ArrayList<>();
        if (parts.length > 4 && !parts[4].isBlank()) {
            for (String itemStr : parts[4].split(";")) {
                String[] itemParts = itemStr.split(":");
                items.add(new OrderItem(itemParts[0], Integer.parseInt(itemParts[1]), Double.parseDouble(itemParts[2])));
            }
        }

        return new Order(id, customerId, items, totalSum, status);
    }
}