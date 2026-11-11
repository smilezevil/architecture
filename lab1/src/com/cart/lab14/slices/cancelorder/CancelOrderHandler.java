package com.cart.lab14.slices.cancelorder;

import com.cart.lab14.domain.Order;
import com.cart.lab14.domain.OrderRepository;
import com.cart.lab14.domain.OrderStatus;

/**
 * Обробник сценарію "Скасувати замовлення" (вертикальний зріз).
 */
public class CancelOrderHandler {

    private final OrderRepository orderRepository;

    public CancelOrderHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order handle(CancelOrderRequest request) {
        Order order = orderRepository.findById(request.orderId)
                .orElseThrow(() -> new CancelOrderValidationException("Замовлення не знайдено: " + request.orderId));

        // Бізнес-правило 5: скасувати можна лише CREATED або CONFIRMED
        if (!order.canCancel()) {
            throw new CancelOrderValidationException(
                    "Неможливо скасувати замовлення у статусі " + order.getStatus() + ".");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return order;
    }
}