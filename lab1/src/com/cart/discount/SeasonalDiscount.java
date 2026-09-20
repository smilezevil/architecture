package com.cart.discount;

/**
 * OCP: новий тип знижки додається як окремий клас,
 * що реалізує DiscountPolicy — жоден існуючий клас
 * (ShoppingCart, інші знижки) при цьому не змінюється.
 */
public class SeasonalDiscount implements DiscountPolicy {
    private final double seasonalPercentage;

    public SeasonalDiscount(double seasonalPercentage) {
        this.seasonalPercentage = seasonalPercentage;
    }

    @Override
    public double applyDiscount(double totalPrice) {
        System.out.println("Застосовано сезонну знижку " + seasonalPercentage + "%");
        return totalPrice - (totalPrice * seasonalPercentage / 100);
    }
}