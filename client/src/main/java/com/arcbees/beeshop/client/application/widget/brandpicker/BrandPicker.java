/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.arcbees.beeshop.client.application.widget.brandpicker;

import javax.inject.Inject;

import com.arcbees.beeshop.common.NameTokens;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class BrandPicker implements IsWidget, AttachEvent.Handler {
    interface Binder extends UiBinder<IsWidget, BrandPicker> {
    }

    protected IsWidget root;

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

    BrandPicker(
            PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Inject
    public BrandPicker(
            Binder binder,
            PlaceManager placeManager) {
        this.placeManager = placeManager;

        setRoot(binder.createAndBindUi(this));
    }

    protected void setRoot(IsWidget widget) {
        root = widget;

        asWidget().addAttachHandler(this);
    }

    @Override
    public Widget asWidget() {
        return root.asWidget();
    }

    @Override
    public void onAttachOrDetach(AttachEvent event) {
        if (event.isAttached()) {
            updateAnchors();
        }
    }

    public void updateAnchors() {
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

        anchor.setAttribute("data-brand", brand);
        anchor.setHref("#" + placeManager.buildHistoryToken(placeRequest));
    }

    public void setAddStyleNames(String styleName) {
        asWidget().addStyleName(styleName);
    }
}
