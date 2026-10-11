package com.cart.bridge;

/**
 * Bridge: абстракція, що містить посилання на реалізацію (MessageSender),
 * а не успадковує її напряму. Завдяки цьому "що" (звичайне/термінове
 * повідомлення) і "як" (email/sms) можна комбінувати вільно,
 * не створюючи окремий клас на кожну пару.
 */
public class Notification {
    protected final MessageSender sender;

    public Notification(MessageSender sender) {
        this.sender = sender;
    }

    public void notify(String contact, String text) {
        sender.send(contact, text);
    }
}