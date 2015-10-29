/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.arcbees.beeshop.client.application.product;

import javax.inject.Inject;

import com.arcbees.beeshop.common.NameTokens;
import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.Product;
import com.arcbees.beeshop.common.dto.ProductType;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.arcbees.beeshop.common.dto.Size;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class CurrentProductImpl implements CurrentProduct {
    private final PlaceManager placeManager;

    @Inject
    CurrentProductImpl(
            PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Override
    public ProductDto get() {
        PlaceRequest currentPlaceRequest = placeManager.getCurrentPlaceRequest();

        String paramBrand = currentPlaceRequest.getParameter(NameTokens.PARAM_BRAND, "");
        Brand brand = Brand.createFromValue(paramBrand);

        String paramProductId = currentPlaceRequest.getParameter(NameTokens.PARAM_ID, "-1");
        ProductType productType = ProductType.createFromId(Integer.parseInt(paramProductId));

        if (productType == ProductType.SHIRT) {
            String paramSize = currentPlaceRequest.getParameter(NameTokens.PARAM_SIZE, "");
            Size size = Size.createFromValue(paramSize);

            return new ProductDto(Product.createShirt(size), brand);
        }

        return new ProductDto(Product.createProduct(productType), brand);
    }
}
