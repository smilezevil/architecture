package com.cart.composite;

/**
 * Composite: спільний інтерфейс для одиночного товару і для набору товарів.
 * Завдяки цьому клієнтський код рахує ціну однаково, не перевіряючи,
 * чи це один товар, чи ціле дерево товарів.
 */
public interface CartComponent {
    double getPrice();
    String getName();
}