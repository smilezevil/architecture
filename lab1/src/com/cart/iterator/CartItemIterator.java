package com.cart.iterator;

import com.cart.model.CartItem;

import java.util.List;

/**
 * Iterator: конкретна реалізація — перебирає список CartItem
 * по черзі, зберігаючи поточну позицію самостійно.
 */
public class CartItemIterator implements CartIterator {
    private final List<CartItem> items;
    private int position = 0;

    public CartItemIterator(List<CartItem> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.size();
    }

    @Override
    public CartItem next() {
        CartItem item = items.get(position);
        position++;
        return item;
    }
}