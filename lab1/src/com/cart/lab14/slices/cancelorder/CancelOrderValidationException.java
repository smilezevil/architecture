package com.cart.lab14.slices.cancelorder;

public class CancelOrderValidationException extends RuntimeException {
    public CancelOrderValidationException(String message) {
        super(message);
    }
}