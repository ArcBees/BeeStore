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

import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.common.NameTokens;
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
        Brand brand = Brand.ARCBEES;
        PlaceRequest request = new PlaceRequest.Builder()
                .with(NameTokens.PARAM_BRAND, brand.getValue())
                .build();
        NavigationEvent navigationEvent = new NavigationEvent(request);

        handler.onNavigation(navigationEvent);

        verify(currentBrand).update(brand);
    }

    @Test
    public void onNavigation_whenBrandChanges_updatesCurrentBrand2() {
        Brand brand = Brand.CHOSEN;
        PlaceRequest request = new PlaceRequest.Builder()
                .with(NameTokens.PARAM_BRAND, brand.getValue())
                .build();
        NavigationEvent navigationEvent = new NavigationEvent(request);

        handler.onNavigation(navigationEvent);

        verify(currentBrand).update(brand);
    }
}
