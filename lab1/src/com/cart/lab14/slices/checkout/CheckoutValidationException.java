package com.cart.lab14.slices.checkout;

/**
 * Виняток для помилок валідації при оформленні замовлення.
 */
public class CheckoutValidationException extends RuntimeException {
    public CheckoutValidationException(String message) {
        super(message);
    }
}