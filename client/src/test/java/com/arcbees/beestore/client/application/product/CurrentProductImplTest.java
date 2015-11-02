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

package com.arcbees.beestore.client.application.product;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beestore.common.NameTokens;
import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;
import com.arcbees.beestore.common.dto.Size;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(JukitoRunner.class)
public class CurrentProductImplTest {
    @Inject
    private PlaceManager placeManager;
    @Inject
    private CurrentProductImpl currentProduct;

    @Test
    public void get_willReturnCustomizedProduct() {
        ProductType productType = ProductType.PHONE_CASE;
        Brand brand = Brand.GQUERY;
        PlaceRequest placeRequest = buildPlaceRequestForProduct(productType.getId(), brand.getValue());
        given(placeManager.getCurrentPlaceRequest()).willReturn(placeRequest);

        ProductDto result = currentProduct.get();

        assertThat(result.getProductType()).isEqualTo(productType);
        assertThat(result.getBrand()).isEqualTo(brand);
    }

    @Test
    public void get_willReturnCustomizedShirt() {
        ProductType productType = ProductType.SHIRT;
        Brand brand = Brand.GAE_STUDIO;
        Size size = Size.SMALL;
        PlaceRequest placeRequest = buildPlaceRequestForShirt(brand, size);
        given(placeManager.getCurrentPlaceRequest()).willReturn(placeRequest);

        ProductDto result = currentProduct.get();

        assertThat(result.getProductType()).isEqualTo(productType);
        assertThat(result.getBrand()).isEqualTo(brand);
        assertThat(result.getProduct().getSize()).isEqualTo(size);
    }

    private PlaceRequest buildPlaceRequestForShirt(Brand brand, Size size) {
        return new PlaceRequest.Builder()
                .with(NameTokens.PARAM_ID, String.valueOf(ProductType.SHIRT.getId()))
                .with(NameTokens.PARAM_BRAND, brand.getValue())
                .with(NameTokens.PARAM_SIZE, size.getValue())
                .build();
    }

    private PlaceRequest buildPlaceRequestForProduct(int productId, String brandValue) {
        return new PlaceRequest.Builder()
                .with(NameTokens.PARAM_ID, String.valueOf(productId))
                .with(NameTokens.PARAM_BRAND, brandValue)
                .build();
    }
}
