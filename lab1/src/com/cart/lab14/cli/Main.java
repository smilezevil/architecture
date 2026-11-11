package com.cart.lab14.cli;

import com.cart.lab14.domain.*;
import com.cart.lab14.slices.addtocart.*;
import com.cart.lab14.slices.applypromocode.*;
import com.cart.lab14.slices.checkout.*;
import com.cart.lab14.slices.registercustomer.*;
import com.cart.lab14.slices.removefromcart.*;

import java.util.Scanner;

/**
 * Composition root: тут вручну "збираються" всі залежності
 * (репозиторії -> хендлери) і запускається консольне меню.
 */
public class Main {

    public static void main(String[] args) {
        // --- Composition root: ручне підключення залежностей ---
        CustomerRepository customerRepository = new CustomerRepository();
        ProductRepository productRepository = new ProductRepository();
        CartRepository cartRepository = new CartRepository();
        OrderRepository orderRepository = new OrderRepository();

        RegisterCustomerHandler registerCustomerHandler = new RegisterCustomerHandler(customerRepository);
        AddProductToCartHandler addProductToCartHandler = new AddProductToCartHandler(cartRepository, productRepository);
        RemoveProductFromCartHandler removeProductFromCartHandler = new RemoveProductFromCartHandler(cartRepository);
        ApplyPromoCodeHandler applyPromoCodeHandler = new ApplyPromoCodeHandler(cartRepository, productRepository);
        CheckoutHandler checkoutHandler = new CheckoutHandler(cartRepository, productRepository, orderRepository);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Інтернет-магазин: консольне меню (Lab 14, Vertical Slices) ===");

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1. Зареєструвати покупця");
            System.out.println("2. Показати товари");
            System.out.println("3. Додати товар у кошик");
            System.out.println("4. Видалити товар з кошика");
            System.out.println("5. Показати кошик");
            System.out.println("6. Застосувати промокод");
            System.out.println("7. Оформити замовлення");
            System.out.println("0. Вихід");
            System.out.print("Обери пункт: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> registerCustomer(scanner, registerCustomerHandler);
                case "2" -> showProducts(productRepository);
                case "3" -> addToCart(scanner, addProductToCartHandler);
                case "4" -> removeFromCart(scanner, removeProductFromCartHandler);
                case "5" -> showCart(scanner, cartRepository, productRepository);
                case "6" -> applyPromoCode(scanner, applyPromoCodeHandler);
                case "7" -> checkout(scanner, checkoutHandler);
                case "0" -> running = false;
                default -> System.out.println("Невідомий пункт меню.");
            }
        }

        System.out.println("Роботу завершено.");
    }

    private static void registerCustomer(Scanner scanner, RegisterCustomerHandler handler) {
        System.out.print("Ім'я покупця: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        try {
            Customer customer = handler.handle(new RegisterCustomerRequest(name, email));
            System.out.println("Покупця зареєстровано: id=" + customer.getId()
                    + ", ім'я=" + customer.getName() + ", email=" + customer.getEmail());
        } catch (RegisterCustomerValidationException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void showProducts(ProductRepository productRepository) {
        System.out.println("Товари:");
        for (Product p : productRepository.findAll()) {
            System.out.println("  id=" + p.getId() + ", " + p.getName()
                    + ", ціна=" + p.getPrice() + ", на складі=" + p.getStockQuantity());
        }
    }

    private static void addToCart(Scanner scanner, AddProductToCartHandler handler) {
        System.out.print("Id покупця: ");
        String customerId = scanner.nextLine();
        System.out.print("Id товару: ");
        String productId = scanner.nextLine();
        System.out.print("Кількість: ");
        int qty = Integer.parseInt(scanner.nextLine());
        try {
            Cart cart = handler.handle(new AddProductToCartRequest(customerId, productId, qty));
            System.out.println("Додано. Позицій у кошику: " + cart.getItems().size());
        } catch (AddProductToCartValidationException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void removeFromCart(Scanner scanner, RemoveProductFromCartHandler handler) {
        System.out.print("Id покупця: ");
        String customerId = scanner.nextLine();
        System.out.print("Id товару: ");
        String productId = scanner.nextLine();
        System.out.print("Кількість: ");
        int qty = Integer.parseInt(scanner.nextLine());
        try {
            Cart cart = handler.handle(new RemoveProductFromCartRequest(customerId, productId, qty));
            System.out.println("Видалено. Позицій у кошику: " + cart.getItems().size());
        } catch (RemoveProductFromCartValidationException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void showCart(Scanner scanner, CartRepository cartRepository, ProductRepository productRepository) {
        System.out.print("Id покупця: ");
        String customerId = scanner.nextLine();
        Cart cart = cartRepository.findOrCreate(customerId);
        if (cart.isEmpty()) {
            System.out.println("Кошик порожній.");
            return;
        }
        double total = 0;
        for (CartItem item : cart.getItems()) {
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            double price = product != null ? product.getPrice() : 0;
            total += price * item.getQuantity();
            System.out.println("  товар=" + item.getProductId() + ", кількість=" + item.getQuantity()
                    + ", ціна=" + price);
        }
        System.out.println("Сума: " + total);
        System.out.println("Знижка: " + cart.getDiscountAmount());
        System.out.println("До сплати: " + (total - cart.getDiscountAmount()));
    }

    private static void applyPromoCode(Scanner scanner, ApplyPromoCodeHandler handler) {
        System.out.print("Id покупця: ");
        String customerId = scanner.nextLine();
        System.out.print("Промокод (SALE10, SALE50, FIX100, FIX100000): ");
        String promoCode = scanner.nextLine();
        try {
            Cart cart = handler.handle(new ApplyPromoCodeRequest(customerId, promoCode));
            System.out.println("Промокод застосовано. Знижка: " + cart.getDiscountAmount());
        } catch (ApplyPromoCodeValidationException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void checkout(Scanner scanner, CheckoutHandler handler) {
        System.out.print("Id покупця: ");
        String customerId = scanner.nextLine();
        try {
            Order order = handler.handle(new CheckoutRequest(customerId));
            System.out.println("Замовлення оформлено: id=" + order.getId()
                    + ", сума=" + order.getTotalSum() + ", статус=" + order.getStatus());
        } catch (CheckoutValidationException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}