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

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class PaymentView extends ViewWithUiHandlers<PaymentUiHandlers> implements PaymentPresenter.MyView {
    interface Binder extends UiBinder<Widget, PaymentView> {
    }

    @UiField
    FormElement form;
    @UiField
    InputElement name;
    @UiField
    InputElement number;
    @UiField
    InputElement cvs;
    @UiField
    InputElement expMonth;
    @UiField
    InputElement expYear;
    @UiField
    ButtonElement submit;

    @Inject
    PaymentView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        bind();

        disablePaymentSubmit();
    }

    private void bind() {
        $(form).submit(new Function() {
            @Override
            public void f() {
                getUiHandlers().onSubmit(
                        name.getValue(),
                        number.getValue(),
                        cvs.getValue(),
                        Integer.parseInt(expMonth.getValue()),
                        Integer.parseInt(expYear.getValue()));
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        Window.alert(message);
    }

    @Override
    public void showSuccessMessage(String message) {
        Window.alert(message);
    }

    @Override
    public void enablePaymentSubmit() {
        submit.setDisabled(false);
    }

    @Override
    public void disablePaymentSubmit() {
        submit.setDisabled(true);
    }
}
