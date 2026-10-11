package com.cart.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight: фабрика, що гарантує існування лише одного об'єкта
 * ProductDisplayStyle на кожну категорію. Якщо стиль для категорії
 * вже створений — повертає існуючий, а не створює новий.
 */
public class ProductDisplayStyleFactory {
    private static final Map<String, ProductDisplayStyle> styles = new HashMap<>();

    public static ProductDisplayStyle getStyle(String category) {
        if (!styles.containsKey(category)) {
            System.out.println("Створюється новий стиль для категорії: " + category);
            ProductDisplayStyle style = switch (category) {
                case "Електроніка" -> new ProductDisplayStyle(category, "синій", "💻");
                case "Аксесуари" -> new ProductDisplayStyle(category, "сірий", "🎒");
                default -> new ProductDisplayStyle(category, "білий", "📦");
            };
            styles.put(category, style);
        }
        return styles.get(category);
    }
}