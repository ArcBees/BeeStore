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

package com.arcbees.beestore.client.application.widget;

import com.arcbees.beestore.client.resources.AppResources;
import com.arcbees.beestore.client.resources.NameTokensConstants;
import com.arcbees.beestore.client.resources.PriceMessages;
import com.arcbees.beestore.common.NameTokens;
import com.arcbees.beestore.common.dto.ProductDto;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

import static com.google.gwt.query.client.GQuery.$;

public class PriceView extends ViewWithUiHandlers<PriceUiHandlers>
        implements PricePresenter.MyView {
    interface Binder extends UiBinder<Widget, PriceView> {
    }

    @UiField
    DivElement brandName;
    @UiField
    DivElement productName;
    @UiField
    DivElement price;
    @UiField
    AnchorElement shopView;
    @UiField
    AnchorElement priceView;
    @UiField
    AppResources res;

    private final TokenFormatter tokenFormatter;
    private final PriceMessages messages;
    private final PlaceManager placeManager;
    private final NameTokensConstants nameTokensConstants;

    @Inject
    PriceView(
            Binder binder,
            TokenFormatter tokenFormatter,
            PriceMessages messages,
            PlaceManager placeManager,
            NameTokensConstants nameTokensConstants) {
        this.tokenFormatter = tokenFormatter;
        this.messages = messages;
        this.placeManager = placeManager;
        this.nameTokensConstants = nameTokensConstants;

        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setProduct(ProductDto product) {
        String smallWordsStyleName = res.style().smallWords();
        String smallerWordsStyle = res.style().smallerWords();

        String brandNameText = messages.brandName(product.getBrand(), smallWordsStyleName, smallerWordsStyle);
        String productNameText = messages.productName(product.getProductType(), smallWordsStyleName, smallerWordsStyle);

        brandName.setInnerHTML(brandNameText);
        productName.setInnerHTML(productNameText);

        price.setInnerText(product.getPrice() + " $");

        buildAnchorUri(product);
    }

    private void buildAnchorUri(ProductDto product) {
        String productId = String.valueOf(product.getProductType().getId());

        PlaceRequest request = new PlaceRequest.Builder(placeManager.getCurrentPlaceRequest())
                .nameToken(nameTokensConstants.PRODUCT())
                .with(NameTokens.PARAM_ID, productId)
                .build();

        String token = tokenFormatter.toPlaceToken(request);
        shopView.setHref("#" + token);
    }

    @Override
    protected void onAttach() {
        setShopViewVisible(false);

        $(asWidget()).mouseover(new Function() {
            @Override
            public void f() {
                setShopViewVisible(true);
            }
        });

        $(asWidget()).mouseout(new Function() {
            @Override
            public void f() {
                setShopViewVisible(false);
            }
        });
    }

    private void setShopViewVisible(boolean visible) {
        if (visible) {
            $(shopView).show();
            $(priceView).hide();
        } else {
            $(shopView).hide();
            $(priceView).show();
        }
    }
}
