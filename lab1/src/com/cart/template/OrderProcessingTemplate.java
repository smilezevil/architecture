package com.cart.template;

import com.cart.model.ShoppingCart;

/**
 * Template Method: визначає незмінний скелет алгоритму обробки
 * замовлення (validate -> calculateDiscount -> confirm), а окремий
 * крок (розрахунок знижки) делегує підкласам через абстрактний метод.
 */
public abstract class OrderProcessingTemplate {
    public final void process(ShoppingCart cart) {
        validate(cart);
        double discount = calculateDiscount(cart);
        System.out.println("Застосована знижка в обробці: " + discount + "%");
        confirm(cart);
    }

    private void validate(ShoppingCart cart) {
        System.out.println("Крок 1: перевірка кошика");
    }

    protected abstract double calculateDiscount(ShoppingCart cart);

    private void confirm(ShoppingCart cart) {
        System.out.println("Крок 3: замовлення підтверджено, сума: " + cart.getTotalPrice() + " грн");
    }
}