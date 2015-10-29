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

package com.arcbees.beeshop.client.application.widget.sidepanel.checkout;

import javax.inject.Inject;

import com.arcbees.beeshop.client.application.widget.sidepanel.cart.CartItemsPresenter;
import com.arcbees.beeshop.client.events.CheckoutContinueEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;

public class OrderPresenter extends PresenterWidget<OrderPresenter.MyView>
        implements OrderUiHandlers {
    interface MyView extends View, HasUiHandlers<OrderUiHandlers> {
    }

    public static final PermanentSlot<CartItemsPresenter> SLOT_CART_ITEMS = new PermanentSlot<>();
    private final CartItemsPresenter cartItemsPresenter;

    @Inject
    OrderPresenter(
            EventBus eventBus,
            MyView view,
            CartItemsPresenter cartItemsPresenter) {
        super(eventBus, view);

        this.cartItemsPresenter = cartItemsPresenter;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        setInSlot(SLOT_CART_ITEMS, cartItemsPresenter);
    }

    @Override
    public void onContinueClicked() {
        CheckoutContinueEvent.fire(this);
    }
}
