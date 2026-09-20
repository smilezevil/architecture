package com.cart.model;

import com.cart.controller.CartController;
import com.cart.discount.SeasonalDiscount;
import com.cart.notification.EmailNotificationService;

public class Main {
    public static void main(String[] args) {
        Customer customer = new VipCustomer("C2", "Олена", "olena@mail.com", 5);
        ShoppingCart cart = new ShoppingCart(customer);

        // DIP: конкретну реалізацію (EmailNotificationService) підставляємо ззовні,
        // CartController знає лише про інтерфейс NotificationService
        CartController controller = new CartController(cart, new EmailNotificationService());

        Product laptop = new Product("P1", "Ноутбук", 25000, 5);
        controller.addProductToCart(laptop, 1);

        cart.setDiscountPolicy(new SeasonalDiscount(15));

        controller.checkout();
        System.out.println(customer.getContactInfo());
    }
}