package com.cart.model;

import com.cart.controller.CartController;
import com.cart.discount.PercentageDiscount;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("C1", "Анастасія", "nastyademyanchuk@mail.com");
        ShoppingCart cart = new ShoppingCart(customer);
        CartController controller = new CartController(cart);

        Product laptop = new Product("P1", "Ноутбук", 25000, 5);
        Product mouse = new Product("P2", "Мишка", 500, 20);

        controller.addProductToCart(laptop, 1);
        controller.addProductToCart(mouse, 2);

        cart.setDiscountPolicy(new PercentageDiscount(10));

        Order order = controller.checkout();
        System.out.println(order);
    }
}