package com.cart.bridge;

/**
 * Bridge: інтерфейс реалізації — відповідає лише за "як" доставити
 * повідомлення. Незалежний від того, яке саме повідомлення відправляється.
 */
public interface MessageSender {
    void send(String contact, String message);
}