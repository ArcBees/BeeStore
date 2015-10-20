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

public class MainProductPresenter extends PresenterWidget<MainProductPresenter.MyView>
        implements BrandChangedEventHandler {
    interface MyView extends View {
        void setStyle(Side side);

        void setProduct(ProductDto productDto);
    }

    static SingleSlot<PricePresenter> SLOT_PRICE = new SingleSlot<>();

    private final Side side;
    private final ProductDto productDto;
    private final PriceWidgetFactory priceWidgetFactory;
    private final CurrentBrand currentBrand;

    @Inject
    MainProductPresenter(
            EventBus eventBus,
            MyView view,
            PriceWidgetFactory priceWidgetFactory,
            CurrentBrand currentBrand,
            @Assisted Side side,
            @Assisted ProductDto productDto) {
        super(eventBus, view);

        this.side = side;
        this.productDto = productDto;
        this.priceWidgetFactory = priceWidgetFactory;
        this.currentBrand = currentBrand;
    }

    @Override
    public void onBrandChanged(BrandChangedEvent event) {
        productDto.setBrand(event.getBrand());
        getView().setProduct(productDto);
    }

    @Override
    protected void onBind() {
        addVisibleHandler(BrandChangedEvent.TYPE, this);

        getView().setStyle(side);

        setInSlot(SLOT_PRICE, priceWidgetFactory.create(productDto));
    }

    @Override
    protected void onReveal() {
        productDto.setBrand(currentBrand.get());
        getView().setProduct(productDto);
    }
}
