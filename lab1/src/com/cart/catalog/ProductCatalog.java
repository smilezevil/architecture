package com.cart.catalog;

import com.cart.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton: гарантує, що на всю програму існує рівно один каталог товарів,
 * і надає глобальну точку доступу до нього через getInstance().
 * Приватний конструктор забороняє створення екземплярів ззовні через new.
 */
public class ProductCatalog {
    private static ProductCatalog instance;
    private final Map<String, Product> products;

    private ProductCatalog() {
        products = new HashMap<>();
    }

    public static ProductCatalog getInstance() {
        if (instance == null) {
            instance = new ProductCatalog();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        System.out.println("Каталог: додано товар " + product.getName());
    }

    public Product getProduct(String id) {
        return products.get(id);
    }

    public int size() {
        return products.size();
    }
}