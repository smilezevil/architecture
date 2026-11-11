package com.cart.lab14.slices.addtocart;

/**
 * Виняток для помилок валідації при додаванні товару в кошик.
 */
public class AddProductToCartValidationException extends RuntimeException {
    public AddProductToCartValidationException(String message) {
        super(message);
    }
}