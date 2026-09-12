package com.cart.util;

/**
 * Pure Fabrication: штучний клас, якого немає в реальній предметній
 * області, — створений тільки щоб форматувати ціну для виводу
 * і не навантажувати цим ShoppingCart чи Order.
 */
public class CartPriceFormatter {
    public static String format(double price) {
        return String.format("%.2f грн", price);
    }
}