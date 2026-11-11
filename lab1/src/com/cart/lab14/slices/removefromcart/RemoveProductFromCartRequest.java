package com.cart.lab14.slices.removefromcart;

/**
 * Запит на видалення товару з кошика.
 */
public class RemoveProductFromCartRequest {
    public final String customerId;
    public final String productId;
    public final int quantity;

    public RemoveProductFromCartRequest(String customerId, String productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }
}