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
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.WebClient;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;
import com.gwtplatform.crawler.server.ServiceKey;
import com.gwtplatform.crawler.server.ServiceUrl;
import com.gwtplatform.crawlerservice.server.CachedPageTimeoutSec;
import com.gwtplatform.crawlerservice.server.HtmlUnitTimeoutMillis;

import static com.arcbees.beestore.common.Constants.ROOT_URL;

public class DispatchServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        filter(ApiPaths.ROOT + "/*").through(GuiceRestEasyFilterDispatcher.class);

        bindConstant().annotatedWith(ServiceKey.class).to("ab12cd34");
        bindConstant().annotatedWith(com.gwtplatform.crawlerservice.server.ServiceKey.class).to("ab12cd34");
        bindConstant().annotatedWith(ServiceUrl.class).to(ROOT_URL);
        bindConstant().annotatedWith(HtmlUnitTimeoutMillis.class).to(6000L);
        bindConstant().annotatedWith(CachedPageTimeoutSec.class).to(86400L);

        requestStaticInjection(CrawlerRequest.class);
        filter("/*").through(CrawlerFilter.class);

        serve("/").with(RootServlet.class);

        for (String locale : LocaleExtractor.SUPPORTED_LOCALES) {
            serveRegex("/" + locale, "/" + locale + "/").with(RootServlet.class);
        }

        serve("/*").with(NotFoundServlet.class);
    }

    @Provides
    WebClient getWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);

        webClient.setIncorrectnessListener(new IncorrectnessListener() {
            @Override
            public void notify(String message, Object origin) {
            }
        });

        return webClient;
    }
}
