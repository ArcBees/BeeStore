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

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.common.NameTokens;
import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.Product;
import com.arcbees.beeshop.common.dto.ProductDto;
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
        Product product = Product.PHONE_CASE;
        Brand brand = Brand.GQUERY;
        PlaceRequest placeRequest = buildPlaceRequestForProduct(product.getId(), brand.getValue());
        given(placeManager.getCurrentPlaceRequest()).willReturn(placeRequest);

        ProductDto result = currentProduct.get();

        assertThat(result.getProduct()).isEqualTo(product);
        assertThat(result.getBrand()).isEqualTo(brand);
    }

    private PlaceRequest buildPlaceRequestForProduct(int productId, String brandValue) {
        return new PlaceRequest.Builder()
                    .with(NameTokens.PARAM_ID, String.valueOf(productId))
                    .with(NameTokens.PARAM_BRAND, brandValue)
                    .build();
    }
}
