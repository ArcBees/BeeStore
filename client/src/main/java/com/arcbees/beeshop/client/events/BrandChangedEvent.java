package com.arcbees.beeshop.client.events;

import com.arcbees.beeshop.common.dto.Brand;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class BrandChangedEvent extends GwtEvent<BrandChangedEventHandler> {
    public static Type<BrandChangedEventHandler> TYPE = new Type<>();

    private final Brand brand;

    public BrandChangedEvent(Brand brand) {
        this.brand = brand;
    }

    public static void fire(Brand brand, HasHandlers source) {
        source.fireEvent(new BrandChangedEvent(brand));
    }

    public Brand getBrand() {
        return brand;
    }

    public Type<BrandChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(BrandChangedEventHandler handler) {
        handler.onBrandChanged(this);
    }
}
