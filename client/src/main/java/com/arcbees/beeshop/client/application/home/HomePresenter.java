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

package com.arcbees.beeshop.client.application.home;

import javax.inject.Inject;

import com.arcbees.beeshop.client.NameTokens;
import com.arcbees.beeshop.client.application.ApplicationPresenter;
import com.arcbees.beeshop.client.application.CurrentBrand;
import com.arcbees.beeshop.client.application.widget.MainProductPresenter;
import com.arcbees.beeshop.client.application.widget.ProductFactory;
import com.arcbees.beeshop.client.application.widget.ProductWidgetType;
import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.Product;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {
    interface MyView extends View {
    }

    static Slot<MainProductPresenter> SLOT_MAIN_PRODUCTS = new Slot<>();
    static Slot<MainProductPresenter> SLOT_SECONDARY_PRODUCTS = new Slot<>();

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }

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
    protected void onBind() {
        Brand brand = currentBrand.get();

        ProductDto shirt = new ProductDto();
        shirt.setBrand(brand);
        shirt.setProduct(Product.SHIRT);

        ProductDto bag = new ProductDto();
        bag.setBrand(brand);
        bag.setProduct(Product.BAG);

        addToSlot(SLOT_MAIN_PRODUCTS, productFactory.create(ProductWidgetType.MAIN_LEFT, shirt));
        addToSlot(SLOT_MAIN_PRODUCTS, productFactory.create(ProductWidgetType.MAIN_RIGHT, bag));

        ProductDto cup = new ProductDto();
        cup.setBrand(brand);
        cup.setProduct(Product.THERMOS);

        ProductDto phoneCase = new ProductDto();
        phoneCase.setBrand(brand);
        phoneCase.setProduct(Product.PHONE_CASE);

        ProductDto key = new ProductDto();
        key.setBrand(brand);
        key.setProduct(Product.USB_KEY);

        ProductDto mug = new ProductDto();
        mug.setBrand(brand);
        mug.setProduct(Product.MUG);

        MainProductPresenter cupPresenter = productFactory.create(ProductWidgetType.SECONDARY, cup);
        MainProductPresenter keyPresenter = productFactory.create(ProductWidgetType.SECONDARY, key);
        MainProductPresenter mugPresenter = productFactory.create(ProductWidgetType.SECONDARY, mug);
        MainProductPresenter phoneCasePresenter = productFactory.create(ProductWidgetType.SECONDARY, phoneCase);

        addToSlot(SLOT_SECONDARY_PRODUCTS, cupPresenter);
        addToSlot(SLOT_SECONDARY_PRODUCTS, phoneCasePresenter);
        addToSlot(SLOT_SECONDARY_PRODUCTS, keyPresenter);
        addToSlot(SLOT_SECONDARY_PRODUCTS, mugPresenter);
    }
}
