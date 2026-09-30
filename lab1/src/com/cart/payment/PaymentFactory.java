package com.cart.payment;

/**
 * Factory Method: абстрактний "creator", що визначає метод створення
 * способу оплати. Конкретний тип оплати (готівка, картка) вирішують
 * підкласи цієї фабрики, а не клієнтський код напряму через new.
 */
public abstract class PaymentFactory {
    public abstract Payable createPayment();
}