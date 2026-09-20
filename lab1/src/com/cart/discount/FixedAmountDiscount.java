package com.cart.discount;

/**
 * Фіксована знижка на суму.
 */
public class FixedAmountDiscount implements DiscountPolicy {
    private final double amount;

    public FixedAmountDiscount(double amount) {
        this.amount = amount;
    }

    // Polymorphism — один і той самий метод, різна поведінка в кожній реалізації

    @Override
    public double applyDiscount(double totalPrice) {
        return Math.max(0, totalPrice - amount);
    }
}