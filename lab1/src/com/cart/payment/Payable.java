package com.cart.payment;

/**
 * ISP: вузький інтерфейс лише для оплати.
 * Усі способи оплати вміють платити — тому це спільний контракт.
 */
public interface Payable {
    void pay(double amount);
}