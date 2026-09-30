package com.cart.factory;

import com.cart.discount.DiscountPolicy;
import com.cart.discount.PercentageDiscount;
import com.cart.notification.EmailNotificationService;
import com.cart.notification.NotificationService;

/**
 * Abstract Factory: конкретна фабрика для звичайного клієнта —
 * створює відсоткову знижку та email-сповіщення.
 */
public class StandardPromotionFactory implements PromotionFactory {
    @Override
    public DiscountPolicy createDiscountPolicy() {
        return new PercentageDiscount(5);
    }

    @Override
    public NotificationService createNotificationService() {
        return new EmailNotificationService();
    }
}