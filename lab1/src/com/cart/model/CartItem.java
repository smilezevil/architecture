package com.cart.model;

/**
 * Одна позиція в кошику: товар + кількість.
 */
public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Information Expert: CartItem має і ціну, і кількість,
     * тому сам рахує суму по собі.
     */
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        this.quantity = Math.max(0, this.quantity - amount);
    }

    @Override
    public String toString() {
        return product.getName() + " x" + quantity + " = " + getSubtotal() + " грн";
    }
}