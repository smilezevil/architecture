package com.cart.mediator;

import com.cart.model.ShoppingCart;
import com.cart.notification.NotificationService;
import com.cart.payment.Payable;

/**
 * Mediator: координує взаємодію кількох незалежних об'єктів
 * (кошик, оплата, сповіщення) під час оформлення замовлення.
 * Ці об'єкти не викликають один одного напряму — вся координація
 * зосереджена в одному місці, що спрощує додавання нових кроків
 * процесу в майбутньому.
 */
public class CheckoutMediator {
    private final ShoppingCart cart;
    private final Payable payment;
    private final NotificationService notificationService;

    public CheckoutMediator(ShoppingCart cart, Payable payment, NotificationService notificationService) {
        this.cart = cart;
        this.payment = payment;
        this.notificationService = notificationService;
    }

    public void completeCheckout() {
        double total = cart.getTotalPrice();
        System.out.println("Mediator: запускає процес оформлення замовлення");
        payment.pay(total);
        notificationService.sendConfirmation(cart.getCustomer().getEmail(), total);
        cart.clear();
        System.out.println("Mediator: оформлення завершено, кошик очищено");
    }
}