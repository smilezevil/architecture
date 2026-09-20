package com.cart.controller;

import com.cart.model.Order;
import com.cart.model.Product;
import com.cart.model.ShoppingCart;
import com.cart.notification.NotificationService;
import com.cart.util.ReceiptPrinter;

/**
 * Controller: приймає запити (наприклад від UI) і керує
 * роботою кошика й оформленням замовлення.
 * Indirection: виступає посередником між зовнішнім кодом і моделлю.
 *
 * DIP: контролер залежить від абстракції NotificationService,
 * а не від конкретного EmailNotificationService чи SmsNotificationService —
 * тому спосіб сповіщення можна підмінити, не змінюючи код контролера.
 */
public class CartController {
    private final ShoppingCart cart;
    private final NotificationService notificationService;

    public CartController(ShoppingCart cart, NotificationService notificationService) {
        this.cart = cart;
        this.notificationService = notificationService;
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
        ReceiptPrinter.print(order);
        notificationService.sendConfirmation(cart.getCustomer().getEmail(), order.getFinalPrice());
        cart.clear();
        return order;
    }
}