package com.cart.util;

import com.cart.model.Order;

/**
 * SRP: єдина відповідальність цього класу — друк чека замовлення.
 * Order сам про це нічого не знає, тому зміна формату чека
 * не вимагає зміни класу Order.
 */
public class ReceiptPrinter {
    public static void print(Order order) {
        System.out.println("========= ЧЕК =========");
        System.out.println(order);
        System.out.println("========================");
    }
}