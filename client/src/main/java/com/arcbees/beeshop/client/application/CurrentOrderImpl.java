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

package com.arcbees.beeshop.client.application;

import java.util.ArrayList;
import java.util.List;

import com.arcbees.beeshop.client.events.ShoppingCartChangedEvent;
import com.arcbees.beeshop.client.events.ShoppingCartQuantityChangeEvent;
import com.arcbees.beeshop.common.dto.ContactInfoDto;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class CurrentOrderImpl implements CurrentOrder, HasHandlers {
    private final EventBus eventBus;

    private List<ShoppingCartItem> items = new ArrayList<>();
    private ContactInfoDto contactInfo;

    @Inject
    CurrentOrderImpl(
            EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void addItem(final ShoppingCartItem item) {
        ShoppingCartItem existingItemOfSameType = Iterables.find(items, new Predicate<ShoppingCartItem>() {
            @Override
            public boolean apply(ShoppingCartItem shoppingCartItem) {
                return shoppingCartItem.equals(item);
            }
        }, null);

        if (existingItemOfSameType != null) {
            existingItemOfSameType.addMore(item.getQuantity());
            ShoppingCartQuantityChangeEvent.fire(this, existingItemOfSameType, existingItemOfSameType.getQuantity());
        } else {
            items.add(item);
            ShoppingCartChangedEvent.fire(item, this);
        }
    }

    @Override
    public int getSize() {
        int size = 0;

        for (ShoppingCartItem item : items) {
            size += item.getQuantity();
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public void removeItem(ShoppingCartItem item) {
        items.remove(item);

        ShoppingCartChangedEvent.fire(item, true, this);
    }

    @Override
    public ContactInfoDto getContactInfo() {
        return contactInfo;
    }

    @Override
    public void setContactInfo(ContactInfoDto contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public float calculateSubTotal() {
        float sum = 0;
        for (ShoppingCartItem item : items) {
            sum += item.getProductDto().getPrice() * item.getQuantity();
        }

        return sum;
    }

    @Override
    public void fireEvent(GwtEvent<?> gwtEvent) {
        eventBus.fireEventFromSource(gwtEvent, this);
    }

    protected List<ShoppingCartItem> getItems() {
        return items;
    }
}
