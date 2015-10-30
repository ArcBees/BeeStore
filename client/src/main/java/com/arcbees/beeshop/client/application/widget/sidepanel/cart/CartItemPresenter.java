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
import com.arcbees.beeshop.client.application.ShoppingCartItem;
import com.arcbees.beeshop.client.events.ShoppingCartQuantityChangeEvent;
import com.arcbees.beeshop.client.events.ShoppingCartQuantityUpdatedEventHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class CartItemPresenter extends PresenterWidget<CartItemPresenter.MyView>
        implements CartItemUiHandlers, ShoppingCartQuantityUpdatedEventHandler {
    interface MyView extends View, HasUiHandlers<CartItemUiHandlers> {
        void setShoppingCartItem(ShoppingCartItem item);

        void updateQuantity(int newQuantity);
    }

    private final CurrentOrder currentOrder;
    private final ShoppingCartItem item;

    @Inject
    CartItemPresenter(
            EventBus eventBus,
            MyView view,
            CurrentOrder currentOrder,
            @Assisted ShoppingCartItem item) {
        super(eventBus, view);

        this.currentOrder = currentOrder;
        this.item = item;

        getView().setUiHandlers(this);
    }

    @Override
    public void delete() {
        currentOrder.removeItem(item);

        this.removeFromParentSlot();
    }

    @Override
    public void onShoppingCartQuantityChanged(ShoppingCartQuantityChangeEvent event) {
        if (item == event.getExistingItem()) {
            getView().updateQuantity(event.getNewQuantity());
        }
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ShoppingCartQuantityChangeEvent.TYPE, this);
    }

    @Override
    protected void onReveal() {
        getView().setShoppingCartItem(item);
    }
}
