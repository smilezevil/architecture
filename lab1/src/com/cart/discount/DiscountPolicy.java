package com.cart.discount;

/**
 * Спільний інтерфейс для всіх видів знижок.
 * Protected Variations: кошик працює тільки з цим інтерфейсом,
 * тому нові типи знижок можна додавати, не змінюючи код кошика.
 */
public interface DiscountPolicy {
    double applyDiscount(double totalPrice);
}