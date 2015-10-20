package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.client.application.CurrentBrand;
import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.client.events.BrandChangedEventHandler;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;

public class SecondaryProductPresenter extends PresenterWidget<SecondaryProductPresenter.MyView>
        implements BrandChangedEventHandler {
    interface MyView extends View {
        void setProduct(ProductDto productDto);
    }

    static SingleSlot<PricePresenter> SLOT_PRICE = new SingleSlot<>();

    private final PriceWidgetFactory priceWidgetFactory;
    private final ProductDto productDto;
    private final CurrentBrand currentBrand;

    @Inject
    SecondaryProductPresenter(
            EventBus eventBus,
            MyView view,
            PriceWidgetFactory priceWidgetFactory,
            CurrentBrand currentBrand,
            @Assisted ProductDto productDto) {
        super(eventBus, view);

        this.priceWidgetFactory = priceWidgetFactory;
        this.productDto = productDto;
        this.currentBrand = currentBrand;
    }

    @Override
    public void onBrandChanged(BrandChangedEvent event) {
        productDto.setBrand(event.getBrand());
        getView().setProduct(productDto);
    }

    @Override
    protected void onReveal() {
        productDto.setBrand(currentBrand.get());
        getView().setProduct(productDto);
    }

    @Override
    protected void onBind() {
        addVisibleHandler(BrandChangedEvent.TYPE, this);

        setInSlot(SLOT_PRICE, priceWidgetFactory.create(productDto));
    }
}
