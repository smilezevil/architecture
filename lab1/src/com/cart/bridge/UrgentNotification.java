package com.cart.bridge;

/**
 * Bridge: розширена абстракція — термінове сповіщення.
 * Успадковує Notification, але спосіб доставки (MessageSender)
 * залишається незалежним і підставляється через конструктор батька.
 */
public class UrgentNotification extends Notification {
    public UrgentNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void notify(String contact, String text) {
        sender.send(contact, "ТЕРМІНОВО! " + text);
    }
}