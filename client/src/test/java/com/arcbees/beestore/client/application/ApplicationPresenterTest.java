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

package com.arcbees.beestore.client.application;

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beestore.client.application.widget.sidepanel.SidePanelPresenter;
import com.arcbees.beestore.client.events.BrandChangedEvent;
import com.arcbees.beestore.client.events.CloseShoppingCartEvent;
import com.arcbees.beestore.client.events.ShoppingCartQuantityChangeEvent;
import com.arcbees.beestore.common.NameTokens;
import com.arcbees.beestore.common.dto.Brand;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class ApplicationPresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            forceMock(SidePanelPresenter.class);
        }
    }

    private static final int ORDER_SIZE = 423;

    @Inject
    private ApplicationPresenter presenter;
    @Inject
    private ApplicationPresenter.MyView view;
    @Inject
    private CurrentOrder currentOrder;

    @Test
    public void onBrandChanged_changeBrandInView() {
        Brand brand = Brand.JUKITO;
        BrandChangedEvent event = new BrandChangedEvent(brand);

        presenter.onBrandChanged(event);

        verify(view).changeBrand(brand);
    }

    @Test
    public void onNavigation_updateLanguageSwitchAnchors() {
        PlaceRequest request = new PlaceRequest.Builder().nameToken(NameTokens.PRODUCT).build();
        NavigationEvent event = new NavigationEvent(request);

        presenter.onNavigation(event);

        verify(view).updateNavigationHref();
    }

    @Test
    public void onCloseShoppingCart_closeShoppingCart() {
        presenter.onCloseShoppingCart(mock(CloseShoppingCartEvent.class));

        verify(view).closeShoppingCart();
    }

    @Test
    public void onCartQuantityChanged_updateQuantityTooltip() {
        given(currentOrder.getSize()).willReturn(ORDER_SIZE);

        presenter.onShoppingCartQuantityChanged(mock(ShoppingCartQuantityChangeEvent.class));

        verify(view).updateNumberOfItems(ORDER_SIZE);
    }

    @Test
    public void onNavigation_setActiveTab_whenNameTokenMatchesFrench() {
        PlaceRequest request = new PlaceRequest.Builder().nameToken(NameTokens.PRODUCT).build();
        NavigationEvent event = new NavigationEvent(request);

        presenter.onNavigation(event);

        verify(view).setProductAnchorActive();
    }

    @Test
    public void onNavigation_setActiveTab_whenNameTokenMatchesEnglish() {
        PlaceRequest request = new PlaceRequest.Builder().nameToken(NameTokens.PRODUCT_FR).build();
        NavigationEvent event = new NavigationEvent(request);

        presenter.onNavigation(event);

        verify(view).setProductAnchorActive();
    }
}
