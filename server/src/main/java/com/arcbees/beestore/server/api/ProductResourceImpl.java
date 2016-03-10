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

import com.arcbees.beestore.common.api.ProductResource;
import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.Product;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;
import com.arcbees.beestore.common.dto.Size;

public class ProductResourceImpl implements ProductResource {
    @Override
    public ProductDto getProduct(int productId, String brandValue) {
        Product product = Product.createProduct(ProductType.createFromId(productId));
        Brand brand = Brand.createFromValue(brandValue);
        product.setSize(Size.UNIQUE);

        return new ProductDto(product, brand);
    }

    @Override
    public ProductDto getProduct(int productId, String brandValue, String sizeValue) {
        Product product = Product.createProduct(ProductType.createFromId(productId));
        Brand brand = Brand.createFromValue(brandValue);
        Size size = Size.createFromValue(sizeValue);

        if (product.getProductType() == ProductType.SHIRT) {
            product.setSize(size);
        }

        return new ProductDto(product, brand);
    }
}
