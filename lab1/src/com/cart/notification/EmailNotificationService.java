package com.cart.notification;

public class EmailNotificationService implements NotificationService {
    @Override
    public void sendConfirmation(String customerContact, double amount) {
        System.out.println("Email на " + customerContact + ": замовлення на суму " + amount + " грн підтверджено.");
    }
}