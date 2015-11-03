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

package com.arcbees.beestore.client.application.widget;

import com.arcbees.beestore.client.application.CurrentBrand;
import com.arcbees.beestore.client.events.BrandChangedEvent;
import com.arcbees.beestore.client.events.BrandChangedEventHandler;
import com.arcbees.beestore.common.dto.ProductDto;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;

public class ProductPresenter extends PresenterWidget<ProductPresenter.MyView>
        implements BrandChangedEventHandler {
    interface MyView extends View {
        void setStyle(ProductWidgetType productWidgetType);

        void setProduct(ProductDto productDto);
    }

    static SingleSlot<PricePresenter> SLOT_PRICE = new SingleSlot<>();

    private final ProductWidgetType productWidgetType;
    private final ProductDto productDto;
    private final PriceWidgetFactory priceWidgetFactory;
    private final CurrentBrand currentBrand;

    @Inject
    ProductPresenter(
            EventBus eventBus,
            MyView view,
            PriceWidgetFactory priceWidgetFactory,
            CurrentBrand currentBrand,
            @Assisted ProductWidgetType productWidgetType,
            @Assisted ProductDto productDto) {
        super(eventBus, view);

        this.productWidgetType = productWidgetType;
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

        getView().setStyle(productWidgetType);

        setInSlot(SLOT_PRICE, priceWidgetFactory.create(productDto));
    }

    @Override
    protected void onReveal() {
        productDto.setBrand(currentBrand.get());
        getView().setProduct(productDto);
    }
}
