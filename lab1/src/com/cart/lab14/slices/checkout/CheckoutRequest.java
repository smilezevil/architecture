package com.cart.lab14.slices.checkout;

/**
 * Запит на оформлення замовлення з кошика покупця.
 */
public class CheckoutRequest {
    public final String customerId;

    public CheckoutRequest(String customerId) {
        this.customerId = customerId;
    }
}