package com.arcbees.beeshop.client.application.gin;

import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.PreBootstrapper;

public class PreBootstrapperImpl implements PreBootstrapper {
    @Override
    public void onPreBootstrap() {
        GWT.setUncaughtExceptionHandler(new SuperDevModeUncaughtExceptionHandler());
    }
}
