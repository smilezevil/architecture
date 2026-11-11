package com.cart.lab14.cli;

import com.cart.lab14.domain.Customer;
import com.cart.lab14.domain.CustomerRepository;
import com.cart.lab14.slices.registercustomer.RegisterCustomerHandler;
import com.cart.lab14.slices.registercustomer.RegisterCustomerRequest;
import com.cart.lab14.slices.registercustomer.RegisterCustomerValidationException;

import java.util.Scanner;

/**
 * Composition root: тут вручну "збираються" всі залежності
 * (репозиторії -> хендлери) і запускається консольне меню.
 */
public class Main {

    public static void main(String[] args) {
        // --- Composition root: ручне підключення залежностей ---
        CustomerRepository customerRepository = new CustomerRepository();
        RegisterCustomerHandler registerCustomerHandler = new RegisterCustomerHandler(customerRepository);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Інтернет-магазин: консольне меню (Lab 14, Vertical Slices) ===");

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1. Зареєструвати покупця");
            System.out.println("0. Вихід");
            System.out.print("Обери пункт: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> registerCustomer(scanner, registerCustomerHandler);
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
                    + ", ім'я=" + customer.getName()
                    + ", email=" + customer.getEmail());
        } catch (RegisterCustomerValidationException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}