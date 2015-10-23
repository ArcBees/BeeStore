package com.arcbees.beeshop.client.application;

public interface CurrentShoppingBag {
    void addItem(ShoppingBagItem item);

    int getSize();

    void removeItem(ShoppingBagItem item);
}
