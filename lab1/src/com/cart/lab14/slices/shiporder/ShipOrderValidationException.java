package com.cart.lab14.slices.shiporder;

public class ShipOrderValidationException extends RuntimeException {
    public ShipOrderValidationException(String message) {
        super(message);
    }
}