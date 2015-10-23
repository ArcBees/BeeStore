package com.arcbees.beeshop.client.application;

import java.util.ArrayList;
import java.util.List;

import com.arcbees.beeshop.client.events.ShoppingBagChangedEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class CurrentShoppingBagImpl implements CurrentShoppingBag, HasHandlers {
    private List<ShoppingBagItem> items = new ArrayList<>();
    private final EventBus eventBus;

    @Inject
    CurrentShoppingBagImpl(
            EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void addItem(ShoppingBagItem item) {
        items.add(item);

        ShoppingBagChangedEvent.fire(item, this);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public void removeItem(ShoppingBagItem item) {
        items.remove(item);

        ShoppingBagChangedEvent.fire(item, true, this);
    }

    @Override
    public void fireEvent(GwtEvent<?> gwtEvent) {
        eventBus.fireEventFromSource(gwtEvent, this);
    }
}
