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

package com.arcbees.beeshop.client.application.gin;

import javax.inject.Inject;

import com.arcbees.beeshop.common.NameTokens;
import com.google.common.base.Strings;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.History;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class BootstrapperImpl implements Bootstrapper {
    private final PlaceManager placeManager;

    @Inject
    BootstrapperImpl(
            PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Override
    public void onBootstrap() {
        PlaceRequest currentPlaceRequest = placeManager.getCurrentPlaceRequest();
        String nameToken = Strings.nullToEmpty(History.getToken());
        String currentLocale = LocaleInfo.getCurrentLocale().getLocaleName();

        if ("en".compareToIgnoreCase(currentLocale) == 0) {
            if (!Strings.isNullOrEmpty(nameToken) && !NameTokens.isEnglish(nameToken)) {
                revealTranslatedNameToken(currentPlaceRequest, nameToken);
                return;
            } else if (Strings.isNullOrEmpty(nameToken)) {
                revealTranslatedNameToken(currentPlaceRequest, NameTokens.HOME);
            }
        } else if ("fr".compareToIgnoreCase(currentLocale) == 0) {
            if (NameTokens.isEnglish(nameToken)) {
                revealTranslatedNameToken(currentPlaceRequest, nameToken);
                return;
            } else if (Strings.isNullOrEmpty(nameToken)) {
                revealTranslatedNameToken(currentPlaceRequest, NameTokens.HOME);
            }
        }

        placeManager.revealCurrentPlace();
    }

    private void revealTranslatedNameToken(PlaceRequest currentPlaceRequest, String nameToken) {
        PlaceRequest placeRequest = new PlaceRequest.Builder(currentPlaceRequest)
                .nameToken(NameTokens.translate(nameToken))
                .build();
        placeManager.revealPlace(placeRequest);
    }
}
