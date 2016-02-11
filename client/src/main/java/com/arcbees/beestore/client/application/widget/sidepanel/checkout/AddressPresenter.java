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

import com.arcbees.beestore.client.events.CheckoutContinueEvent;
import com.arcbees.beestore.common.dto.ContactInfoDto;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class AddressPresenter extends PresenterWidget<AddressPresenter.MyView> implements AddressUiHandlers {
    interface MyView extends View, HasUiHandlers<AddressUiHandlers> {
        ContactInfoDto getContactInfo();
    }

    @Inject
    AddressPresenter(
            EventBus eventBus,
            MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);
    }

    @Override
    public void onContinueClicked() {
        if (validateContactInfo()) {
            CheckoutContinueEvent.fire(this);
        }
    }

    private boolean validateContactInfo() {
        ContactInfoDto contactInfo = getContactInfo();

        return !Strings.isNullOrEmpty(contactInfo.getFirstName()) &&
                !Strings.isNullOrEmpty(contactInfo.getLastName()) &&
                !Strings.isNullOrEmpty(contactInfo.getEmail()) &&
                !Strings.isNullOrEmpty(contactInfo.getPhone()) &&
                !Strings.isNullOrEmpty(contactInfo.getAddress()) &&
                !Strings.isNullOrEmpty(contactInfo.getTownCity());
    }

    public ContactInfoDto getContactInfo() {
        return getView().getContactInfo();
    }
}
