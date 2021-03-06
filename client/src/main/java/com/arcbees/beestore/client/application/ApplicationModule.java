/*
 * Copyright 2016 ArcBees Inc.
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

package com.arcbees.beestore.client.application;

import javax.inject.Singleton;

import com.arcbees.beestore.client.application.home.HomeModule;
import com.arcbees.beestore.client.application.notfound.NotFoundModule;
import com.arcbees.beestore.client.application.product.ProductModule;
import com.arcbees.beestore.client.application.widget.WidgetModule;
import com.arcbees.beestore.client.resources.ProductBrandUtil;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());
        install(new ProductModule());
        install(new WidgetModule());
        install(new NotFoundModule());

        bind(ShoppingCartLocalStorage.class).to(ShoppingCartLocalStorageImpl.class).asEagerSingleton();
        bind(BrandChangeHandler.class).asEagerSingleton();
        bind(CurrentBrand.class).to(CurrentBrandImpl.class).in(Singleton.class);
        bind(ProductBrandUtil.class).in(Singleton.class);
        bind(CurrentOrder.class).to(CurrentOrderImpl.class).in(Singleton.class);
        bind(LocaleHelper.class).to(LocaleHelperImpl.class).in(Singleton.class);

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}
