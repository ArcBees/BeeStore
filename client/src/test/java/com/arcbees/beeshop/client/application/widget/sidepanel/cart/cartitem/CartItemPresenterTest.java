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

package com.arcbees.beeshop.client.application.widget.sidepanel.cart.cartitem;

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.client.application.CurrentOrder;
import com.arcbees.beeshop.client.application.ShoppingCartItem;
import com.arcbees.beeshop.client.events.ShoppingCartQuantityChangeEvent;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class CartItemPresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            install(new FactoryModuleBuilder().build(CartItemFactory.class));
        }
    }

    final static int SOME_QUANTITY = 333;
    final static int NEW_QUANTITY = 414;

    @Inject
    private CartItemFactory cartItemFactory;
    @Inject
    private CartItemPresenter.MyView view;
    @Inject
    private CurrentOrder currentOrder;

    private CartItemPresenter presenter;
    private ShoppingCartItem currentShoppingCartItem;

    @Before
    public void setUp() {
        currentShoppingCartItem = new ShoppingCartItem(new ProductDto(), SOME_QUANTITY);

        presenter = cartItemFactory.create(currentShoppingCartItem);
    }

    @Test
    public void onQuantityChangedInView_deletesItem_whenQuantityIs0() {
        presenter.onQuantityChangedInView(0);

        verify(currentOrder).removeItem(currentShoppingCartItem);
    }

    @Test
    public void onQuantityChangedInView_updatesQuantityInItem_whenQuantityIsOver0() {
        currentShoppingCartItem.setQuantity(4);

        presenter.onQuantityChangedInView(2);

        assertThat(currentShoppingCartItem.getQuantity()).isEqualTo(2);
    }

    @Test
    public void onShoppingCartQuantityUpdated_updatesViewWithNewQuantity_whenCartItemIsSameAsCurrentOne() {
        ShoppingCartQuantityChangeEvent updateEvent =
                new ShoppingCartQuantityChangeEvent(currentShoppingCartItem, NEW_QUANTITY);

        presenter.onShoppingCartQuantityChanged(updateEvent);

        verify(view).updateQuantity(NEW_QUANTITY);
    }

    @Test
    public void onShoppingCartQuantityUpdated_doesNothing_whenItemIsNotTheSameAsCurrentOne() {
        ShoppingCartItem otherItem = mock(ShoppingCartItem.class);
        ShoppingCartQuantityChangeEvent updateEvent =
                new ShoppingCartQuantityChangeEvent(otherItem, SOME_QUANTITY);

        presenter.onShoppingCartQuantityChanged(updateEvent);

        verify(view, never()).updateQuantity(anyInt());
    }
}
