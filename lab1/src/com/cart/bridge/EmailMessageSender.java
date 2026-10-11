package com.cart.bridge;

public class EmailMessageSender implements MessageSender {
    @Override
    public void send(String contact, String message) {
        System.out.println("[Email до " + contact + "]: " + message);
    }
}