package com.cart.visitor;

import com.cart.model.Product;

/**
 * Visitor: інтерфейс операції, що "відвідує" об'єкти певного типу.
 * Нову операцію над Product можна додати, просто створивши новий
 * Visitor, не змінюючи сам клас Product.
 */
public interface ProductVisitor {
    void visit(Product product);
}