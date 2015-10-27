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
import com.arcbees.beeshop.client.events.ShoppingBagChangedEvent;
import com.arcbees.beeshop.client.events.ShoppingBagChangedEventHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.Slot;

public class ShoppingBagPresenter extends PresenterWidget<ShoppingBagPresenter.MyView>
        implements ShoppingBagUiHandlers, ShoppingBagChangedEventHandler {
    interface MyView extends View, HasUiHandlers<ShoppingBagUiHandlers> {
        void updateItemNumber(int Number);
    }

    static final Slot<ShoppingBagItemPresenter> SLOT_BAG_ITEM = new Slot<>();

    private ShoppingBagItemFactory shoppingBagItemFactory;
    private CurrentOrder currentOrder;

    @Inject
    ShoppingBagPresenter(
            EventBus eventBus,
            MyView view,
            ShoppingBagItemFactory shoppingBagItemFactory,
            CurrentOrder currentOrder) {
        super(eventBus, view);

        this.shoppingBagItemFactory = shoppingBagItemFactory;
        this.currentOrder = currentOrder;

        getView().setUiHandlers(this);
    }

    @Override
    public void onShoppingBagChanged(ShoppingBagChangedEvent event) {
        getView().updateItemNumber(currentOrder.getSize());

        if (!event.isRemoved()) {
            addToSlot(SLOT_BAG_ITEM, shoppingBagItemFactory.create(event.getItem()));
        }
    }

    @Override
    public void checkout() {
        CheckoutContinueEvent.fire(this);
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ShoppingBagChangedEvent.TYPE, this);
    }
}
