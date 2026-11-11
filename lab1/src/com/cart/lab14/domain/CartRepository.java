package com.cart.lab14.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Репозиторій кошиків. Для простоти зберігає кошики в пам'яті
 * (один кошик на покупця), без запису у файл — кошик це тимчасові
 * дані сесії покупки, на відміну від замовлень і покупців.
 */
public class CartRepository {

    private final Map<String, Cart> cartsByCustomerId = new HashMap<>();

    public Cart findOrCreate(String customerId) {
        return cartsByCustomerId.computeIfAbsent(customerId, Cart::new);
    }

    public void save(Cart cart) {
        cartsByCustomerId.put(cart.getCustomerId(), cart);
    }
}