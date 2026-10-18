package com.cart.state;

import com.cart.model.Order;

/**
 * State: початковий стан щойно створеного замовлення.
 * З нього можливий лише перехід у ConfirmedState.
 */
public class CreatedState implements OrderState {
    @Override
    public void next(Order order) {
        System.out.println("Перехід: CREATED -> CONFIRMED");
        order.setState(new ConfirmedState());
    }

    @Override
    public String getName() {
        return "CREATED";
    }
}