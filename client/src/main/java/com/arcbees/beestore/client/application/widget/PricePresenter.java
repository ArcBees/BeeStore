package com.arcbees.beestore.client.application.widget;

import javax.inject.Inject;

import com.arcbees.beestore.client.application.CurrentBrand;
import com.arcbees.beestore.client.events.BrandChangedEvent;
import com.arcbees.beestore.client.events.BrandChangedEventHandler;
import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.ProductDto;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class PricePresenter extends PresenterWidget<PricePresenter.MyView>
        implements PriceUiHandlers, BrandChangedEventHandler {
    interface MyView extends View, HasUiHandlers<PriceUiHandlers> {
        void setProduct(ProductDto product);
    }

    private final CurrentBrand currentBrand;

    private ProductDto product;

    @Inject
    PricePresenter(
            EventBus eventBus,
            MyView view,
            CurrentBrand currentBrand,
            @Assisted ProductDto product) {
        super(eventBus, view);

        this.currentBrand = currentBrand;
        this.product = product;

        getView().setUiHandlers(this);
    }

    @Override
    public void onBrandChanged(BrandChangedEvent event) {
        updateBrand(event.getBrand());
    }

    @Override
    protected void onReveal() {
        product.setBrand(currentBrand.get());
        getView().setProduct(product);
    }

    @Override
    protected void onBind() {
        addVisibleHandler(BrandChangedEvent.TYPE, this);

        getView().setProduct(product);
    }

    private void updateBrand(Brand brand) {
        product.setBrand(brand);
        getView().setProduct(product);
    }
}
