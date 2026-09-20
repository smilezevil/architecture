package com.cart.model;

//Low Coupling — клас нічого не знає про ShoppingCart чи Customer

/**
 * Товар в інтернет-магазині.
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
     * Information Expert: товар сам знає свій залишок на складі,
     * тому сам перевіряє, чи вистачає його на потрібну кількість.
     */
    public boolean isAvailable(int quantity) {
        return stockQuantity >= quantity;
    }

    public void reduceStock(int quantity) {
        if (isAvailable(quantity)) {
            stockQuantity -= quantity;
        }
    }

    @Override
    public String toString() {
        return name + " (" + price + " грн)";
    }
}