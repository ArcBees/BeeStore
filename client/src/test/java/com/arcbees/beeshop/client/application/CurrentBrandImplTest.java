package com.arcbees.beeshop.client.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

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
        currentBrand.update(brand);

        Brand result = currentBrand.get();

        assertThat(result).isEqualTo(brand);
    }

    @Test
    public void updateBrand_firesEventWhenBrandUpdates() {
        Brand brand = Brand.CHOSEN;

        currentBrand.update(brand);

        ArgumentCaptor<BrandChangedEvent> captor = ArgumentCaptor.forClass(BrandChangedEvent.class);
        verify(eventBus).fireEventFromSource(captor.capture(), same(currentBrand));
        assertThat(captor.getValue().getBrand()).isEqualTo(brand);
    }

    @Test
    public void updateBrand_doesNotFireEventWhenBrandIsSame() {
        Brand brand = Brand.CHOSEN;
        currentBrand.update(brand);
        Mockito.reset(eventBus);

        currentBrand.update(brand);

        verify(eventBus, never()).fireEventFromSource(isA(BrandChangedEvent.class), same(currentBrand));
    }
}
