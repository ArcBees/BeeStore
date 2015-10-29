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

package com.arcbees.beeshop.client.application.widget.sidepanel.checkout;

import javax.inject.Inject;

import com.arcbees.ui.ReplacePanel;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class OrderView extends ViewWithUiHandlers<OrderUiHandlers> implements OrderPresenter.MyView {
    interface Binder extends UiBinder<Widget, OrderView> {
    }

    @UiField
    ReplacePanel cartItems;
    @UiField
    ButtonElement continueButton;

    @Inject
    OrderView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        bindSlot(OrderPresenter.SLOT_CART_ITEMS, cartItems);

        bind();
    }

    private void bind() {
        $(continueButton).click(new Function() {
            @Override
            public void f() {
                getUiHandlers().onContinueClicked();
            }
        });
    }
}
