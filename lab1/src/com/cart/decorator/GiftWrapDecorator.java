package com.cart.decorator;

import com.cart.discount.DiscountPolicy;

/**
 * Decorator: додає до суми вартість подарункового пакування
 * ПІСЛЯ того, як базовий DiscountPolicy застосував знижку.
 * Так можна комбінувати будь-яку знижку з додатковою послугою,
 * не змінюючи жоден існуючий клас знижки.
 */
public class GiftWrapDecorator extends DiscountPolicyDecorator {
    private static final double WRAP_PRICE = 50;

    public GiftWrapDecorator(DiscountPolicy wrapped) {
        super(wrapped);
    }

    @Override
    public double applyDiscount(double totalPrice) {
        double discounted = wrapped.applyDiscount(totalPrice);
        System.out.println("Додано подарункове пакування: +" + WRAP_PRICE + " грн");
        return discounted + WRAP_PRICE;
    }
}