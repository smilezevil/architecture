package com.cart.discount;

/**
 * Без знижки — сума лишається без змін.
 */
public class NoDiscount implements DiscountPolicy {

    // Polymorphism — один і той самий метод, різна поведінка в кожній реалізації

    @Override
    public double applyDiscount(double totalPrice) {
        return totalPrice;
    }
}