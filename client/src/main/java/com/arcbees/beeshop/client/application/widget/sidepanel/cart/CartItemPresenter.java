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
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class CartItemPresenter extends PresenterWidget<CartItemPresenter.MyView>
        implements CartItemUiHandlers {
    interface MyView extends View, HasUiHandlers<CartItemUiHandlers> {
        void setShoppingCartItem(ShoppingCartItem item);
    }

    private ShoppingCartItem item;
    private CurrentOrder currentOrder;

    @Inject
    CartItemPresenter(
            EventBus eventBus,
            MyView view,
            @Assisted ShoppingCartItem item,
            CurrentOrder currentOrder) {
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
    protected void onReveal() {
        getView().setShoppingCartItem(item);
    }
}