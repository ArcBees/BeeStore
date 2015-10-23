package com.arcbees.beeshop.client.application.gin;

import javax.inject.Inject;

import com.arcbees.beeshop.common.NameTokens;
import com.google.common.base.Strings;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.History;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class BootstrapperImpl implements Bootstrapper {
    private final PlaceManager placeManager;

    @Inject
    public BootstrapperImpl(
            PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Override
    public void onBootstrap() {
        PlaceRequest currentPlaceRequest = placeManager.getCurrentPlaceRequest();
        String nameToken = Strings.nullToEmpty(History.getToken());
        String currentLocale = LocaleInfo.getCurrentLocale().getLocaleName();

        GQuery.console.log("Locale: " + currentLocale);

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
