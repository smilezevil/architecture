package com.cart.lab14.slices.addtocart;

import com.cart.lab14.domain.Cart;
import com.cart.lab14.domain.CartRepository;
import com.cart.lab14.domain.Product;
import com.cart.lab14.domain.ProductRepository;

/**
 * Обробник сценарію "Додати товар у кошик" (вертикальний зріз).
 */
public class AddProductToCartHandler {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddProductToCartHandler(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart handle(AddProductToCartRequest request) {
        if (request.quantity <= 0) {
            throw new AddProductToCartValidationException("Кількість товару має бути більшою за нуль.");
        }

        Product product = productRepository.findById(request.productId)
                .orElseThrow(() -> new AddProductToCartValidationException(
                        "Товар не знайдено: " + request.productId));

        Cart cart = cartRepository.findOrCreate(request.customerId);
        int alreadyInCart = cart.findItem(request.productId).map(i -> i.getQuantity()).orElse(0);
        int totalRequested = alreadyInCart + request.quantity;

        // Бізнес-правило 1: не можна додати більше, ніж є на складі
        if (!product.hasEnoughStock(totalRequested)) {
            throw new AddProductToCartValidationException(
                    "Недостатньо товару на складі. В наявності: " + product.getStockQuantity()
                            + ", у кошику вже: " + alreadyInCart + ", запитано ще: " + request.quantity);
        }

        cart.addItem(request.productId, request.quantity);
        cartRepository.save(cart);
        return cart;
    }
}