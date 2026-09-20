package com.cart.util;

// High Cohesion — клас відповідає лише за одну задачу: форматування ціни
// Pure Fabrication — штучний клас, якого немає серед реальних сутностей магазину

public class CartPriceFormatter {
    public static String format(double price) {
        return String.format("%.2f грн", price);
    }
}