package com.cart.strategy;

/**
 * Strategy: спільний інтерфейс для різних алгоритмів розрахунку
 * вартості доставки. Клієнтський код працює лише з цим інтерфейсом,
 * не знаючи, який саме алгоритм підставлено.
 */
public interface ShippingStrategy {
    double calculateShippingCost(double orderTotal);
}