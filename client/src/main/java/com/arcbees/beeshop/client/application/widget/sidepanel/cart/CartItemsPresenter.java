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

package com.arcbees.beeshop.client.application.widget.sidepanel.cart;

import com.arcbees.beeshop.client.application.CurrentOrder;
import com.arcbees.beeshop.client.events.ShoppingCartChangedEvent;
import com.arcbees.beeshop.client.events.ShoppingCartChangedEventHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.Slot;

public class CartItemsPresenter extends PresenterWidget<CartItemsPresenter.MyView>
        implements ShoppingCartChangedEventHandler {
    interface MyView extends View {
        void showAndSetSubTotal(float subTotal);

        void showEmpty();
    }

    static final Slot<CartItemPresenter> SLOT_ITEMS = new Slot<>();

    private final CartItemFactory cartItemFactory;
    private final CurrentOrder currentOrder;

    @Inject
    CartItemsPresenter(
            EventBus eventBus,
            MyView view,
            CartItemFactory cartItemFactory,
            CurrentOrder currentOrder) {
        super(eventBus, view);

        this.cartItemFactory = cartItemFactory;
        this.currentOrder = currentOrder;
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ShoppingCartChangedEvent.TYPE, this);
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
}
