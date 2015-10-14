package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import com.arcbees.beeshop.client.NameTokens;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class BrandChangeHandler implements NavigationHandler {
    private final CurrentBrand currentBrand;

    @Inject
    public BrandChangeHandler(
            EventBus eventBus,
            CurrentBrand currentBrand) {
        this.currentBrand = currentBrand;
        eventBus.addHandler(NavigationEvent.getType(), this);
    }

    @Override
    public void onNavigation(NavigationEvent navigationEvent) {
        PlaceRequest request = navigationEvent.getRequest();

        String brandValue = request.getParameter(NameTokens.PARAM_BRAND, "");

        currentBrand.update(Brand.createFromValue(brandValue));
    }
}
