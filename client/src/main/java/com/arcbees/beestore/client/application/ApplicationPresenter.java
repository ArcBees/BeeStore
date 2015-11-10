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

import java.util.List;

import javax.inject.Inject;

import com.arcbees.beestore.client.application.widget.sidepanel.SidePanelPresenter;
import com.arcbees.beestore.client.events.BrandChangedEvent;
import com.arcbees.beestore.client.events.BrandChangedEventHandler;
import com.arcbees.beestore.client.events.CloseShoppingCartEvent;
import com.arcbees.beestore.client.events.CloseShoppingCartEventHandler;
import com.arcbees.beestore.client.events.ShoppingCartChangedEvent;
import com.arcbees.beestore.client.events.ShoppingCartChangedEventHandler;
import com.arcbees.beestore.client.events.ShoppingCartQuantityChangeEvent;
import com.arcbees.beestore.client.events.ShoppingCartQuantityUpdatedEventHandler;
import com.arcbees.beestore.common.NameTokens;
import com.arcbees.beestore.common.dto.Brand;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.client.proxy.Proxy;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
        implements BrandChangedEventHandler, NavigationHandler, ShoppingCartChangedEventHandler,
        CloseShoppingCartEventHandler, ShoppingCartQuantityUpdatedEventHandler {
    @ProxyStandard
    @NoGatekeeper
    interface MyProxy extends Proxy<ApplicationPresenter> {
    }

    interface MyView extends View {
        void changeBrand(Brand brand);

        void updateItemNumber(int number);

        void updateNavigationHref();

        void closeShoppingCart();

        void setProductAnchorActive();

        void setHomeAnchorActive();
    }

    public static final NestedSlot SLOT_MAIN = new NestedSlot();
    public static final SingleSlot SLOT_SIDE_PANEL = new SingleSlot();

    private final SidePanelPresenter sidePanelPresenter;
    private final ShoppingCartLocalStorage shoppingCartLocalStorage;

    private CurrentOrder currentOrder;

    @Inject
    ApplicationPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            SidePanelPresenter sidePanelPresenter,
            CurrentOrder currentOrder,
            ShoppingCartLocalStorage shoppingCartLocalStorage) {
        super(eventBus, view, proxy, RevealType.Root);

        this.sidePanelPresenter = sidePanelPresenter;
        this.currentOrder = currentOrder;
        this.shoppingCartLocalStorage = shoppingCartLocalStorage;
    }

    @Override
    public void onCloseShoppingCart(CloseShoppingCartEvent event) {
        getView().closeShoppingCart();
    }

    @Override
    public void onShoppingCartQuantityChanged(ShoppingCartQuantityChangeEvent event) {
        getView().updateItemNumber(currentOrder.getSize());
    }

    @Override
    protected void onBind() {
        setInSlot(SLOT_SIDE_PANEL, sidePanelPresenter);

        addVisibleHandler(BrandChangedEvent.TYPE, this);

        addVisibleHandler(ShoppingCartChangedEvent.TYPE, this);

        addVisibleHandler(CloseShoppingCartEvent.TYPE, this);

        addVisibleHandler(ShoppingCartQuantityChangeEvent.TYPE, this);

        addRegisteredHandler(NavigationEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        populateCurrentOrderFromSession();
        updateItemNumberTooltip();
    }

    private void populateCurrentOrderFromSession() {
        List<ShoppingCartItem> items = shoppingCartLocalStorage.getItems();
        for (ShoppingCartItem item : items) {
            currentOrder.addItem(item);
        }
    }

    private void updateItemNumberTooltip() {
        getView().updateItemNumber(currentOrder.getSize());
    }

    @ProxyEvent
    @Override
    public void onBrandChanged(BrandChangedEvent event) {
        getView().changeBrand(event.getBrand());
    }

    @Override
    public void onShoppingCartChanged(ShoppingCartChangedEvent event) {
        updateItemNumberTooltip();
    }

    @ProxyEvent
    @Override
    public void onNavigation(NavigationEvent navigationEvent) {
        String newNameToken = navigationEvent.getRequest().getNameToken();

        if (NameTokens.matchesAnyLanguageOfNameToken(newNameToken, NameTokens.HOME)) {
            getView().setHomeAnchorActive();
        } else if (NameTokens.matchesAnyLanguageOfNameToken(newNameToken, NameTokens.PRODUCT)) {
            getView().setProductAnchorActive();
        }

        getView().updateNavigationHref();
    }
}
