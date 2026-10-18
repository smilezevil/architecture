package com.cart.command;

import com.cart.controller.CartController;

/**
 * Command: конкретна команда — оформлення замовлення.
 */
public class CheckoutCommand implements Command {
    private final CartController controller;

    public CheckoutCommand(CartController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.checkout();
    }
}