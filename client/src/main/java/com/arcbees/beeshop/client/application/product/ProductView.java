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

package com.arcbees.beeshop.client.application.product;

import javax.inject.Inject;

import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.client.resources.FontResources;
import com.arcbees.ui.ReplacePanel;
import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.arcbees.beeshop.client.application.product.ProductPresenter.SLOT_SHARE_PANEL;
import static com.google.gwt.query.client.GQuery.$;

public class ProductView extends ViewWithUiHandlers<ProductPresenterUiHandlers> implements ProductPresenter.MyView {
    interface Binder extends UiBinder<Widget, ProductView> {
    }

    @UiField
    FontResources font;
    @UiField
    AppResources res;
    @UiField
    Element shareButton;
    @UiField
    ReplacePanel sharePanel;

    @Inject
    ProductView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(SLOT_SHARE_PANEL, sharePanel);

        bind();
    }

    private void bind() {
        $(shareButton).click(new Function() {
            @Override
            public void f() {
                getUiHandlers().onShareButtonClicked();
            }
        });
    }

    @Override
    public void hideSharePanel() {
        shareButton.setClassName(font.icons().iconShare());
        $(sharePanel).hide();
    }

    @Override
    public void showSharePanel() {
        shareButton.setClassName(font.icons().iconClose() + " " + res.style().share_close());
        $(sharePanel).show();
    }
}
