package com.cart.bridge;

public class SmsMessageSender implements MessageSender {
    @Override
    public void send(String contact, String message) {
        System.out.println("[SMS до " + contact + "]: " + message);
    }
}