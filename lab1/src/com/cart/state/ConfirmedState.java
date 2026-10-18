package com.cart.state;

import com.cart.model.Order;

/**
 * State: замовлення підтверджене. Наступний можливий стан — відправлене.
 */
public class ConfirmedState implements OrderState {
    @Override
    public void next(Order order) {
        System.out.println("Перехід: CONFIRMED -> SHIPPED");
        order.setState(new ShippedState());
    }

    @Override
    public String getName() {
        return "CONFIRMED";
    }
}