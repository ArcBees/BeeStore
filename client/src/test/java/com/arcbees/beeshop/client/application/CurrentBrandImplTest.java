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

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

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

        verifyZeroInteractions(eventBus);
    }
}
