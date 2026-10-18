package com.cart.chain;

import com.cart.model.ShoppingCart;

/**
 * Chain of Responsibility: перевіряє коректність застосованої суми
 * після знижки, останній обробник у ланцюжку.
 */
public class DiscountValidationHandler extends OrderValidationHandler {
    @Override
    public void handle(ShoppingCart cart) {
        if (cart.getTotalPrice() < 0) {
            System.out.println("Перевірка знижки: сума від'ємна, помилка");
            return;
        }
        System.out.println("Перевірка знижки: сума коректна, ОК");
        super.handle(cart);
    }
}