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

package com.arcbees.beestore.client.application.widget.sidepanel.cart.cartitem;

import com.arcbees.beestore.client.application.CurrencyFormat;
import com.arcbees.beestore.client.application.ShoppingCartItem;
import com.arcbees.beestore.client.resources.AppMessages;
import com.arcbees.beestore.client.resources.AppResources;
import com.arcbees.beestore.client.resources.ProductBrandUtil;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;
import com.google.common.base.Strings;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.event.dom.client.KeyCodes.KEY_ENTER;
import static com.google.gwt.query.client.GQuery.$;

public class CartItemView extends ViewWithUiHandlers<CartItemUiHandlers> implements CartItemPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, CartItemView> {
    }

    @UiField
    HeadingElement name;
    @UiField
    SpanElement itemColor;
    @UiField
    SpanElement logoColor;
    @UiField
    SpanElement size;
    @UiField
    InputElement quantity;
    @UiField
    SpanElement price;
    @UiField
    Element delete;
    @UiField
    AppResources res;
    @UiField
    Image productThumbnail;

    private final AppMessages appMessages;
    private final ProductBrandUtil productBrandUtil;
    private final CartItemColorUtil cartItemColorUtil;
    private final CurrencyFormat currencyFormat;

    @Inject
    CartItemView(
            Binder binder,
            AppMessages appMessages,
            ProductBrandUtil productBrandUtil,
            CartItemColorUtil cartItemColorUtil,
            CurrencyFormat currencyFormat) {
        this.appMessages = appMessages;
        this.productBrandUtil = productBrandUtil;
        this.cartItemColorUtil = cartItemColorUtil;
        this.currencyFormat = currencyFormat;

        initWidget(binder.createAndBindUi(this));

        init();
    }

    private void init() {
        $(delete).click(new Function() {
            @Override
            public void f() {
                getUiHandlers().delete();
            }
        });

        $(quantity).blur(new Function() {
            @Override
            public void f() {
                updateQuantity();
            }
        }).keypress(new Function() {
            @Override
            public void f() {
                if (getEvent().getKeyCode() == KEY_ENTER) {
                    updateQuantity();
                }
            }
        });
    }

    private void updateQuantity() {
        getUiHandlers().onQuantityChangedInView(getQuantity());
    }

    private int getQuantity() {
        int quantity = 0;
        if (!Strings.isNullOrEmpty(this.quantity.getValue())) {
            quantity = Integer.parseInt(this.quantity.getValue());
        }

        if (quantity < 0) {
            quantity = 1;
        }

        return quantity;
    }

    @Override
    public void setShoppingCartItem(ShoppingCartItem item) {
        ProductDto productDto = item.getProductDto();
        ProductType productType = productDto.getProductType();
        int productPrice = productType.getPrice();

        String translatedBrandName = appMessages.brandName(productDto.getBrand());
        String translatedProductType = appMessages.productName(productType);
        String translatedItemColor = appMessages.itemColor(productType, productDto.getBrand());
        String translatedLogoColor = appMessages.logoColor(productType, productDto.getBrand());
        String translatedSize = appMessages.size(productDto.getSize());

        name.setInnerText(translatedBrandName + " " + translatedProductType);
        itemColor.setInnerText(translatedItemColor);
        logoColor.setInnerText(translatedLogoColor);
        size.setInnerText(translatedSize);
        price.setInnerText(currencyFormat.format(productPrice));
        quantity.setValue(String.valueOf(item.getQuantity()));
        productThumbnail.setResource(productBrandUtil.getThumbnail(productType, productDto.getBrand()));

        if (productType.equals(ProductType.SHIRT)) {
            $(asWidget()).addClass(cartItemColorUtil.getShirtClass(productDto.getBrand()));
        }
    }

    @Override
    public void updateQuantity(int newQuantity) {
        quantity.setValue(String.valueOf(newQuantity));
    }
}
