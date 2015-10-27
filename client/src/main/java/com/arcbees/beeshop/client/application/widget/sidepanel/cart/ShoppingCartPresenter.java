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
import com.arcbees.beeshop.client.events.CheckoutContinueEvent;
import com.arcbees.beeshop.client.events.ShoppingCartChangedEvent;
import com.arcbees.beeshop.client.events.ShoppingCartChangedEventHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.Slot;

public class ShoppingCartPresenter extends PresenterWidget<ShoppingCartPresenter.MyView>
        implements ShoppingCartUiHandlers, ShoppingCartChangedEventHandler {
    interface MyView extends View, HasUiHandlers<ShoppingCartUiHandlers> {
        void updateItemNumber(int Number);
    }

    static final Slot<ShoppingCartItemPresenter> SLOT_CART_ITEM = new Slot<>();

    private ShoppingCartItemFactory shoppingCartItemFactory;
    private CurrentOrder currentOrder;

    @Inject
    ShoppingCartPresenter(
            EventBus eventBus,
            MyView view,
            ShoppingCartItemFactory shoppingCartItemFactory,
            CurrentOrder currentOrder) {
        super(eventBus, view);

        this.shoppingCartItemFactory = shoppingCartItemFactory;
        this.currentOrder = currentOrder;

        getView().setUiHandlers(this);
    }

    @Override
    public void onShoppingCartChanged(ShoppingCartChangedEvent event) {
        getView().updateItemNumber(currentOrder.getSize());

        if (!event.isRemoved()) {
            addToSlot(SLOT_CART_ITEM, shoppingCartItemFactory.create(event.getItem()));
        }
    }

    @Override
    public void checkout() {
        CheckoutContinueEvent.fire(this);
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ShoppingCartChangedEvent.TYPE, this);
    }
}
