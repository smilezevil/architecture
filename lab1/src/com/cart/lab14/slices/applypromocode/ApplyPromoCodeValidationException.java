package com.cart.lab14.slices.applypromocode;

/**
 * Виняток для помилок валідації при застосуванні промокоду.
 */
public class ApplyPromoCodeValidationException extends RuntimeException {
    public ApplyPromoCodeValidationException(String message) {
        super(message);
    }
}