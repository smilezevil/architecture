package com.cart.pool;

import com.cart.notification.EmailNotificationService;
import com.cart.notification.NotificationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Object Pool: зберігає обмежену кількість готових об'єктів
 * NotificationService і видає їх у користування замість створення
 * нового об'єкта щоразу. Повернуті в пул об'єкти можна перевикористати.
 */
public class NotificationServicePool {
    private final List<NotificationService> available = new ArrayList<>();
    private static final int MAX_SIZE = 3;

    public NotificationServicePool() {
        for (int i = 0; i < MAX_SIZE; i++) {
            available.add(new EmailNotificationService());
        }
    }

    public NotificationService acquire() {
        if (available.isEmpty()) {
            System.out.println("Пул порожній, створюється новий об'єкт");
            return new EmailNotificationService();
        }
        System.out.println("Об'єкт взято з пулу, залишилось: " + (available.size() - 1));
        return available.remove(available.size() - 1);
    }

    public void release(NotificationService service) {
        available.add(service);
        System.out.println("Об'єкт повернуто в пул, доступно: " + available.size());
    }
}