package com.cart.model;

/**
 * Оформлене замовлення на основі кошика.
 */

public class Order {
    private final ShoppingCart cart;
    private final double finalPrice;
    private String status;
    private String comment;
    private String deliveryAddress;

    public Order(ShoppingCart cart) {
        this.cart = cart;
        this.finalPrice = cart.getTotalPrice();
        this.status = "CREATED";
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void confirm() {
        this.status = "CONFIRMED";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Замовлення [").append(status).append("] на суму ")
                .append(com.cart.util.CartPriceFormatter.format(finalPrice))
                .append(" для ").append(cart.getCustomer().getName());
        if (deliveryAddress != null) {
            sb.append("\nАдреса доставки: ").append(deliveryAddress);
        }
        if (comment != null) {
            sb.append("\nКоментар: ").append(comment);
        }
        return sb.toString();
    }
}