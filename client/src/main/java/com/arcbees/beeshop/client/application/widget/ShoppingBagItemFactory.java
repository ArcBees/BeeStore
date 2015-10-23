package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.client.application.ShoppingBagItem;

public interface ShoppingBagItemFactory {
    ShoppingBagItemPresenter create(ShoppingBagItem shoppingBagItem);
}
