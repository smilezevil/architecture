package com.cart.state;

import com.cart.model.Order;

/**
 * State: спільний інтерфейс для всіх станів замовлення.
 * Кожен конкретний стан визначає, в який наступний стан можна перейти
 * саме з нього — поведінка переходу залежить від поточного стану.
 */
public interface OrderState {
    void next(Order order);
    String getName();
}