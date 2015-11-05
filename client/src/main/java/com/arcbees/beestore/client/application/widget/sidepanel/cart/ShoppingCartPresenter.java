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

package com.arcbees.beestore.client.application.widget.sidepanel.cart;

import com.arcbees.beestore.client.application.CurrentOrder;
import com.arcbees.beestore.client.application.widget.sidepanel.cart.cartitems.CartItemsPresenter;
import com.arcbees.beestore.client.events.CheckoutContinueEvent;
import com.arcbees.beestore.client.events.CloseShoppingCartEvent;
import com.arcbees.beestore.client.events.ShoppingCartChangedEvent;
import com.arcbees.beestore.client.events.ShoppingCartChangedEventHandler;
import com.arcbees.beestore.client.events.ShoppingCartQuantityChangeEvent;
import com.arcbees.beestore.client.events.ShoppingCartQuantityUpdatedEventHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;

public class ShoppingCartPresenter extends PresenterWidget<ShoppingCartPresenter.MyView>
        implements ShoppingCartUiHandlers, ShoppingCartChangedEventHandler, ShoppingCartQuantityUpdatedEventHandler {
    interface MyView extends View, HasUiHandlers<ShoppingCartUiHandlers> {
        void updateItemNumber(int number);

        void showEmptyCart();

        void showCheckout();
    }

    static final PermanentSlot<CartItemsPresenter> SLOT_CART_ITEM = new PermanentSlot<>();

    private final CurrentOrder currentOrder;
    private final CartItemsPresenter cartItemsPresenter;

    @Inject
    ShoppingCartPresenter(
            EventBus eventBus,
            MyView view,
            CurrentOrder currentOrder,
            CartItemsPresenter cartItemsPresenter) {
        super(eventBus, view);

        this.currentOrder = currentOrder;
        this.cartItemsPresenter = cartItemsPresenter;

        getView().setUiHandlers(this);
    }

    @Override
    public void onShoppingCartChanged(ShoppingCartChangedEvent event) {
        int orderSize = currentOrder.getSize();

        getView().updateItemNumber(orderSize);

        showCartContent();
    }

    @Override
    public void onShoppingCartQuantityChanged(ShoppingCartQuantityChangeEvent event) {
        getView().updateItemNumber(currentOrder.getSize());
    }

    private void showCartContent() {
        if (currentOrder.isEmpty()) {
            getView().showEmptyCart();
        } else {
            getView().showCheckout();
        }
    }

    @Override
    public void onCheckout() {
        CheckoutContinueEvent.fire(this);
    }

    @Override
    public void onClose() {
        CloseShoppingCartEvent.fire(this);
    }

    @Override
    protected void onBind() {
        setInSlot(SLOT_CART_ITEM, cartItemsPresenter);

        addRegisteredHandler(ShoppingCartChangedEvent.TYPE, this);
        addRegisteredHandler(ShoppingCartQuantityChangeEvent.TYPE, this);

        showCartContent();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }
}
