package com.cart.command;

import com.cart.controller.CartController;
import com.cart.model.Product;

/**
 * Command: конкретна команда — додавання товару в кошик.
 * Зберігає всі дані, потрібні для виконання дії, і виконує її
 * лише при виклику execute(), а не одразу при створенні.
 */
public class AddToCartCommand implements Command {
    private final CartController controller;
    private final Product product;
    private final int quantity;

    public AddToCartCommand(CartController controller, Product product, int quantity) {
        this.controller = controller;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        controller.addProductToCart(product, quantity);
    }
}