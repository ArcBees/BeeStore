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

package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.client.application.widget.slider.Slider;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class WidgetModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(PricePresenter.MyView.class).to(PriceView.class);
        bind(ProductPresenter.MyView.class).to(ProductView.class);
        bind(ShoppingBagItemPresenter.MyView.class).to(ShoppingBagItemView.class);
        bindSingletonPresenterWidget(ShoppingBagPresenter.class, ShoppingBagPresenter.MyView.class, ShoppingBagView.class);

        install(new GinFactoryModuleBuilder().build(PriceWidgetFactory.class));
        install(new GinFactoryModuleBuilder().build(ProductFactory.class));
        install(new GinFactoryModuleBuilder().build(ShoppingBagItemFactory.class));

        requestStaticInjection(Slider.class);
    }
}
