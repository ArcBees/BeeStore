package com.arcbees.beeshop.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface ShoppingBagChangedEventHandler extends EventHandler {
    void onShoppingBagChanged(ShoppingBagChangedEvent event);
}
