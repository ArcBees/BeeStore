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

package com.arcbees.beestore.client.application.widget.sidepanel.cart.cartitem;

import javax.inject.Inject;

import com.arcbees.beestore.client.resources.AppResources;
import com.arcbees.beestore.common.dto.Brand;

public class CartItemColorUtil {
    private final AppResources appResources;

    @Inject
    public CartItemColorUtil(AppResources appResources) {
        this.appResources = appResources;
    }

    String getShirtClass(Brand brand) {
        switch (brand) {
            case ARCBEES:
                return appResources.style().orderBox__arcbeesShirt();
            case GQUERY:
                return appResources.style().orderBox__gQueryShirt();
            case GAE_STUDIO:
                return appResources.style().orderBox__gaeStudioShirt();
            case GWTP:
                return appResources.style().orderBox__gwtpShirt();
            case JUKITO:
                return appResources.style().orderBox__jukitoShirt();
            case GSSS:
                return appResources.style().orderBox__gsssShirt();
            case CHOSEN:
                return appResources.style().orderBox__chosenShirt();
            default:
                return appResources.style().orderBox__arcbeesShirt();
        }
    }
}
