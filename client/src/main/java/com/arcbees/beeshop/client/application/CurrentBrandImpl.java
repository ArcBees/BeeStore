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

import javax.inject.Inject;

import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class CurrentBrandImpl implements CurrentBrand, HasHandlers {
    private final EventBus eventBus;

    private Brand brand;
    private final PlaceManager placeManager;

    @Inject
    public CurrentBrandImpl(
            EventBus eventBus,
            PlaceManager placeManager) {
        this.eventBus = eventBus;
        this.placeManager = placeManager;
    }

    @Override
    public void update(Brand brand) {
        if (this.brand == brand) {
            return;
        }

        this.brand = brand;
        BrandChangedEvent.fire(this.brand, this);
    }

    @Override
    public Brand get() {
        return brand;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEventFromSource(event, this);
    }
}
