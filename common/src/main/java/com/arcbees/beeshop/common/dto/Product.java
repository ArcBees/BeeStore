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

package com.arcbees.beeshop.common.dto;

public class Product {
    private ProductType productType;
    private Size size;

    private Product(ProductType productType, Size size) {
        this.productType = productType;
        this.size = size;
    }

    public static Product createShirtWithDefaultSize() {
        return new Product(ProductType.SHIRT, Size.getDefaultValue());
    }

    public static Product createShirt(Size size) {
        return new Product(ProductType.SHIRT, size);
    }

    public static Product createProduct(ProductType productType) {
        return new Product(productType, Size.UNIQUE);
    }

    public int getPrice() {
        return productType.getPrice();
    }

    public ProductType getProductType() {
        return productType;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
