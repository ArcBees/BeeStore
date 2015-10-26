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

package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.client.application.ShoppingBagItem;
import com.arcbees.beeshop.common.dto.Product;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class ShoppingBagItemView extends ViewWithUiHandlers<ShoppingBagItemUiHandlers>
        implements ShoppingBagItemPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, ShoppingBagItemView> {
    }

    @UiField
    HeadingElement name;
    @UiField
    SpanElement color;
    @UiField
    SpanElement brand;
    @UiField
    SpanElement size;
    @UiField
    InputElement quantity;
    @UiField
    SpanElement price;
    @UiField
    Element delete;

    @Inject
    ShoppingBagItemView(
            Binder binder) {
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
    public void setShoppingBagItem(ShoppingBagItem item) {
        Product product = item.getProductDto().getProduct();

        name.setInnerText(product.getName());
        color.setInnerText(product.getDescription());
        size.setInnerText(product.getSize());
        price.setInnerText(String.valueOf(product.getPrice()));
        quantity.setInnerText(String.valueOf(item.getQuantity()));
    }
}
