package com.cart.controller;

import com.cart.model.Order;
import com.cart.model.Product;
import com.cart.model.ShoppingCart;

/**
 * Controller: приймає запити (наприклад від UI) і керує
 * роботою кошика й оформленням замовлення.
 * Indirection: UI звертається не напряму до кошика, а через цей клас.
 */
public class CartController {
    private final ShoppingCart cart;

    public CartController(ShoppingCart cart) {
        this.cart = cart;
    }

    public void addProductToCart(Product product, int quantity) {
        cart.addItem(product, quantity);
        System.out.println("Контролер: додано " + product.getName() + " x" + quantity);
    }

    public void removeProductFromCart(Product product) {
        cart.removeItem(product);
        System.out.println("Контролер: видалено " + product.getName());
    }

    public Order checkout() {
        Order order = new Order(cart);
        order.confirm();
        cart.clear();
        return order;
    }
}