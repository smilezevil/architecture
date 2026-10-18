package com.cart.chain;

import com.cart.model.ShoppingCart;

/**
 * Chain of Responsibility: базовий обробник ланцюжка перевірок кошика.
 * Кожен конкретний обробник або сам перевіряє умову, або передає
 * запит далі по ланцюжку наступному обробнику.
 */
public abstract class OrderValidationHandler {
    protected OrderValidationHandler next;

    public OrderValidationHandler setNext(OrderValidationHandler next) {
        this.next = next;
        return next;
    }

    public void handle(ShoppingCart cart) {
        if (next != null) {
            next.handle(cart);
        }
    }
}