package com.cart.decorator;

import com.cart.discount.DiscountPolicy;

/**
 * Decorator: базовий клас-обгортка над DiscountPolicy. Реалізує той самий
 * інтерфейс, що і об'єкт, який обгортає, — тому декоратор можна підставити
 * скрізь, де очікується звичайний DiscountPolicy.
 */
public abstract class DiscountPolicyDecorator implements DiscountPolicy {
    protected final DiscountPolicy wrapped;

    public DiscountPolicyDecorator(DiscountPolicy wrapped) {
        this.wrapped = wrapped;
    }
}