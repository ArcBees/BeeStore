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

public class ShoppingCartChangedEvent extends GwtEvent<ShoppingCartChangedEventHandler> {
    public static final Type<ShoppingCartChangedEventHandler> TYPE = new Type<>();
    private final ShoppingCartItem item;
    private boolean removed;

    public ShoppingCartChangedEvent(ShoppingCartItem item, Boolean removed) {
        this.item = item;
        this.removed = removed;
    }

    public static void fire(ShoppingCartItem item, HasHandlers source) {
        source.fireEvent(new ShoppingCartChangedEvent(item, false));
    }

    public static void fire(ShoppingCartItem item, Boolean removed, HasHandlers source) {
        source.fireEvent(new ShoppingCartChangedEvent(item, removed));
    }

    public ShoppingCartItem getItem() {
        return item;
    }

    public boolean isRemoved() {
        return removed;
    }

    @Override
    public Type<ShoppingCartChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShoppingCartChangedEventHandler handler) {
        handler.onShoppingCartChanged(this);
    }
}
