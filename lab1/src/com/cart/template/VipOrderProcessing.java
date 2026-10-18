package com.cart.template;

import com.cart.model.ShoppingCart;

/**
 * Template Method: конкретна реалізація кроку розрахунку знижки
 * для VIP-клієнта — вищий відсоток.
 */
public class VipOrderProcessing extends OrderProcessingTemplate {
    @Override
    protected double calculateDiscount(ShoppingCart cart) {
        return 20;
    }
}