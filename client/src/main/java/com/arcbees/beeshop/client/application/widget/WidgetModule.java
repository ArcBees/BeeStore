
package com.arcbees.beeshop.client.application.widget;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class WidgetModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(PricePresenter.MyView.class).to(PriceView.class);
        install(new GinFactoryModuleBuilder().build(PriceWidgetFactory.class));
    }
}
