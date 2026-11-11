package com.cart.lab14.slices.checkout;

import com.cart.lab14.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Обробник сценарію "Оформити замовлення" (вертикальний зріз).
 * Створює Order із вмісту Cart, списує товар зі складу, очищує кошик.
 */
public class CheckoutHandler {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public CheckoutHandler(CartRepository cartRepository, ProductRepository productRepository,
                           OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Order handle(CheckoutRequest request) {
        Cart cart = cartRepository.findOrCreate(request.customerId);

        // Бізнес-правило 2: не можна оформити порожній кошик
        if (cart.isEmpty()) {
            throw new CheckoutValidationException("Неможливо оформити порожній кошик.");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (CartItem cartItem : cart.getItems()) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new CheckoutValidationException(
                            "Товар не знайдено: " + cartItem.getProductId()));

            if (!product.hasEnoughStock(cartItem.getQuantity())) {
                throw new CheckoutValidationException(
                        "Товару " + product.getName() + " вже недостатньо на складі.");
            }

            orderItems.add(new OrderItem(product.getId(), cartItem.getQuantity(), product.getPrice()));
            total += product.getPrice() * cartItem.getQuantity();

            product.decreaseStock(cartItem.getQuantity());
            productRepository.save(product);
        }

        double finalSum = total - cart.getDiscountAmount();

        Order order = new Order(orderRepository.generateNextId(), request.customerId,
                orderItems, finalSum, OrderStatus.CONFIRMED);
        orderRepository.save(order);

        cart.clear();
        cartRepository.save(cart);

        return order;
    }
}