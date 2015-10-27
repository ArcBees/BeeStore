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

import com.arcbees.beeshop.client.events.ShoppingBagChangedEvent;
import com.arcbees.beeshop.common.dto.ContactInfoDto;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class CurrentOrderImpl implements CurrentOrder, HasHandlers {
    private final EventBus eventBus;

    private List<ShoppingBagItem> items = new ArrayList<>();
    private ContactInfoDto contactInfo;

    @Inject
    CurrentOrderImpl(
            EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void addItem(ShoppingBagItem item) {
        items.add(item);

        ShoppingBagChangedEvent.fire(item, this);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public void removeItem(ShoppingBagItem item) {
        items.remove(item);

        ShoppingBagChangedEvent.fire(item, true, this);
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
    public void fireEvent(GwtEvent<?> gwtEvent) {
        eventBus.fireEventFromSource(gwtEvent, this);
    }
}
