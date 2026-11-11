package com.cart.lab14.slices.cancelorder;

public class CancelOrderRequest {
    public final String orderId;

    public CancelOrderRequest(String orderId) {
        this.orderId = orderId;
    }
}