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

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class ShoppingBagView extends ViewWithUiHandlers<ShoppingBagUiHandlers>
        implements ShoppingBagPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, ShoppingBagView> {
    }

    @UiField
    SpanElement numberOfItems;
    @UiField
    HTMLPanel itemsContainer;

    @Inject
    ShoppingBagView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        bindSlot(ShoppingBagPresenter.SLOT_BAG_ITEM, itemsContainer);
    }

    @Override
    public void updateItemNumber(int number) {
        $(numberOfItems).text(String.valueOf(number));
    }
}
