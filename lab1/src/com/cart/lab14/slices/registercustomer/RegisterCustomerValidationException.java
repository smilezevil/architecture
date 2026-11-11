package com.cart.lab14.slices.registercustomer;

/**
 * Виняток для помилок валідації при реєстрації покупця.
 * Повертає читабельне повідомлення, а не стек-трейс.
 */
public class RegisterCustomerValidationException extends RuntimeException {
    public RegisterCustomerValidationException(String message) {
        super(message);
    }
}