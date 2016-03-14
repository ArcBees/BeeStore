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

package com.arcbees.beestore.server.api;

import java.util.ArrayList;
import java.util.List;

import com.arcbees.beestore.common.api.ProductResource;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;

public class ProductResourceImpl implements ProductResource {
    @Override
    public ProductDto getProduct(int productId, String brandValue, String size) {
        return new ProductDto.Builder()
                .withProductTypeId(productId)
                .withBrandValue(brandValue)
                .withShirtSizeValue(size)
                .build();
    }

    @Override
    public List<ProductDto> getProductsByBrand(String brand) {
        List<ProductDto> productDtos = new ArrayList<>();

        for (ProductType productType : ProductType.values()) {
            ProductDto productDto = new ProductDto.Builder()
                    .withProductType(productType)
                    .withBrandValue(brand)
                    .build();

            productDtos.add(productDto);
        }

        return productDtos;
    }
}
