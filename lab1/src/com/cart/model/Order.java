package com.cart.model;

/**
 * Оформлене замовлення на основі кошика.
 */
public class Order {
    private final ShoppingCart cart;
    private final double finalPrice;
    private String status;

    public Order(ShoppingCart cart) {
        this.cart = cart;
        this.finalPrice = cart.getTotalPrice();
        this.status = "CREATED";
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void confirm() {
        this.status = "CONFIRMED";
        System.out.println("Замовлення підтверджено. Сума до сплати: " + finalPrice + " грн");
    }

    @Override
    public String toString() {
        return "Замовлення [" + status + "] на суму " + com.cart.util.CartPriceFormatter.format(finalPrice)
                + " для " + cart.getCustomer().getName();
    }
}