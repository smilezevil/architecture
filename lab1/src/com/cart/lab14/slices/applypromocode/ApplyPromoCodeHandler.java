package com.cart.lab14.slices.applypromocode;

import com.cart.lab14.domain.Cart;
import com.cart.lab14.domain.CartItem;
import com.cart.lab14.domain.CartRepository;
import com.cart.lab14.domain.Product;
import com.cart.lab14.domain.ProductRepository;

import java.util.Map;

/**
 * Обробник сценарію "Застосувати промокод до кошика" (вертикальний зріз).
 * Тестові промокоди:
 *   SALE10, SALE50     — відсоткова знижка (10% / 50%)
 *   FIX100              — фіксована знижка 100 грн
 *   FIX100000           — фіксована знижка 100000 грн (навмисно завелика,
 *                          щоб продемонструвати бізнес-правило 4)
 */
public class ApplyPromoCodeHandler {

    private static final Map<String, Double> PERCENTAGE_PROMOS = Map.of(
            "SALE10", 0.10,
            "SALE50", 0.50
    );
    private static final Map<String, Double> FIXED_PROMOS = Map.of(
            "FIX100", 100.0,
            "FIX100000", 100000.0
    );

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public ApplyPromoCodeHandler(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart handle(ApplyPromoCodeRequest request) {
        Cart cart = cartRepository.findOrCreate(request.customerId);

        if (cart.isEmpty()) {
            throw new ApplyPromoCodeValidationException("Кошик порожній, нема до чого застосовувати промокод.");
        }

        double total = computeTotal(cart);
        double discountAmount;

        if (PERCENTAGE_PROMOS.containsKey(request.promoCode)) {
            discountAmount = total * PERCENTAGE_PROMOS.get(request.promoCode);
        } else if (FIXED_PROMOS.containsKey(request.promoCode)) {
            discountAmount = FIXED_PROMOS.get(request.promoCode);
        } else {
            throw new ApplyPromoCodeValidationException("Невідомий промокод: " + request.promoCode);
        }

        // Бізнес-правило 4: сума після знижки не може бути від'ємною
        if (total - discountAmount < 0) {
            throw new ApplyPromoCodeValidationException(
                    "Промокод робить суму від'ємною (сума=" + total + ", знижка=" + discountAmount + ").");
        }

        cart.setDiscountAmount(discountAmount);
        cartRepository.save(cart);
        return cart;
    }

    private double computeTotal(Cart cart) {
        double sum = 0;
        for (CartItem item : cart.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ApplyPromoCodeValidationException(
                            "Товар не знайдено: " + item.getProductId()));
            sum += product.getPrice() * item.getQuantity();
        }
        return sum;
    }
}