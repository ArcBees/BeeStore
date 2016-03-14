/*
 * Copyright 2016 ArcBees Inc.
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

package com.arcbees.beestore.common.dto;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductDtoTest {
    @Test
    public void getBrand_shouldReturnArbeesByDefault() throws Exception {
        ProductDto productDto = new ProductDto.Builder().build();

        assertTrue(Brand.ARCBEES.equals(productDto.getBrand()));
    }

    @Test
    public void getProductType_shouldReturnShirtByDefault() throws Exception {
        ProductDto productDto = new ProductDto.Builder().build();

        assertTrue(ProductType.SHIRT.equals(productDto.getProductType()));
    }

    @Test
    public void getSize_whenProductIsShirt_shouldBeMediumByDefault() throws Exception {
        ProductDto productDto = new ProductDto.Builder().build();

        assertTrue(Size.MEDIUM.equals(productDto.getSize()));
    }

    @Test
    public void getSize_whenProductIsNotShirt_shouldReturnUnique() throws Exception {
        ProductType notShirt = ProductType.MUG;
        ProductDto productDto = new ProductDto.Builder()
                .withProductType(notShirt)
                .build();

        assertTrue(Size.UNIQUE.equals(productDto.getSize()));
    }

    @Test
    public void getSize_whenProductIsShirt_cannotBeUnique() throws Exception {
        ProductDto productDto = new ProductDto.Builder()
                .withProductType(ProductType.SHIRT)
                .withShirtSize(Size.UNIQUE)
                .build();

        assertFalse(Size.UNIQUE.equals(productDto.getSize()));
    }

    @Test
    public void getSize_whenProductIsNotAShirt_canOnlyBeUnique() throws Exception {
        Size notUniqueSize = Size.MEDIUM;
        ProductDto productDto = new ProductDto.Builder()
                .withProductType(ProductType.BAG)
                .withShirtSize(notUniqueSize)
                .build();

        assertFalse(notUniqueSize.equals(productDto.getSize()));
    }

    @Test
    public void getBrand_shouldReturnAppropriateBrand() throws Exception {
        Brand expectedBrand = Brand.JUKITO;
        ProductDto productDto = new ProductDto.Builder()
                .withBrand(expectedBrand)
                .build();

        assertTrue(expectedBrand.equals(productDto.getBrand()));
    }
}