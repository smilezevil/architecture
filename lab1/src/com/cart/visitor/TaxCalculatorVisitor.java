package com.cart.visitor;

import com.cart.model.Product;

/**
 * Visitor: конкретна операція — розрахунок податку для товару.
 * Додана як окремий клас, без зміни Product.
 */
public class TaxCalculatorVisitor implements ProductVisitor {
    private static final double TAX_RATE = 0.20;
    private double totalTax = 0;

    @Override
    public void visit(Product product) {
        double tax = product.getPrice() * TAX_RATE;
        totalTax += tax;
        System.out.println("Visitor: податок для " + product.getName() + " = " + tax + " грн");
    }

    public double getTotalTax() {
        return totalTax;
    }
}