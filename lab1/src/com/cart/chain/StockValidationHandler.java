package com.cart.chain;

import com.cart.model.ShoppingCart;

/**
 * Chain of Responsibility: перевіряє, що кошик не порожній,
 * перед тим як передати запит далі по ланцюжку.
 */
public class StockValidationHandler extends OrderValidationHandler {
    @Override
    public void handle(ShoppingCart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Перевірка складу: кошик порожній, замовлення неможливе");
            return;
        }
        System.out.println("Перевірка складу: товари в наявності, ОК");
        super.handle(cart);
    }
}