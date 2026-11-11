package com.cart.lab14.slices.registercustomer;

import com.cart.lab14.domain.Customer;
import com.cart.lab14.domain.CustomerRepository;


/**
 * Обробник сценарію "Реєстрація покупця" (вертикальний зріз).
 * Містить всю логіку use case: валідацію, бізнес-правило, збереження.
 */
public class RegisterCustomerHandler {

    private final CustomerRepository customerRepository;

    public RegisterCustomerHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer handle(RegisterCustomerRequest request) {
        if (request.name == null || request.name.isBlank()) {
            throw new RegisterCustomerValidationException("Ім'я покупця не може бути порожнім.");
        }
        if (request.email == null || request.email.isBlank()) {
            throw new RegisterCustomerValidationException("Email не може бути порожнім.");
        }

        // Бізнес-правило 3: email має бути унікальним
        if (customerRepository.findByEmail(request.email).isPresent()) {
            throw new RegisterCustomerValidationException(
                    "Покупець з таким email вже зареєстрований: " + request.email);
        }

        Customer customer = new Customer(customerRepository.generateNextId(), request.name, request.email);
        customerRepository.save(customer);
        return customer;
    }
}