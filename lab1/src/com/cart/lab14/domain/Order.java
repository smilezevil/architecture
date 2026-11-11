package com.cart.lab14.domain;

import java.util.List;

/**
 * Замовлення, створене з кошика покупця.
 */
public class Order {
    private final String id;
    private final String customerId;
    private final List<OrderItem> items;
    private final double totalSum;
    private OrderStatus status;

    public Order(String id, String customerId, List<OrderItem> items, double totalSum, OrderStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.totalSum = totalSum;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Бізнес-правило 5: скасувати можна лише CREATED або CONFIRMED.
     */
    public boolean canCancel() {
        return status == OrderStatus.CREATED || status == OrderStatus.CONFIRMED;
    }

    /**
     * Бізнес-правило 6 (частина 1): відправити можна лише CONFIRMED.
     */
    public boolean canShip() {
        return status == OrderStatus.CONFIRMED;
    }
}