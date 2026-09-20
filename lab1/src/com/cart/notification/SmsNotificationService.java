package com.cart.notification;

public class SmsNotificationService implements NotificationService {
    @Override
    public void sendConfirmation(String customerContact, double amount) {
        System.out.println("SMS на " + customerContact + ": замовлення на суму " + amount + " грн підтверджено.");
    }
}