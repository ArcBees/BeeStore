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

import com.arcbees.beestore.common.dto.ContactInfoDto;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class AddressView extends ViewWithUiHandlers<AddressUiHandlers> implements AddressPresenter.MyView {
    interface Binder extends UiBinder<Widget, AddressView> {
    }

    @UiField
    ButtonElement continueButton;
    @UiField
    InputElement firstName;
    @UiField
    InputElement lastName;
    @UiField
    InputElement company;
    @UiField
    InputElement address;
    @UiField
    InputElement townCity;
    @UiField
    InputElement state;
    @UiField
    InputElement country;
    @UiField
    InputElement email;
    @UiField
    InputElement phone;
    @UiField
    SpanElement backToCart;

    @Inject
    AddressView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        bind();
    }

    @Override
    public ContactInfoDto getContactInfo() {
        ContactInfoDto contactInfo = new ContactInfoDto();
        contactInfo.setFirstName(firstName.getValue());
        contactInfo.setLastName(lastName.getValue());
        contactInfo.setCompany(company.getValue());
        contactInfo.setAddress(address.getValue());
        contactInfo.setTownCity(townCity.getValue());
        contactInfo.setState(state.getValue());
        contactInfo.setCountry(country.getValue());
        contactInfo.setEmail(email.getValue());
        contactInfo.setPhone(phone.getValue());

        return contactInfo;
    }

    @Override
    public void hideContinueButton() {
        $(continueButton).hide();
    }

    private void bind() {
        $(continueButton).click(new Function() {
            @Override
            public void f() {
                getUiHandlers().onContinueClicked();
            }
        });

        $(firstName, lastName, company, address, townCity, state, country, email, phone).change(new Function() {
            @Override
            public void f() {
                getUiHandlers().onPaymentDetailsUpdated();
            }
        });

        $(backToCart).click(new Function() {
            @Override
            public void f() {
                getUiHandlers().onBackToCart();
            }
        });
    }
}
