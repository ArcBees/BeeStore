package com.arcbees.beestore.server.guice;

import com.arcbees.beestore.common.api.ApiPaths;
import com.arcbees.beestore.server.servlets.LocaleExtractor;
import com.arcbees.beestore.server.servlets.NotFoundServlet;
import com.arcbees.beestore.server.servlets.RootServlet;
import com.arcbees.guicyresteasy.GuiceRestEasyFilterDispatcher;
import com.google.inject.servlet.ServletModule;

public class DispatchServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        filter(ApiPaths.ROOT + "/*").through(GuiceRestEasyFilterDispatcher.class);

        serve("/").with(RootServlet.class);

        for (String locale : LocaleExtractor.SUPPORTED_LOCALES) {
            serveRegex("/" + locale, "/" + locale + "/").with(RootServlet.class);
        }

        serve("/*").with(NotFoundServlet.class);
    }
}
