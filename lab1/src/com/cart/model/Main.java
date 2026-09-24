package com.cart.model;

import com.cart.catalog.ProductCatalog;
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

        // Singleton: перевірка, що ProductCatalog існує в єдиному екземплярі
        ProductCatalog catalog1 = ProductCatalog.getInstance();
        ProductCatalog catalog2 = ProductCatalog.getInstance();

        System.out.println("Це той самий об'єкт? " + (catalog1 == catalog2));

        catalog1.addProduct(laptop);
        System.out.println("Товарів у каталозі (через catalog2): " + catalog2.size());

        // Prototype: клонуємо вже існуючий товар, а не створюємо новий з нуля
        Product clonedLaptop = laptop.clone();
        System.out.println("Клонований товар: " + clonedLaptop);
        System.out.println("Це той самий об'єкт? " + (laptop == clonedLaptop));
    }
}