package com.cart.model;

import com.cart.discount.DiscountPolicy;
import com.cart.discount.NoDiscount;

import java.util.ArrayList;
import java.util.List;

/**
 * Кошик покупця.
 */
public class ShoppingCart {
    private final Customer customer;
    private final List<CartItem> items;
    private DiscountPolicy discountPolicy;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.discountPolicy = new NoDiscount();
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    /**
     * Creator: кошик сам створює CartItem, бо саме він
     * зберігає в собі ці об'єкти.
     */
    public void addItem(Product product, int quantity) {
        if (!product.isAvailable(quantity)) {
            System.out.println("Товару " + product.getName() + " недостатньо на складі.");
            return;
        }

        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().getId().equals(product.getId()));
    }

    /**
     * Information Expert: кошик знає весь список товарів, тому сам рахує суму.
     * Protected Variations: сама знижка рахується в discountPolicy,
     * кошику не важливо яка саме знижка застосована.
     */
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return discountPolicy.applyDiscount(total);
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Кошик покупця " + customer.getName() + ":\n");
        for (CartItem item : items) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append("Всього: ").append(getTotalPrice()).append(" грн");
        return sb.toString();
    }
}