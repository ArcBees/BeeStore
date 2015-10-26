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

package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import com.arcbees.beeshop.client.NameTokens;
import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.client.resources.FontResources;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

import static com.google.gwt.query.client.GQuery.$;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    private static final int ANIMATION_DURATION = 400;

    @UiField
    SimplePanel main;
    @UiField
    SimplePanel shoppingBagWidget;
    @UiField
    ButtonElement cartButton;
    @UiField
    Object backTop;
    @UiField
    AnchorElement englishAnchor;
    @UiField
    AnchorElement frenchAnchor;
    @UiField
    SpanElement numberOfItems;
    @UiField
    Element cartIcon;

    private final AppResources resources;
    private final FontResources fontResources;
    private final TokenFormatter formatter;
    private final PlaceManager placeManager;

    private Boolean shoppingBagOpen;

    @Inject
    ApplicationView(
            Binder uiBinder,
            TokenFormatter formatter,
            PlaceManager placeManager,
            AppResources resources,
            FontResources fontResources) {
        this.formatter = formatter;
        this.placeManager = placeManager;
        this.resources = resources;
        this.fontResources = fontResources;

        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(ApplicationPresenter.SLOT_MAIN, main);
        bindSlot(ApplicationPresenter.SLOT_CART_WIDGET, shoppingBagWidget);

        bind();
    }

    private void bind() {
        $(shoppingBagWidget).hide();
        shoppingBagOpen = false;

        $(backTop).click(new Function() {
            @Override
            public void f() {
                $("html, body").each(new Function() {
                    @Override
                    public void f(Element element) {
                        new ScrollTopAnimation(element).run(ANIMATION_DURATION);
                    }
                });
            }
        });

        $(cartButton).click(new Function() {
            @Override
            public void f() {
                if (shoppingBagOpen) {
                    $(shoppingBagWidget).show();
                    $(cartIcon).attr("class", fontResources.icons().iconClose());
                    shoppingBagOpen = false;
                } else {
                    $(shoppingBagWidget).hide();
                    $(cartIcon).attr("class", fontResources.icons().iconCart());
                    shoppingBagOpen = true;
                }
            }
        });

        setI18nAnchors();
    }

    private void setI18nAnchors() {
        PlaceRequest currentPlaceRequest = placeManager.getCurrentPlaceRequest();
        setLanguageAnchor(currentPlaceRequest, NameTokens.LANGUAGE_FRENCH, frenchAnchor);
        setLanguageAnchor(currentPlaceRequest, NameTokens.LANGUAGE_ENGLISH, englishAnchor);
    }

    private void setLanguageAnchor(PlaceRequest currentPlaceRequest, String language, AnchorElement anchorElement) {
        PlaceRequest newRequest = new PlaceRequest.Builder(currentPlaceRequest)
                .with(NameTokens.PARAM_LANGUAGE, language)
                .build();

        String href = formatter.toPlaceToken(newRequest);

        anchorElement.setHref("#" + href);
    }

    @Override
    public void changeBrand(Brand brand) {
        $("body").removeClass();
        $("body").addClass(getStyle(brand));
    }

    @Override
    public void updateItemNumber(int number) {
        $(numberOfItems).text(String.valueOf(number));
    }

    private String getStyle(Brand brand) {
        switch (brand) {
            case ARCBEES:
                return resources.style().arcbees();
            case GWTP:
                return resources.style().gwtp();
            case CHOSEN:
                return resources.style().chosen();
            case GQUERY:
                return resources.style().gquery();
            case GSSS:
                return resources.style().gsss();
            case GAE_STUDIO:
                return resources.style().gaestudio();
            case JUKITO:
                return resources.style().jukito();
            default:
                GQuery.console.log("Couldn't determine the selected brand, picking up Arcbees.");
                return resources.style().arcbees();
        }
    }
}
