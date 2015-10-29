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

import com.arcbees.beeshop.common.NameTokens;
import com.arcbees.beeshop.client.application.ApplicationPresenter;
import com.arcbees.beeshop.client.application.CurrentOrder;
import com.arcbees.beeshop.client.application.ShoppingCartItem;
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
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class ProductPresenter extends Presenter<ProductPresenter.MyView, ProductPresenter.MyProxy>
        implements ProductPresenterUiHandlers {
    interface MyView extends View, HasUiHandlers<ProductPresenterUiHandlers> {
        void hideSharePanel();

        void showSharePanel();

        void setProduct(ProductDto productDto);
    }

    @ProxyStandard
    @NameToken({NameTokens.PRODUCT, NameTokens.PRODUCT_FR})
    interface MyProxy extends ProxyPlace<ProductPresenter> {
    }

    static final PermanentSlot<SharePanelPresenter> SLOT_SHARE_PANEL = new PermanentSlot<>();

    private final CurrentOrder currentOrder;
    private final CurrentProduct currentProduct;

    private boolean isSharePanelShown;

    @Inject
    ProductPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            SharePanelPresenter sharePanel,
            CurrentOrder currentOrder,
            CurrentProduct currentProduct) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.currentOrder = currentOrder;
        this.currentProduct = currentProduct;

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
    public void prepareFromRequest(PlaceRequest request) {
        getView().setProduct(currentProduct.get());
    }

    @Override
    public void onAddToCartButtonClicked() {
        // TODO: Populate this object depending on the product selected
        currentOrder.addItem(dummyItem());
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

    private ShoppingCartItem dummyItem() {
        ProductDto productDto = new ProductDto();
        productDto.setBrand(Brand.ARCBEES);
        productDto.setProduct(Product.BAG);

        return new ShoppingCartItem(productDto, 2);
    }
}
