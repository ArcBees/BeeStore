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

import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.client.resources.FontResources;
import com.arcbees.beeshop.common.NameTokens;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.i18n.client.LocaleInfo;
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
    SimplePanel sidePanelContainer;
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

    FontResources fontRes;
    AppResources res;

    private final TokenFormatter formatter;
    private final PlaceManager placeManager;

    private Boolean shoppingBagOpen;

    @Inject
    ApplicationView(
            Binder uiBinder,
            TokenFormatter formatter,
            PlaceManager placeManager,
            AppResources res,
            FontResources fontRes) {
        this.formatter = formatter;
        this.placeManager = placeManager;
        this.fontRes = fontRes;
        this.res = res;

        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(ApplicationPresenter.SLOT_MAIN, main);
        bindSlot(ApplicationPresenter.SLOT_SIDEPANEL, sidePanelContainer);

        bind();
    }

    @Override
    public void changeBrand(Brand brand) {
        $("body").removeClass();
        $("body").addClass(getStyle(brand));
    }

    @Override
    public void updateNavigationHref() {
        setI18nAnchors();
    }

    private void bind() {
        $(sidePanelContainer).hide();
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
                    $(sidePanelContainer).show();
                    $(cartIcon).attr("class", fontRes.icons().iconClose());
                    shoppingBagOpen = false;
                } else {
                    $(sidePanelContainer).hide();
                    $(cartIcon).attr("class", fontRes.icons().iconCart());
                    shoppingBagOpen = true;
                }
            }
        });

        setI18nAnchors();
    }

    private void setI18nAnchors() {
        if (isFrench()) {
            setAnchorHighlighted(frenchAnchor);
        } else {
            setAnchorHighlighted(englishAnchor);
        }

        setLanguageAnchor(NameTokens.LANGUAGE_FRENCH, frenchAnchor);
        setLanguageAnchor(NameTokens.LANGUAGE_ENGLISH, englishAnchor);
    }

    private void setAnchorHighlighted(AnchorElement languageAnchor) {
        $(languageAnchor).addClass(res.style().active());
    }

    private void setLanguageAnchor(String language, AnchorElement anchorElement) {
        anchorElement.setHref(buildPath(language));
    }

    private boolean isFrench() {
        LocaleInfo currentLocale = LocaleInfo.getCurrentLocale();
        return currentLocale.getLocaleName().equals(NameTokens.LANGUAGE_FRENCH);
    }

    private String buildPath(String language) {
        PlaceRequest currentPlaceRequest = placeManager.getCurrentPlaceRequest();

        String currentNameToken = currentPlaceRequest.getNameToken();

        PlaceRequest newRequest = new PlaceRequest.Builder(currentPlaceRequest)
                .nameToken(NameTokens.translate(currentNameToken))
                .build();

        String historyToken = placeManager.buildHistoryToken(newRequest);

        return "/" + language + "/#" + historyToken;
    }

    @Override
    public void updateItemNumber(int number) {
        $(numberOfItems).text(String.valueOf(number));
    }

    private String getStyle(Brand brand) {
        switch (brand) {
            case ARCBEES:
                return res.style().arcbees();
            case GWTP:
                return res.style().gwtp();
            case CHOSEN:
                return res.style().chosen();
            case GQUERY:
                return res.style().gquery();
            case GSSS:
                return res.style().gsss();
            case GAE_STUDIO:
                return res.style().gaestudio();
            case JUKITO:
                return res.style().jukito();
            default:
                GQuery.console.log("Couldn't determine the selected brand, picking up Arcbees.");
                return res.style().arcbees();
        }
    }
}
