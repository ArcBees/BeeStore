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

package com.arcbees.beestore.client.application.widget.sidepanel.checkout;

import javax.inject.Inject;

import com.arcbees.beestore.client.application.CurrencyFormat;
import com.arcbees.beestore.client.application.CurrentOrder;
import com.arcbees.beestore.client.application.ShippingMethod;
import com.arcbees.beestore.client.application.widget.sidepanel.cart.cartitems.CartItemsPresenter;
import com.arcbees.beestore.client.events.CheckoutContinueEvent;
import com.arcbees.beestore.client.events.CheckoutEvent;
import com.arcbees.beestore.client.events.CheckoutEventHandler;
import com.arcbees.beestore.client.events.EmptyCartEvent;
import com.arcbees.beestore.client.events.EmptyCartEventHandler;
import com.arcbees.beestore.client.events.ShoppingCartChangedEvent;
import com.arcbees.beestore.client.events.ShoppingCartChangedEventHandler;
import com.arcbees.beestore.client.events.ShoppingCartQuantityChangeEvent;
import com.arcbees.beestore.client.events.ShoppingCartQuantityUpdatedEventHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;

public class OrderPresenter extends PresenterWidget<OrderPresenter.MyView>
        implements OrderUiHandlers, ShoppingCartChangedEventHandler, ShoppingCartQuantityUpdatedEventHandler,
        CheckoutEventHandler, EmptyCartEventHandler {
    interface MyView extends View, HasUiHandlers<OrderUiHandlers> {
        void hideCheckoutButton();

        void setTaxes(String taxes);

        void setOrderTotal(String grandTotal);

        void setStandardPrice(String price);

        void setInternationalPrice(String price);
    }

    public static final SingleSlot<CartItemsPresenter> SLOT_CART_ITEMS = new SingleSlot<>();

    private final CartItemsPresenter cartItemsPresenter;
    private final CurrencyFormat currencyFormat;

    private CurrentOrder currentOrder;

    @Inject
    OrderPresenter(
            EventBus eventBus,
            MyView view,
            CartItemsPresenter cartItemsPresenter,
            CurrentOrder currentOrder,
            CurrencyFormat currencyFormat) {
        super(eventBus, view);

        this.currentOrder = currentOrder;
        this.cartItemsPresenter = cartItemsPresenter;
        this.currencyFormat = currencyFormat;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ShoppingCartChangedEvent.TYPE, this);
        addRegisteredHandler(ShoppingCartQuantityChangeEvent.TYPE, this);
        addRegisteredHandler(CheckoutEvent.TYPE, this);
        addRegisteredHandler(EmptyCartEvent.TYPE, this);

        setShippingPrices();
        setOrderTotal();
    }

    private void setShippingPrices() {
        float standardPrice = ShippingMethod.STANDARD.getPrice();
        float internationalPrice = ShippingMethod.INTERNATIONAL.getPrice();

        getView().setStandardPrice(currencyFormat.format(standardPrice));
        getView().setInternationalPrice(currencyFormat.format(internationalPrice));
    }

    @Override
    public void onEmptyCart(EmptyCartEvent event) {
        removeFromSlot(SLOT_CART_ITEMS, cartItemsPresenter);
    }

    @Override
    public void onCheckout(CheckoutEvent event) {
        if (event.isCheckoutOpen()) {
            setInSlot(SLOT_CART_ITEMS, cartItemsPresenter);
        }
    }

    @Override
    public void onContinueClicked() {
        CheckoutContinueEvent.fire(this);
    }

    @Override
    public void setShippingMethod(ShippingMethod shippingMethod) {
        currentOrder.setShippingMethod(shippingMethod);

        setOrderTotal();
    }

    @Override
    public void onShoppingCartChanged(ShoppingCartChangedEvent event) {
        setOrderTotal();
    }

    @Override
    public void onShoppingCartQuantityChanged(ShoppingCartQuantityChangeEvent event) {
        setOrderTotal();
    }

    private void setOrderTotal() {
        float grandTotal = currentOrder.calculateGrandTotal();
        float taxes = currentOrder.calculateTaxes();

        getView().setOrderTotal(formatCurrency(grandTotal));
        getView().setTaxes(formatCurrency(taxes));
    }

    private String formatCurrency(float total) {
        return currencyFormat.format(total);
    }
}
