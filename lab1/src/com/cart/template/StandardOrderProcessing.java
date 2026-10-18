package com.cart.template;

import com.cart.model.ShoppingCart;

/**
 * Template Method: конкретна реалізація кроку розрахунку знижки
 * для звичайного клієнта.
 */
public class StandardOrderProcessing extends OrderProcessingTemplate {
    @Override
    protected double calculateDiscount(ShoppingCart cart) {
        return 5;
    }
}