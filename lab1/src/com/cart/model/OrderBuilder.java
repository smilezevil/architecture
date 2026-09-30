package com.cart.model;

/**
 * Builder: відокремлює покрокове конструювання складного об'єкта Order
 * від його кінцевого представлення. Дозволяє гнучко додавати необов'язкові
 * деталі замовлення (коментар, адресу), не роздуваючи конструктор Order.
 */
public class OrderBuilder {
    private final ShoppingCart cart;
    private String comment;
    private String deliveryAddress;

    public OrderBuilder(ShoppingCart cart) {
        this.cart = cart;
    }

    public OrderBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public OrderBuilder withDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public Order build() {
        Order order = new Order(cart);
        if (comment != null) {
            order.setComment(comment);
        }
        if (deliveryAddress != null) {
            order.setDeliveryAddress(deliveryAddress);
        }
        return order;
    }
}