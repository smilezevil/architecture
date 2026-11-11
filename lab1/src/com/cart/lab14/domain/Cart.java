package com.cart.lab14.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Кошик покупця.
 */
public class Cart {
    private final String customerId;
    private final List<CartItem> items = new ArrayList<>();
    private double appliedDiscountAmount = 0.0;

    public Cart(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public Optional<CartItem> findItem(String productId) {
        return items.stream().filter(i -> i.getProductId().equals(productId)).findFirst();
    }

    public void addItem(String productId, int quantity) {
        Optional<CartItem> existing = findItem(productId);
        if (existing.isPresent()) {
            existing.get().increaseQuantity(quantity);
        } else {
            items.add(new CartItem(productId, quantity));
        }
    }

    public void removeItem(String productId, int quantity) {
        Optional<CartItem> existing = findItem(productId);
        if (existing.isEmpty()) {
            return;
        }
        CartItem item = existing.get();
        item.decreaseQuantity(quantity);
        if (item.getQuantity() <= 0) {
            items.remove(item);
        }
    }

    /**
     * Бізнес-правило 2: кошик не повинен бути порожнім для оформлення.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void setDiscountAmount(double amount) {
        this.appliedDiscountAmount = amount;
    }

    public double getDiscountAmount() {
        return appliedDiscountAmount;
    }
}