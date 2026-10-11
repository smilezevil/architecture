package com.cart.composite;

import com.cart.model.Product;

/**
 * Composite: "лист" дерева — обгортає один товар, щоб він також
 * реалізовував CartComponent і міг брати участь у композиції.
 */
public class CartItemComponent implements CartComponent {
    private final Product product;

    public CartItemComponent(Product product) {
        this.product = product;
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

    @Override
    public String getName() {
        return product.getName();
    }
}