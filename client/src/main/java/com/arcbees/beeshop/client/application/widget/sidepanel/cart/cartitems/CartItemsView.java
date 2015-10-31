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

package com.arcbees.beeshop.client.application.widget.sidepanel.cart.cartitems;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import static com.google.gwt.query.client.GQuery.$;

public class CartItemsView extends ViewImpl implements CartItemsPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, CartItemsView> {
    }

    @UiField
    HTMLPanel itemsContainer;
    @UiField
    SpanElement subTotal;
    @UiField
    DivElement subTotalContainer;
    @UiField
    DivElement noItems;

    @Inject
    CartItemsView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        bindSlot(CartItemsPresenter.SLOT_ITEMS, itemsContainer);
    }

    @Override
    public void showAndSetSubTotal(float subTotal) {
        this.subTotal.setInnerText(subTotal + " $");

        $(subTotalContainer).show();
        $(noItems).hide();
    }

    @Override
    public void showEmpty() {
        $(subTotalContainer).hide();
        $(noItems).show();
    }
}
