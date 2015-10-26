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

package com.arcbees.beeshop.client.application.product;

import javax.inject.Inject;

import com.arcbees.beeshop.client.NameTokens;
import com.arcbees.beeshop.client.application.ApplicationPresenter;
import com.arcbees.beeshop.client.application.CurrentShoppingBag;
import com.arcbees.beeshop.client.application.ShoppingBagItem;
import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.Product;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class ProductPresenter extends Presenter<ProductPresenter.MyView, ProductPresenter.MyProxy>
        implements ProductPresenterUiHandlers {
    interface MyView extends View, HasUiHandlers<ProductPresenterUiHandlers> {
        void hideSharePanel();

        void showSharePanel();
    }

    @ProxyStandard
    @NameToken(NameTokens.PRODUCTS)
    interface MyProxy extends ProxyPlace<ProductPresenter> {
    }

    static final PermanentSlot<SharePanelPresenter> SLOT_SHARE_PANEL = new PermanentSlot<>();

    private final CurrentShoppingBag currentShoppingBag;

    private boolean isSharePanelShown;

    @Inject
    ProductPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            SharePanelPresenter sharePanel,
            CurrentShoppingBag currentShoppingBag) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.currentShoppingBag = currentShoppingBag;

        setInSlot(SLOT_SHARE_PANEL, sharePanel);
        getView().setUiHandlers(this);
    }

    @Override
    public void onShareButtonClicked() {
        if (isSharePanelShown) {
            hideSharePanel();
        } else {
            showSharePanel();
        }
    }

    @Override
    public void onAddToCartButtonClicked() {
        // TODO: Populate this object depending on the product selected
        currentShoppingBag.addItem(dummyItem());
    }

    @Override
    protected void onReveal() {
        hideSharePanel();
    }

    private void showSharePanel() {
        getView().showSharePanel();
        isSharePanelShown = true;
    }

    private void hideSharePanel() {
        getView().hideSharePanel();
        isSharePanelShown = false;
    }

    private ShoppingBagItem dummyItem() {
        ProductDto productDto = new ProductDto();
        productDto.setBrand(Brand.ARCBEES);
        productDto.setProduct(Product.BAG);

        return new ShoppingBagItem(productDto, 2);
    }
}
