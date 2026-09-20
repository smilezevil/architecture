package com.cart.model;

/**
 * LSP: VipCustomer успадковує Customer і може використовуватись
 * скрізь, де очікується Customer, без порушення роботи програми —
 * лише додає нову поведінку (бонусну знижку), не змінюючи стару.
 */
public class VipCustomer extends Customer {
    private final double loyaltyDiscountPercentage;

    public VipCustomer(String id, String name, String email, double loyaltyDiscountPercentage) {
        super(id, name, email);
        this.loyaltyDiscountPercentage = loyaltyDiscountPercentage;
    }

    public double getLoyaltyDiscountPercentage() {
        return loyaltyDiscountPercentage;
    }

    @Override
    public String getContactInfo() {
        return super.getContactInfo() + " [VIP]";
    }
}