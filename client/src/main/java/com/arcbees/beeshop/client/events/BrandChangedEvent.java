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

import com.arcbees.beeshop.common.dto.Brand;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class BrandChangedEvent extends GwtEvent<BrandChangedEventHandler> {
    public static Type<BrandChangedEventHandler> TYPE = new Type<>();
    private final Brand brand;

    public BrandChangedEvent(Brand brand) {
        this.brand = brand;
    }

    // Needed for the proxyEvent on ApplicationPresenter
    public static Type<BrandChangedEventHandler> getType() {
        return TYPE;
    }

    public static void fire(Brand brand, HasHandlers source) {
        source.fireEvent(new BrandChangedEvent(brand));
    }

    public Brand getBrand() {
        return brand;
    }

    @Override
    public Type<BrandChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(BrandChangedEventHandler handler) {
        handler.onBrandChanged(this);
    }
}
