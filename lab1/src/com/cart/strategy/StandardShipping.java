package com.cart.strategy;

/**
 * Strategy: стандартна доставка — фіксована вартість.
 */
public class StandardShipping implements ShippingStrategy {
    @Override
    public double calculateShippingCost(double orderTotal) {
        return 60;
    }
}