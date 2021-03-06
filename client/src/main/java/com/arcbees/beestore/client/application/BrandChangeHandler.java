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

package com.arcbees.beestore.client.application;

import javax.inject.Inject;

import com.arcbees.beestore.common.NameTokens;
import com.arcbees.beestore.common.dto.Brand;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;

public class BrandChangeHandler implements NavigationHandler {
    private final CurrentBrand currentBrand;

    @Inject
    public BrandChangeHandler(
            EventBus eventBus,
            CurrentBrand currentBrand) {
        this.currentBrand = currentBrand;

        eventBus.addHandler(NavigationEvent.getType(), this);
    }

    @Override
    public void onNavigation(NavigationEvent navigationEvent) {
        String brandName = navigationEvent.getRequest().getParameter(NameTokens.PARAM_BRAND, "");

        Brand brandValue = Brand.createFromValue(brandName);

        currentBrand.update(brandValue);
    }
}
