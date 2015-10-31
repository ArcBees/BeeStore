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

package com.arcbees.beeshop.client.application.widget.sidepanel.cart;

import com.arcbees.beeshop.client.application.ShoppingCartItem;
import com.arcbees.beeshop.client.resources.AppMessages;
import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.client.resources.ProductBrandUtil;
import com.arcbees.beeshop.common.dto.Product;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.arcbees.beeshop.common.dto.ProductType;
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

import static com.google.gwt.query.client.GQuery.$;

public class CartItemView extends ViewWithUiHandlers<CartItemUiHandlers>
        implements CartItemPresenter.MyView {
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

    @Inject
    CartItemView(
            Binder binder,
            AppMessages appMessages,
            ProductBrandUtil productBrandUtil,
            CartItemColorUtil cartItemColorUtil) {
        this.appMessages = appMessages;
        this.productBrandUtil = productBrandUtil;
        this.cartItemColorUtil = cartItemColorUtil;
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
    }

    @Override
    public void setShoppingCartItem(ShoppingCartItem item) {
        ProductDto productDto = item.getProductDto();
        Product product = productDto.getProduct();
        ProductType productType = product.getProductType();

        String translatedBrandName = appMessages.brandName(productDto.getBrand());
        String translatedProductType = appMessages.productName(productType);
        String translatedItemColor = appMessages.itemColor(productType, productDto.getBrand());
        String translatedLogoColor = appMessages.logoColor(productType, productDto.getBrand());
        String translatedSize = appMessages.size(product.getSize());

        name.setInnerText(translatedBrandName + " " + translatedProductType);
        itemColor.setInnerText(translatedItemColor);
        logoColor.setInnerText(translatedLogoColor);
        size.setInnerText(translatedSize);
        price.setInnerText(String.valueOf(productType.getPrice()));
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
