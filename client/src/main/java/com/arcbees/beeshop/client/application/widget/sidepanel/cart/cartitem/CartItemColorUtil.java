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

import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.common.dto.Brand;

public class CartItemColorUtil {
    private final AppResources appResources;

    @Inject
    public CartItemColorUtil(AppResources appResources) {
        this.appResources = appResources;
    }

    String getShirtClass(Brand brand) {
        switch (brand) {
            case ARCBEES:
                return appResources.style().shirtArcbees();
            case GQUERY:
                return appResources.style().shirtGQuery();
            case GAE_STUDIO:
                return appResources.style().shirtGaeStudio();
            case GWTP:
                return appResources.style().shirtGWTP();
            case JUKITO:
                return appResources.style().shirtJukito();
            case GSSS:
                return appResources.style().shirtGSSS();
            case CHOSEN:
                return appResources.style().shirtChosen();
            default:
                return appResources.style().shirtArcbees();
        }
    }
}
