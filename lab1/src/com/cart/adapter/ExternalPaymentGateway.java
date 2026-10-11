package com.cart.adapter;

/**
 * Adapter: імітує сторонню бібліотеку платіжного шлюзу з несумісним
 * інтерфейсом — інша назва методу, інший порядок аргументів.
 * Цей клас ми "не можемо" змінювати, тому що він ззовні (сторонній).
 */
public class ExternalPaymentGateway {
    public void makeTransaction(double sum, String currency) {
        System.out.println("Зовнішній шлюз: транзакція на " + sum + " " + currency);
    }
}