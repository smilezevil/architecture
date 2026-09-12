package com.cart.discount;

/**
 * Без знижки — сума лишається без змін.
 */
public class NoDiscount implements DiscountPolicy {
    @Override
    public double applyDiscount(double totalPrice) {
        return totalPrice;
    }
}