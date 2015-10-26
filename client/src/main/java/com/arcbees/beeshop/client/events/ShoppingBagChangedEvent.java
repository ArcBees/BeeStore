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

package com.arcbees.beeshop.client.events;

import com.arcbees.beeshop.client.application.ShoppingBagItem;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ShoppingBagChangedEvent extends GwtEvent<ShoppingBagChangedEventHandler> {
    public static final Type<ShoppingBagChangedEventHandler> TYPE = new Type<>();
    private final ShoppingBagItem item;
    private boolean removed;

    public ShoppingBagChangedEvent(ShoppingBagItem item, Boolean removed) {
        this.item = item;
        this.removed = removed;
    }

    public static void fire(ShoppingBagItem item, HasHandlers source) {
        source.fireEvent(new ShoppingBagChangedEvent(item, false));
    }

    public static void fire(ShoppingBagItem item, Boolean removed, HasHandlers source) {
        source.fireEvent(new ShoppingBagChangedEvent(item, removed));
    }

    public ShoppingBagItem getItem() {
        return item;
    }

    public boolean isRemoved() {
        return removed;
    }

    @Override
    public Type<ShoppingBagChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShoppingBagChangedEventHandler handler) {
        handler.onShoppingBagChanged(this);
    }
}
