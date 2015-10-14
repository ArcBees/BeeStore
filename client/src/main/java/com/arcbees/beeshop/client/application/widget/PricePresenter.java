package com.arcbees.beeshop.client.application.widget;

import javax.inject.Inject;

import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class PricePresenter extends PresenterWidget<PricePresenter.MyView> implements PriceUiHandlers {
    interface MyView extends View, HasUiHandlers<PriceUiHandlers> {
        void setProduct(ProductDto product);
    }

    private final ProductDto product;

    @Inject
    PricePresenter(
            EventBus eventBus,
            MyView view,
            @Assisted ProductDto product) {
        super(eventBus, view);
        this.product = product;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        getView().setProduct(product);
    }
}
