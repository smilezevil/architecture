package com.cart.proxy;

import com.cart.catalog.ProductCatalog;
import com.cart.model.Product;

/**
 * Proxy: заступник, що контролює доступ до реального ProductCatalog
 * (Singleton). Додає логування кожного звернення, не змінюючи сам
 * ProductCatalog і залишаючись прозорим для клієнтського коду —
 * клієнт працює з проксі так само, як працював би з оригіналом.
 */
public class ProductCatalogProxy {
    private final ProductCatalog realCatalog;

    public ProductCatalogProxy() {
        this.realCatalog = ProductCatalog.getInstance();
    }

    public void addProduct(Product product) {
        System.out.println("Proxy: перевірка доступу перед додаванням товару...");
        realCatalog.addProduct(product);
    }

    public Product getProduct(String id) {
        System.out.println("Proxy: лог звернення до товару з id=" + id);
        return realCatalog.getProduct(id);
    }
}