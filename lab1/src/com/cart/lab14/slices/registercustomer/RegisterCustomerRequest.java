package com.cart.lab14.slices.registercustomer;

/**
 * Запит на реєстрацію нового покупця (вхідні дані зрізу).
 */
public class RegisterCustomerRequest {
    public final String name;
    public final String email;

    public RegisterCustomerRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }
}