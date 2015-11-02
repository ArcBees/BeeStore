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

package com.arcbees.beestore.client.events;

import com.arcbees.beestore.client.application.ShoppingCartItem;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ShoppingCartQuantityChangeEvent extends GwtEvent<ShoppingCartQuantityUpdatedEventHandler> {
    public static Type<ShoppingCartQuantityUpdatedEventHandler> TYPE = new Type<>();

    private final int newQuantity;
    private final ShoppingCartItem existingItem;

    public ShoppingCartQuantityChangeEvent(ShoppingCartItem existingItem, int newQuantity) {
        this.existingItem = existingItem;
        this.newQuantity = newQuantity;
    }

    public static void fire(HasHandlers source, ShoppingCartItem existingItem, int newQuantity) {
        source.fireEvent(new ShoppingCartQuantityChangeEvent(existingItem, newQuantity));
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    public ShoppingCartItem getExistingItem() {
        return existingItem;
    }

    @Override
    public Type<ShoppingCartQuantityUpdatedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShoppingCartQuantityUpdatedEventHandler handler) {
        handler.onShoppingCartQuantityChanged(this);
    }
}
