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

import com.arcbees.beestore.client.application.CurrencyFormat;
import com.arcbees.beestore.client.application.CurrentOrder;
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
        void showAndSetSubTotal(String subTotal);

        void showEmpty();
    }

    static final Slot<CartItemPresenter> SLOT_ITEMS = new Slot<>();

    private final CartItemFactory cartItemFactory;
    private final CurrentOrder currentOrder;
    private final CurrencyFormat currencyFormat;

    @Inject
    CartItemsPresenter(
            EventBus eventBus,
            MyView view,
            CartItemFactory cartItemFactory,
            CurrentOrder currentOrder,
            CurrencyFormat currencyFormat) {
        super(eventBus, view);

        this.cartItemFactory = cartItemFactory;
        this.currentOrder = currentOrder;
        this.currencyFormat = currencyFormat;
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ShoppingCartChangedEvent.TYPE, this);
        addRegisteredHandler(ShoppingCartQuantityChangeEvent.TYPE, this);
    }

    @Override
    public void onShoppingCartChanged(ShoppingCartChangedEvent event) {
        if (!event.isRemoved()) {
            addToSlot(SLOT_ITEMS, cartItemFactory.create(event.getItem()));
        }

        if (currentOrder.isEmpty()) {
            getView().showEmpty();
        } else {
            float subtotal = currentOrder.calculateSubTotal();
            getView().showAndSetSubTotal(currencyFormat.format(subtotal));
        }
    }

    @Override
    public void onShoppingCartQuantityChanged(ShoppingCartQuantityChangeEvent event) {
        float subTotal = currentOrder.calculateSubTotal();

        getView().showAndSetSubTotal(currencyFormat.format(subTotal));
    }
}
