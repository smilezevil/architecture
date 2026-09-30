package com.cart.payment;

/**
 * Factory Method: конкретна фабрика, що створює оплату готівкою.
 */
public class CashPaymentFactory extends PaymentFactory {
    @Override
    public Payable createPayment() {
        return new CashPayment();
    }
}