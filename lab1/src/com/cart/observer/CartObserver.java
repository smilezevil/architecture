package com.cart.observer;

/**
 * Observer: спільний інтерфейс для всіх спостерігачів за кошиком.
 * ShoppingCart сповіщає всіх підписаних спостерігачів про зміни,
 * не знаючи наперед, скільки їх і що саме вони з цим роблять.
 */
public interface CartObserver {
    void onCartChanged(int itemCount, double totalPrice);
}