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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.arcbees.beeshop.client.NameTokens;
import com.arcbees.beeshop.client.application.widget.PricePresenter;
import com.arcbees.beeshop.client.application.widget.PriceWidgetFactory;
import com.arcbees.beeshop.client.resources.AppMessages;
import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

public class HomeView extends ViewImpl implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField(provided = true)
    PricePresenter shirtPrice;
    @UiField(provided = true)
    PricePresenter bagPrice;
    @UiField
    AnchorElement gaeAnchor;
    @UiField
    AnchorElement jukitoAnchor;
    @UiField
    AnchorElement gwtpAnchor;
    @UiField
    AnchorElement arcbeesAnchor;
    @UiField
    AnchorElement gqueryAnchor;
    @UiField
    AnchorElement gsssAnchor;
    @UiField
    AnchorElement chosenAnchor;

    private final PlaceManager placeManager;
    private final TokenFormatter tokenFormatter;
    private final AppMessages appMessages;
    private final List<PricePresenter> products;

    @Inject
    HomeView(
            Binder uiBinder,
            PriceWidgetFactory priceWidgetFactory,
            PlaceManager placeManager,
            TokenFormatter tokenFormatter,
            AppMessages appMessages) {
        this.placeManager = placeManager;
        this.tokenFormatter = tokenFormatter;
        this.appMessages = appMessages;

        products = new ArrayList<>();

        ProductDto shirt = new ProductDto();
        shirt.setBrandName(appMessages.brandName(Brand.ARCBEES));
        shirt.setPrice(55);
        shirt.setProductName("Shirt");

        ProductDto bag = new ProductDto();
        bag.setBrandName(appMessages.brandName(Brand.ARCBEES));
        bag.setPrice(35);
        bag.setProductName("Bag");

        shirtPrice = priceWidgetFactory.create(shirt);
        bagPrice = priceWidgetFactory.create(bag);

        products.add(shirtPrice);
        products.add(bagPrice);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void changeBrand(Brand brand) {
        for (PricePresenter product : products) {
            ProductDto value = product.getValue();

            value.setBrandName(appMessages.brandName(brand));

            product.setValue(value);
        }
    }

    @Override
    protected void onAttach() {
        PlaceRequest currentPlaceRequest = placeManager.getCurrentPlaceRequest();

        setAnchor(currentPlaceRequest, NameTokens.GAE_STUDIO, gaeAnchor);
        setAnchor(currentPlaceRequest, NameTokens.ARCBEES, arcbeesAnchor);
        setAnchor(currentPlaceRequest, NameTokens.CHOSEN, chosenAnchor);
        setAnchor(currentPlaceRequest, NameTokens.JUKITO, jukitoAnchor);
        setAnchor(currentPlaceRequest, NameTokens.GWTP, gwtpAnchor);
        setAnchor(currentPlaceRequest, NameTokens.GQUERY, gqueryAnchor);
        setAnchor(currentPlaceRequest, NameTokens.GSSS, gsssAnchor);
    }

    private void setAnchor(PlaceRequest currentPlaceRequest, String brand, AnchorElement anchor) {
        PlaceRequest placeRequest = new PlaceRequest.Builder(currentPlaceRequest)
                .with(NameTokens.PARAM_BRAND, brand)
                .build();

        anchor.setHref("#" + tokenFormatter.toPlaceToken(placeRequest));
    }
}
