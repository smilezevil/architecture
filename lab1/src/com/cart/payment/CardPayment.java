package com.cart.payment;

public class CardPayment implements Payable, Refundable {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата карткою на суму " + amount + " грн");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Повернення на картку суми " + amount + " грн");
    }
}