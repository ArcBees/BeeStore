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

package com.arcbees.beestore.client.application.home;

import java.util.List;

import javax.inject.Inject;

import com.arcbees.beestore.client.RestCallbackImpl;
import com.arcbees.beestore.client.application.ApplicationPresenter;
import com.arcbees.beestore.client.application.CurrentBrand;
import com.arcbees.beestore.client.application.widget.ProductPresenter;
import com.arcbees.beestore.client.application.widget.ProductWidgetFactory;
import com.arcbees.beestore.client.application.widget.ProductWidgetType;
import com.arcbees.beestore.common.NameTokens;
import com.arcbees.beestore.common.api.ProductResource;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {
    interface MyView extends View {
        void updateSeo();
    }

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }

    static Slot<ProductPresenter> SLOT_MAIN_PRODUCTS = new Slot<>();
    static Slot<ProductPresenter> SLOT_SECONDARY_PRODUCTS = new Slot<>();

    private final ProductWidgetFactory productWidgetFactory;
    private final CurrentBrand currentBrand;

    private ResourceDelegate<ProductResource> productDelegate;

    @Inject
    HomePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            ProductWidgetFactory productWidgetFactory,
            CurrentBrand currentBrand,
            ResourceDelegate<ProductResource> productDelegate) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.productWidgetFactory = productWidgetFactory;
        this.currentBrand = currentBrand;
        this.productDelegate = productDelegate;
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        getView().updateSeo();
    }

    @Override
    protected void onReveal() {
        productDelegate.withCallback(new RestCallbackImpl<List<ProductDto>>() {
            @Override
            public void onSuccess(List<ProductDto> result) {
                setProductsInSlots(result);
            }
        }).getProductsByBrand(currentBrand.get().getValue());
    }

    private void setProductsInSlots(List<ProductDto> result) {
        for (ProductDto productDto : result) {
            if (isFeaturedLeftProductType(productDto)) {
                addToSlot(SLOT_MAIN_PRODUCTS, productWidgetFactory.create(ProductWidgetType.MAIN_LEFT, productDto));
            } else if (isFeaturedRightProductType(productDto)) {
                addToSlot(SLOT_MAIN_PRODUCTS, productWidgetFactory.create(ProductWidgetType.MAIN_RIGHT, productDto));
            } else {
                addToSlot(SLOT_SECONDARY_PRODUCTS, productWidgetFactory.create(ProductWidgetType.SECONDARY,
                        productDto));
            }
        }
    }

    private boolean isFeaturedRightProductType(ProductDto productDto) {
        return productDto.getProductType().equals(ProductType.BAG);
    }

    private boolean isFeaturedLeftProductType(ProductDto productDto) {
        return productDto.getProductType().equals(ProductType.SHIRT);
    }
}
