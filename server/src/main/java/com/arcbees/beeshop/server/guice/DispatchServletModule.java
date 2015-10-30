package com.arcbees.beeshop.server.guice;

import com.arcbees.beeshop.common.api.ApiPaths;
import com.arcbees.beeshop.server.servlets.LocaleExtractor;
import com.arcbees.beeshop.server.servlets.NotFoundServlet;
import com.arcbees.beeshop.server.servlets.RootServlet;
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
