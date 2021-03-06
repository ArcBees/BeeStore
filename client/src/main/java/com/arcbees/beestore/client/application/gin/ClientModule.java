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

package com.arcbees.beestore.client.application.gin;

import com.arcbees.beestore.common.NameTokens;
import com.arcbees.beestore.client.application.ApplicationModule;
import com.arcbees.beestore.client.resources.ResourceLoader;
import com.arcbees.beestore.common.api.ApiPaths;
import com.arcbees.seo.TwitterCard;
import com.arcbees.stripe.client.StripeModule;
import com.google.gwt.inject.client.AbstractGinModule;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

public class ClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        install(new DefaultModule.Builder()
                .tokenFormatter(RouteTokenFormatter.class)
                .defaultPlace(NameTokens.HOME)
                .unauthorizedPlace(NameTokens.HOME)
                .errorPlace(NameTokens.NOT_FOUND)
                .build());

        install(new ApplicationModule());
        install(new StripeModule());

        bind(ResourceLoader.class).asEagerSingleton();
        bind(Bootstrapper.class).to(BootstrapperImpl.class).asEagerSingleton();
        bind(TwitterCard.class).toProvider(TwitterCardProvider.class);

        install(new RestDispatchAsyncModule.Builder().build());
        bindConstant().annotatedWith(RestApplicationPath.class).to(ApiPaths.ROOT);
    }
}
