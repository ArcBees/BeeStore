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

import static com.google.gwt.query.client.GQuery.$;

import javax.inject.Inject;

import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.client.events.BrandChangedEventHandler;
import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.web.bindery.event.shared.EventBus;

public class ThemeChangerImpl implements ThemeChanger, BrandChangedEventHandler {
    private final AppResources resources;

    @Inject
    public ThemeChangerImpl(
            EventBus eventBus,
            AppResources resources) {
        this.resources = resources;

        eventBus.addHandler(BrandChangedEvent.TYPE, this);
    }

    @Override
    public void changeBrand(Brand brand) {
        $("body").removeClass();
        $("body").addClass(getStyle(brand));
    }

    @Override
    public void onBrandChanged(BrandChangedEvent event) {
        changeBrand(event.getBrand());
    }

    private String getStyle(Brand brand) {
        switch (brand) {
            case ARCBEES:
                return resources.style().arcbees();
            case GWTP:
                return resources.style().gwtp();
            case CHOSEN:
                return resources.style().chosen();
            case GQUERY:
                return resources.style().gquery();
            case GSSS:
                return resources.style().gsss();
            case GAE_STUDIO:
                return resources.style().gaestudio();
            case JUKITO:
                return resources.style().jukito();
            default:
                throw new RuntimeException("Cannot select brand style");
        }
    }
}
