package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import com.arcbees.beeshop.client.NameTokens;
import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class CurrentBrandImpl implements CurrentBrand, HasHandlers {
    private final EventBus eventBus;
    private final PlaceManager placeManager;

    @Inject
    public CurrentBrandImpl(
            EventBus eventBus,
            PlaceManager placeManager) {
        this.eventBus = eventBus;
        this.placeManager = placeManager;
    }

    @Override
    public void update() {
        BrandChangedEvent.fire(get(), this);
    }

    @Override
    public Brand get() {
        PlaceRequest currentPlaceRequest = placeManager.getCurrentPlaceRequest();
        String brandName = currentPlaceRequest.getParameter(NameTokens.PARAM_BRAND, "");

        return Brand.createFromValue(brandName);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEventFromSource(event, this);
    }
}
