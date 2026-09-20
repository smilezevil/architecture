package com.cart.discount;

/**
 * Знижка у відсотках від суми.
 */
public class PercentageDiscount implements DiscountPolicy {
    private final double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    // Polymorphism — один і той самий метод, різна поведінка в кожній реалізації

    @Override
    public double applyDiscount(double totalPrice) {
        return totalPrice - (totalPrice * percentage / 100);
    }
}