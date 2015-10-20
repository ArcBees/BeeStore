package com.arcbees.beeshop.client.application.widget;

import javax.inject.Inject;

import com.arcbees.beeshop.client.application.CurrentBrand;
import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.client.events.BrandChangedEventHandler;
import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.gwt.user.client.TakesValue;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class PricePresenter extends PresenterWidget<PricePresenter.MyView>
        implements PriceUiHandlers, TakesValue<ProductDto>, BrandChangedEventHandler {
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
    public ProductDto getValue() {
        return product;
    }

    @Override
    public void setValue(ProductDto value) {
        this.product = value;
        getView().setProduct(product);
    }

    @Override
    public void onBrandChanged(BrandChangedEvent event) {
        updateBrand(event.getBrand());
    }

    private void updateBrand(Brand brand) {
        product.setBrand(brand);
        getView().setProduct(product);
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
}
