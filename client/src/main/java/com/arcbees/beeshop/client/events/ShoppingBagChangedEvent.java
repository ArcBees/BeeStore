package com.arcbees.beeshop.client.events;

import com.arcbees.beeshop.client.application.ShoppingBagItem;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ShoppingBagChangedEvent extends GwtEvent<ShoppingBagChangedEventHandler> {
    public static final Type<ShoppingBagChangedEventHandler> TYPE = new Type<>();
    private final ShoppingBagItem item;
    private boolean removed;

    public ShoppingBagChangedEvent(ShoppingBagItem item, Boolean removed) {
        this.item = item;
        this.removed = removed;
    }

    public static void fire(ShoppingBagItem item, HasHandlers source) {
        source.fireEvent(new ShoppingBagChangedEvent(item, false));
    }

    public static void fire(ShoppingBagItem item, Boolean removed, HasHandlers source) {
        source.fireEvent(new ShoppingBagChangedEvent(item, removed));
    }

    public ShoppingBagItem getItem() {
        return item;
    }

    public boolean isRemoved() {
        return removed;
    }

    @Override
    public Type<ShoppingBagChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShoppingBagChangedEventHandler handler) {
        handler.onShoppingBagChanged(this);
    }
}
