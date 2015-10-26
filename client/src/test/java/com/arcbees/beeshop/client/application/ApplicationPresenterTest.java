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

package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class ApplicationPresenterTest {
    @Inject
    private ApplicationPresenter presenter;
    @Inject
    private ApplicationPresenter.MyView view;

    @Test
    public void onBrandChanged_changeBrandInView() {
        Brand brand = Brand.JUKITO;
        BrandChangedEvent event = new BrandChangedEvent(brand);

        presenter.onBrandChanged(event);

        verify(view).changeBrand(brand);
    }

    @Test
    public void onNavigation_updateLanguageSwitchAnchors() {
        NavigationEvent event = new NavigationEvent(mock(PlaceRequest.class));

        presenter.onNavigation(event);

        verify(view).updateNavigationHref();
    }
}
