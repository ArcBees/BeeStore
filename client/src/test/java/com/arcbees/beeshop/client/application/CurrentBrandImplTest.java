package com.arcbees.beeshop.client.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import com.arcbees.beeshop.client.NameTokens;
import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

@RunWith(JukitoRunner.class)
public class CurrentBrandImplTest {
    @Inject
    CurrentBrandImpl currentBrand;
    @Inject
    ThemeChanger themeChanger;
    @Inject
    EventBus eventBus;
    @Inject
    PlaceManager placeManager;

    @Test
    public void getCurrentBrand_returnsCurrentBrand() {
        Brand brand = Brand.CHOSEN;
        stubPlaceRequestWithBrandParameter(brand);

        Brand result = currentBrand.get();

        assertThat(result).isEqualTo(brand);
    }

    private void stubPlaceRequestWithBrandParameter(Brand brand) {
        PlaceRequest placeRequest = new PlaceRequest.Builder()
                .with(NameTokens.PARAM_BRAND, brand.getValue())
                .build();
        given(placeManager.getCurrentPlaceRequest()).willReturn(placeRequest);
    }

    @Test
    public void ctor_defaultBrand_isSet() {
        PlaceRequest placeRequest = new PlaceRequest();
        given(placeManager.getCurrentPlaceRequest()).willReturn(placeRequest);

        assertThat(currentBrand.get()).isEqualTo(Brand.getDefaultValue());
    }

    @Test
    public void updateBrand_firesEventWhenBrandUpdates() {
        Brand brand = Brand.CHOSEN;
        stubPlaceRequestWithBrandParameter(brand);

        currentBrand.update();

        ArgumentCaptor<BrandChangedEvent> captor = ArgumentCaptor.forClass(BrandChangedEvent.class);
        verify(eventBus).fireEventFromSource(captor.capture(), same(currentBrand));
        assertThat(captor.getValue().getBrand()).isEqualTo(brand);
    }
}
