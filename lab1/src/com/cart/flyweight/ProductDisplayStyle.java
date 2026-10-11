package com.cart.flyweight;

/**
 * Flyweight: об'єкт, що зберігає спільні (незмінні) дані відображення
 * для цілої категорії товарів — колір бейджа, іконку. Один такий об'єкт
 * перевикористовується для всіх товарів однієї категорії, замість
 * створення окремого об'єкта стилю на кожен товар.
 */
public class ProductDisplayStyle {
    private final String category;
    private final String badgeColor;
    private final String icon;

    public ProductDisplayStyle(String category, String badgeColor, String icon) {
        this.category = category;
        this.badgeColor = badgeColor;
        this.icon = icon;
    }

    public void display(String productName) {
        System.out.println("[" + icon + " | " + badgeColor + "] " + productName + " (категорія: " + category + ")");
    }
}