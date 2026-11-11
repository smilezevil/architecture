package com.cart.lab14.domain;

/**
 * Доменна сутність "Товар".
 */
public class Product {
    private final String id;
    private final String name;
    private final double price;
    private int stockQuantity;

    public Product(String id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Бізнес-правило 1: перевірка, чи достатньо товару на складі.
     */
    public boolean hasEnoughStock(int requestedQty) {
        return stockQuantity >= requestedQty;
    }

    public void decreaseStock(int qty) {
        this.stockQuantity -= qty;
    }
}