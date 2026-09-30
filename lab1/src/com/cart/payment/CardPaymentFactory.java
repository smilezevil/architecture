package com.cart.payment;

/**
 * Factory Method: конкретна фабрика, що створює оплату карткою.
 */
public class CardPaymentFactory extends PaymentFactory {
    @Override
    public Payable createPayment() {
        return new CardPayment();
    }
}