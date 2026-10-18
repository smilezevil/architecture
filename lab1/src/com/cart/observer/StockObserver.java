package com.cart.observer;

/**
 * Observer: конкретний спостерігач, що реагує на зміну кошика —
 * наприклад, логує зміни для аналітики складу.
 */
public class StockObserver implements CartObserver {
    @Override
    public void onCartChanged(int itemCount, double totalPrice) {
        System.out.println("StockObserver: кошик змінився — позицій: " + itemCount + ", сума: " + totalPrice + " грн");
    }
}