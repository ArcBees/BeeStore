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

package com.arcbees.beestore.client.application.widget.sidepanel.checkout;

import javax.inject.Inject;

import com.arcbees.beestore.client.application.ShippingMethod;
import com.arcbees.ui.ReplacePanel;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.SpanElement;
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
    @UiField
    SpanElement tax;
    @UiField
    SpanElement orderTotal;
    @UiField
    InputElement standard;
    @UiField
    InputElement international;
    @UiField
    SpanElement standardPrice;
    @UiField
    SpanElement internationalPrice;

    /*
    This is to counter the fact that UiBinder does not allow both id and ui:field
    attributes to be set on the same element.
    */
    @UiField(provided = true)
    final String standardId = Document.get().createUniqueId();
    @UiField(provided = true)
    final String internationalId = Document.get().createUniqueId();

    @Inject
    OrderView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        bindSlot(OrderPresenter.SLOT_CART_ITEMS, cartItems);

        bind();
    }

    private void bind() {
        standard.setId(standardId);
        international.setId(internationalId);

        $(continueButton).click(new Function() {
            @Override
            public void f() {
                getUiHandlers().onContinueClicked();
            }
        });

        $(standard, international).click(new Function() {
            @Override
            public void f() {
                getUiHandlers().setShippingMethod(getShippingMethod());
            }
        });
    }

    @Override
    public void hideCheckoutButton() {
        $(continueButton).hide();
    }

    @Override
    public void setTaxes(String taxes) {
        tax.setInnerText(taxes);
    }

    @Override
    public void setOrderTotal(String grandTotal) {
        orderTotal.setInnerText(grandTotal);
    }

    @Override
    public void setStandardPrice(String price) {
        standardPrice.setInnerText(price);
    }

    @Override
    public void setInternationalPrice(String price) {
        internationalPrice.setInnerText(price);
    }

    private ShippingMethod getShippingMethod() {
        if (standard.isChecked()) {
            return ShippingMethod.STANDARD;
        }

        return ShippingMethod.INTERNATIONAL;
    }
}
