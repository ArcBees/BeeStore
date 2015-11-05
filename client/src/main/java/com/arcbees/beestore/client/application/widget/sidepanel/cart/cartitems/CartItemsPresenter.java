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

package com.arcbees.beestore.client.application.widget.sidepanel.cart.cartitems;

import java.util.List;

import com.arcbees.beestore.client.application.CurrentOrder;
import com.arcbees.beestore.client.application.SessionStorageHandler;
import com.arcbees.beestore.client.application.ShoppingCartItem;
import com.arcbees.beestore.client.application.widget.sidepanel.cart.cartitem.CartItemFactory;
import com.arcbees.beestore.client.application.widget.sidepanel.cart.cartitem.CartItemPresenter;
import com.arcbees.beestore.client.events.ShoppingCartChangedEvent;
import com.arcbees.beestore.client.events.ShoppingCartChangedEventHandler;
import com.arcbees.beestore.client.events.ShoppingCartQuantityChangeEvent;
import com.arcbees.beestore.client.events.ShoppingCartQuantityUpdatedEventHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.Slot;

public class CartItemsPresenter extends PresenterWidget<CartItemsPresenter.MyView>
        implements ShoppingCartChangedEventHandler, ShoppingCartQuantityUpdatedEventHandler {
    interface MyView extends View {
        void showAndSetSubTotal(float subTotal);

        void showEmpty();
    }

    static final Slot<CartItemPresenter> SLOT_ITEMS = new Slot<>();

    private final CartItemFactory cartItemFactory;
    private final CurrentOrder currentOrder;
    private final SessionStorageHandler sessionStorageHandler;
    private boolean itemsPopulated;

    @Inject
    CartItemsPresenter(
            EventBus eventBus,
            MyView view,
            CartItemFactory cartItemFactory,
            CurrentOrder currentOrder,
            SessionStorageHandler sessionStorageHandler) {
        super(eventBus, view);

        this.cartItemFactory = cartItemFactory;
        this.currentOrder = currentOrder;
        this.sessionStorageHandler = sessionStorageHandler;
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ShoppingCartChangedEvent.TYPE, this);
        addRegisteredHandler(ShoppingCartQuantityChangeEvent.TYPE, this);
    }

    @Override
    protected void onReset() {
        if (!itemsPopulated) {
            populateCartItemsFromSessionStorage();

            itemsPopulated = true;
        }
    }

    private void populateCartItemsFromSessionStorage() {
        List<ShoppingCartItem> items = sessionStorageHandler.getItems();
        for (ShoppingCartItem item : items) {
            currentOrder.addItem(item);
        }
    }

    @Override
    public void onShoppingCartChanged(ShoppingCartChangedEvent event) {
        if (!event.isRemoved()) {
            addToSlot(SLOT_ITEMS, cartItemFactory.create(event.getItem()));
        }

        if (currentOrder.isEmpty()) {
            getView().showEmpty();
        } else {
            getView().showAndSetSubTotal(currentOrder.calculateSubTotal());
        }
    }

    @Override
    public void onShoppingCartQuantityChanged(ShoppingCartQuantityChangeEvent event) {
        getView().showAndSetSubTotal(currentOrder.calculateSubTotal());
    }
}
