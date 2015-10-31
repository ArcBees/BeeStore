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

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.client.application.CurrentOrder;
import com.arcbees.beeshop.client.events.CloseShoppingCartEvent;
import com.arcbees.beeshop.client.events.ShoppingCartChangedEvent;
import com.arcbees.beeshop.client.events.ShoppingCartQuantityChangeEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.AutobindDisable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class ShoppingCartPresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            bind(AutobindDisable.class).toInstance(new AutobindDisable(true));
        }
    }

    private static final java.lang.Integer ORDER_SIZE = 456;

    @Inject
    private ShoppingCartPresenter presenter;
    @Inject
    private CurrentOrder currentOrder;
    @Inject
    private ShoppingCartPresenter.MyView view;
    @Inject
    private EventBus eventBus;

    @Test
    public void onShoppingCartChanged_showEmptyCart_whenOrderIsEmpty() {
        given(currentOrder.isEmpty()).willReturn(true);

        presenter.onShoppingCartChanged(mock(ShoppingCartChangedEvent.class));

        verify(view).showEmptyCart();
    }

    @Test
    public void onShoppingCartChanged_showCheckout_whenOrderIsNotEmpty() {
        given(currentOrder.isEmpty()).willReturn(false);

        presenter.onShoppingCartChanged(mock(ShoppingCartChangedEvent.class));

        verify(view).showCheckout();
    }

    @Test
    public void onClose_fireCloseShoppingCartEvent() {
        presenter.onClose();

        verify(eventBus).fireEventFromSource(isA(CloseShoppingCartEvent.class), same(presenter));
    }

    @Test
    public void onBind_showCheckout_whenOrderIsNotEmpty() {
        given(currentOrder.isEmpty()).willReturn(false);

        presenter.onBind();

        verify(view).showCheckout();
    }

    @Test
    public void onBind_showEmptyCart_whenOrderIsEmpty() {
        given(currentOrder.isEmpty()).willReturn(false);

        presenter.onBind();

        view.showEmptyCart();
    }

    @Test
    public void onCartQuantityChange_updateCartTooltip() {
        given(currentOrder.getSize()).willReturn(ORDER_SIZE);

        presenter.onShoppingCartQuantityChanged(mock(ShoppingCartQuantityChangeEvent.class));

        verify(view).updateItemNumber(ORDER_SIZE);
    }
}
