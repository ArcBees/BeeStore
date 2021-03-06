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

package com.arcbees.beestore.client.application.gin;

import javax.inject.Inject;

import com.arcbees.beestore.client.application.LocaleHelper;
import com.arcbees.beestore.common.NameTokens;
import com.google.gwt.user.client.History;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

public class BootstrapperImpl implements Bootstrapper {
    private final PlaceManager placeManager;
    private final LocaleHelper localeHelper;
    private final TokenFormatter tokenFormatter;

    @Inject
    BootstrapperImpl(
            PlaceManager placeManager,
            LocaleHelper localeHelper,
            TokenFormatter tokenFormatter) {
        this.placeManager = placeManager;
        this.localeHelper = localeHelper;
        this.tokenFormatter = tokenFormatter;
    }

    @Override
    public void onBootstrap() {
        String historyToken = History.getToken();
        PlaceRequest currentPlaceRequest =
                tokenFormatter.toPlaceRequest(historyToken.isEmpty() ? NameTokens.HOME : historyToken);
        String nameToken = currentPlaceRequest.getNameToken();

        if (nameTokenInWrongLanguage(nameToken)) {
            revealTranslatedNameToken(currentPlaceRequest);
        } else {
            placeManager.revealCurrentPlace();
        }
    }

    private boolean nameTokenInWrongLanguage(String nameToken) {
        return localeHelper.isEnglish() && !NameTokens.isEnglish(nameToken)
                || localeHelper.isFrench() && NameTokens.isEnglish(nameToken);
    }

    private void revealTranslatedNameToken(PlaceRequest currentPlaceRequest) {
        PlaceRequest placeRequest = new PlaceRequest.Builder(currentPlaceRequest)
                .nameToken(NameTokens.translate(currentPlaceRequest.getNameToken()))
                .build();

        placeManager.revealPlace(placeRequest);
    }
}
