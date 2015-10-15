package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.EventBus;

public class CurrentBrandImpl implements CurrentBrand, HasHandlers {
    private final EventBus eventBus;

    private Brand brand;

    @Inject
    public CurrentBrandImpl(
            EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void update(Brand brand) {
        if (this.brand == brand) {
            return;
        }

        this.brand = brand;
        BrandChangedEvent.fire(this.brand, this);
    }

    @Override
    public Brand get() {
        return brand;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEventFromSource(event, this);
    }
}
