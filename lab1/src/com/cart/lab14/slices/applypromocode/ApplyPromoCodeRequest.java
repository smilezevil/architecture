package com.cart.lab14.slices.applypromocode;

/**
 * Запит на застосування промокоду до кошика.
 */
public class ApplyPromoCodeRequest {
    public final String customerId;
    public final String promoCode;

    public ApplyPromoCodeRequest(String customerId, String promoCode) {
        this.customerId = customerId;
        this.promoCode = promoCode;
    }
}