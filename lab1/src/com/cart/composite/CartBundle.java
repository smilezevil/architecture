package com.cart.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite: "гілка" дерева — набір (комплект) товарів чи навіть
 * інших наборів. Рахує свою ціну як суму цін усіх вкладених елементів,
 * і при цьому сам реалізує той самий CartComponent — тому набір
 * можна вкласти в інший набір, і це буде працювати рекурсивно.
 */
public class CartBundle implements CartComponent {
    private final String bundleName;
    private final List<CartComponent> components = new ArrayList<>();

    public CartBundle(String bundleName) {
        this.bundleName = bundleName;
    }

    public void add(CartComponent component) {
        components.add(component);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (CartComponent component : components) {
            total += component.getPrice();
        }
        return total;
    }

    @Override
    public String getName() {
        return bundleName;
    }
}