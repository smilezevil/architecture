package com.cart.lab14.slices.removefromcart;

/**
 * Виняток для помилок валідації при видаленні товару з кошика.
 */
public class RemoveProductFromCartValidationException extends RuntimeException {
    public RemoveProductFromCartValidationException(String message) {
        super(message);
    }
}