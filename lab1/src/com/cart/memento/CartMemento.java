package com.cart.memento;

import com.cart.model.CartItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Memento: зберігає знімок стану кошика (список товарів) на певний момент
 * часу. Сам об'єкт незмінний (immutable) — повертає копію списку,
 * тому зовнішній код не може випадково змінити збережений знімок.
 */
public class CartMemento {
    private final List<CartItem> savedItems;

    public CartMemento(List<CartItem> items) {
        this.savedItems = new ArrayList<>(items);
    }

    public List<CartItem> getSavedItems() {
        return new ArrayList<>(savedItems);
    }
}