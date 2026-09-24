package com.cart.model;

/**
 * Товар в інтернет-магазині.
 * Prototype: клас реалізує Cloneable, тому новий товар можна створити
 * копіюванням вже існуючого (наприклад, схожий товар з іншим id чи ціною),
 * а не описувати всі поля заново через new.
 */
public class Product implements Cloneable {
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

    public boolean isAvailable(int quantity) {
        return stockQuantity >= quantity;
    }

    public void reduceStock(int quantity) {
        if (isAvailable(quantity)) {
            stockQuantity -= quantity;
        }
    }

    /**
     * Prototype: створює копію поточного товару.
     * Використовується, коли треба швидко отримати новий об'єкт
     * на основі вже існуючого, замість ручного виклику new Product(...)
     * з повторенням усіх параметрів.
     */
    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Клонування Product не підтримується", e);
        }
    }

    @Override
    public String toString() {
        return name + " (" + price + " грн)";
    }
}