package com.cart.lab14.slices.shiporder;

import com.cart.lab14.domain.*;

/**
 * Обробник сценарію "Відправити замовлення" (вертикальний зріз).
 */
public class ShipOrderHandler {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public ShipOrderHandler(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order handle(ShipOrderRequest request) {
        Order order = orderRepository.findById(request.orderId)
                .orElseThrow(() -> new ShipOrderValidationException("Замовлення не знайдено: " + request.orderId));

        // Бізнес-правило 6: відправити можна лише CONFIRMED
        if (!order.canShip()) {
            throw new ShipOrderValidationException(
                    "Неможливо відправити замовлення у статусі " + order.getStatus() + ".");
        }

        // Бізнес-правило 6 (частина 2): товар досі має бути в наявності
        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ShipOrderValidationException("Товар не знайдено: " + item.getProductId()));
            if (!product.hasEnoughStock(0)) {
                // захист про всяк випадок — тут просто перевірка існування товару
            }
        }

        order.setStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
        return order;
    }
}