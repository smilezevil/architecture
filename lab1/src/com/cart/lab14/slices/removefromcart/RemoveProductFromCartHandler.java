package com.cart.lab14.slices.removefromcart;

import com.cart.lab14.domain.Cart;
import com.cart.lab14.domain.CartRepository;

/**
 * Обробник сценарію "Видалити товар з кошика" (вертикальний зріз).
 */
public class RemoveProductFromCartHandler {

    private final CartRepository cartRepository;

    public RemoveProductFromCartHandler(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart handle(RemoveProductFromCartRequest request) {
        if (request.quantity <= 0) {
            throw new RemoveProductFromCartValidationException("Кількість має бути більшою за нуль.");
        }

        Cart cart = cartRepository.findOrCreate(request.customerId);

        if (cart.findItem(request.productId).isEmpty()) {
            throw new RemoveProductFromCartValidationException(
                    "Цього товару немає в кошику: " + request.productId);
        }

        cart.removeItem(request.productId, request.quantity);
        cartRepository.save(cart);
        return cart;
    }
}