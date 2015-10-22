package com.arcbees.beeshop.server.guice;

import javax.inject.Singleton;

import com.arcbees.beeshop.common.api.ApiPaths;
import com.arcbees.beeshop.server.servlets.RootServlet;
import com.arcbees.guicyresteasy.GuiceRestEasyFilterDispatcher;
import com.google.inject.servlet.ServletModule;

public class DispatchServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/").with(RootServlet.class);
        bind(RootServlet.class).in(Singleton.class);

        filter(ApiPaths.ROOT + "/*").through(GuiceRestEasyFilterDispatcher.class);
    }
}
