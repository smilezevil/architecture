package com.cart.payment;

/**
 * ISP: CashPayment реалізує лише Payable, бо готівкою
 * автоматичне повернення коштів не передбачене — і не змушена
 * "заглушувати" непотрібний метод refund(), як було б з одним товстим інтерфейсом.
 */
public class CashPayment implements Payable {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата готівкою на суму " + amount + " грн");
    }
}