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

import javax.inject.Inject;

import com.arcbees.beestore.client.application.ApplicationPresenter;
import com.arcbees.beestore.client.application.CurrentBrand;
import com.arcbees.beestore.client.application.widget.ProductFactory;
import com.arcbees.beestore.client.application.widget.ProductPresenter;
import com.arcbees.beestore.client.application.widget.ProductWidgetType;
import com.arcbees.beestore.common.NameTokens;
import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.Product;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;
import com.google.web.bindery.event.shared.EventBus;
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

    private final ProductFactory productFactory;
    private final CurrentBrand currentBrand;

    @Inject
    HomePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            ProductFactory productFactory,
            CurrentBrand currentBrand) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.productFactory = productFactory;
        this.currentBrand = currentBrand;
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        getView().updateSeo();
    }

    @Override
    protected void onBind() {
        Brand brand = currentBrand.get();

        ProductDto shirt = new ProductDto(Product.createShirtWithDefaultSize(), brand);
        ProductDto bag = new ProductDto(Product.createProduct(ProductType.BAG), brand);

        addToSlot(SLOT_MAIN_PRODUCTS, productFactory.create(ProductWidgetType.MAIN_LEFT, shirt));
        addToSlot(SLOT_MAIN_PRODUCTS, productFactory.create(ProductWidgetType.MAIN_RIGHT, bag));

        ProductDto cup = new ProductDto(Product.createProduct(ProductType.THERMOS), brand);
        ProductDto phoneCase = new ProductDto(Product.createProduct(ProductType.PHONE_CASE), brand);
        ProductDto key = new ProductDto(Product.createProduct(ProductType.USB_KEY), brand);
        ProductDto mug = new ProductDto(Product.createProduct(ProductType.MUG), brand);

        ProductPresenter cupPresenter = productFactory.create(ProductWidgetType.SECONDARY, cup);
        ProductPresenter keyPresenter = productFactory.create(ProductWidgetType.SECONDARY, key);
        ProductPresenter mugPresenter = productFactory.create(ProductWidgetType.SECONDARY, mug);
        ProductPresenter phoneCasePresenter = productFactory.create(ProductWidgetType.SECONDARY, phoneCase);

        addToSlot(SLOT_SECONDARY_PRODUCTS, cupPresenter);
        addToSlot(SLOT_SECONDARY_PRODUCTS, phoneCasePresenter);
        addToSlot(SLOT_SECONDARY_PRODUCTS, keyPresenter);
        addToSlot(SLOT_SECONDARY_PRODUCTS, mugPresenter);
    }
}
