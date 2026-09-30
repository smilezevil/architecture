package com.cart.factory;

import com.cart.discount.DiscountPolicy;
import com.cart.notification.NotificationService;

/**
 * Abstract Factory: визначає створення сімейства пов'язаних об'єктів —
 * знижки та способу сповіщення, які логічно повинні йти разом.
 * Гарантує, що для одного типу клієнта завжди створюється узгоджена пара.
 */
public interface PromotionFactory {
    DiscountPolicy createDiscountPolicy();
    NotificationService createNotificationService();
}