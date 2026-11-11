package com.cart.lab14.slices.addtocart;

/**
 * Запит на додавання товару в кошик.
 */
public class AddProductToCartRequest {
    public final String customerId;
    public final String productId;
    public final int quantity;

    public AddProductToCartRequest(String customerId, String productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }
}