package com.cart.notification;

/**
 * DIP: абстракція, від якої залежить високорівневий CartController.
 * Конкретний спосіб сповіщення (email, sms) — деталь низького рівня,
 * що підставляється ззовні, а не "зашита" в контролер.
 */
public interface NotificationService {
    void sendConfirmation(String customerContact, double amount);
}