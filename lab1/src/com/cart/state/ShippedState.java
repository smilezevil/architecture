package com.cart.state;

import com.cart.model.Order;

/**
 * State: кінцевий стан — замовлення відправлене, подальших переходів немає.
 */
public class ShippedState implements OrderState {
    @Override
    public void next(Order order) {
        System.out.println("Замовлення вже відправлене, подальших переходів немає");
    }

    @Override
    public String getName() {
        return "SHIPPED";
    }
}