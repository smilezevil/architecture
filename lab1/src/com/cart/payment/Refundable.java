package com.cart.payment;

/**
 * ISP: окремий вузький інтерфейс лише для повернення коштів.
 * Не всі способи оплати підтримують автоматичне повернення,
 * тому цей контракт винесено окремо від Payable.
 */
public interface Refundable {
    void refund(double amount);
}