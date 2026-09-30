package com.cart.factory;

import com.cart.discount.DiscountPolicy;
import com.cart.discount.SeasonalDiscount;
import com.cart.notification.NotificationService;
import com.cart.notification.SmsNotificationService;

/**
 * Abstract Factory: конкретна фабрика для VIP-клієнта —
 * створює сезонну знижку та sms-сповіщення.
 */
public class VipPromotionFactory implements PromotionFactory {
    @Override
    public DiscountPolicy createDiscountPolicy() {
        return new SeasonalDiscount(20);
    }

    @Override
    public NotificationService createNotificationService() {
        return new SmsNotificationService();
    }
}