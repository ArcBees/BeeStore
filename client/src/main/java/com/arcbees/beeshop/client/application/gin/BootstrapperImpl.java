package com.arcbees.beeshop.client.application.gin;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class BootstrapperImpl implements Bootstrapper {
    private final PlaceManager placeManager;

    @Inject
    public BootstrapperImpl(PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Override
    public void onBootstrap() {
        GWT.setUncaughtExceptionHandler(new SuperDevModeUncaughtExceptionHandler());

        placeManager.revealCurrentPlace();
    }
}
