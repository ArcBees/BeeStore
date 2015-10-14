package com.arcbees.beeshop.client.application;

import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.client.NameTokens;
import com.arcbees.beeshop.common.dto.Brand;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

@RunWith(JukitoRunner.class)
public class BrandChangeHandlerTest {
    @Inject
    private BrandChangeHandler handler;
    @Inject
    private CurrentBrand currentBrand;

    @Test
    public void onNavigation_whenBrandChanges_updatesCurrentBrand() {
        String brand = Brand.ARCBEES.getValue();
        PlaceRequest request = new PlaceRequest.Builder()
                .with(NameTokens.PARAM_BRAND, brand)
                .build();
        NavigationEvent navigationEvent = new NavigationEvent(request);

        handler.onNavigation(navigationEvent);

        verify(currentBrand).update(Brand.ARCBEES);
    }

    @Test
    public void onNavigation_whenBrandChanges_updatesCurrentBrand2() {
        String brand = Brand.CHOSEN.getValue();
        PlaceRequest request = new PlaceRequest.Builder()
                .with(NameTokens.PARAM_BRAND, brand)
                .build();
        NavigationEvent navigationEvent = new NavigationEvent(request);

        handler.onNavigation(navigationEvent);

        verify(currentBrand).update(Brand.CHOSEN);
    }
}
