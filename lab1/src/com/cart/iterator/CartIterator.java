package com.cart.iterator;

import com.cart.model.CartItem;

/**
 * Iterator: спільний інтерфейс для перебору елементів кошика.
 * Клієнтський код working з цим інтерфейсом не знає і не повинен знати,
 * як саме товари зберігаються всередині ShoppingCart (List, масив тощо).
 */
public interface CartIterator {
    boolean hasNext();
    CartItem next();
}