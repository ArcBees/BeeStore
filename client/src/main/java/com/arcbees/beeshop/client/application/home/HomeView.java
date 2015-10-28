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

package com.arcbees.beeshop.client.application.home;

import javax.inject.Inject;

import com.arcbees.beeshop.client.application.widget.brandpicker.BrandPicker;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeView extends ViewImpl implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    HTMLPanel secondaryProducts;
    @UiField
    HTMLPanel mainProducts;
    @UiField(provided = true)
    BrandPicker brandPicker;

    @Inject
    HomeView(
            Binder uiBinder,
            BrandPicker brandPicker) {

        this.brandPicker = brandPicker;

        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(HomePresenter.SLOT_MAIN_PRODUCTS, mainProducts);
        bindSlot(HomePresenter.SLOT_SECONDARY_PRODUCTS, secondaryProducts);
    }
}
