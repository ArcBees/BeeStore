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

package com.arcbees.beeshop.client.application;

import javax.inject.Singleton;

import com.arcbees.beeshop.client.application.home.HomeModule;
import com.arcbees.beeshop.client.application.payment.PaymentModule;
import com.arcbees.beeshop.client.application.product.ProductModule;
import com.arcbees.beeshop.client.application.widget.WidgetModule;
import com.arcbees.beeshop.client.resources.ProductBrandUtil;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new PaymentModule());
        install(new HomeModule());
        install(new ProductModule());
        install(new WidgetModule());

        bind(BrandChangeHandler.class).asEagerSingleton();
        bind(CurrentBrand.class).to(CurrentBrandImpl.class).in(Singleton.class);
        bind(ProductBrandUtil.class).in(Singleton.class);
        bind(CurrentOrder.class).to(CurrentOrderImpl.class).in(Singleton.class);
        bind(LocaleHelper.class).to(LocaleHelperImpl.class).in(Singleton.class);

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}
