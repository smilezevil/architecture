package com.cart.lab14.domain;

/**
 * Позиція товару в кошику.
 */
public class CartItem {
    private final String productId;
    private int quantity;

    public CartItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }
}