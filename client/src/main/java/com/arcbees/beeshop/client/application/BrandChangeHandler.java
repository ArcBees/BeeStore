package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;

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
        currentBrand.update();
    }
}
