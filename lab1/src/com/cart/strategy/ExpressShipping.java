package com.cart.strategy;

/**
 * Strategy: експрес-доставка — вартість залежить від суми замовлення.
 */
public class ExpressShipping implements ShippingStrategy {
    @Override
    public double calculateShippingCost(double orderTotal) {
        return 150 + orderTotal * 0.02;
    }
}