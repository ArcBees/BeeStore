/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

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
