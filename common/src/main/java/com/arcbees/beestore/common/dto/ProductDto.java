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

package com.arcbees.beestore.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {
    private Brand brand;
    private ProductType productType;
    private Size size;

    private ProductDto() {
    }

    @JsonCreator
    public ProductDto(
            @JsonProperty("productType") ProductType productType,
            @JsonProperty("brand") Brand brand,
            @JsonProperty("size") Size size) {

        this.productType = productType;
        this.brand = brand;
        this.size = size;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Size getSize() {
        return size;
    }

    public ProductType getProductType() {
        return productType;
    }

    @JsonIgnore
    public int getPrice() {
        return productType.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDto that = (ProductDto) o;

        if (brand != that.brand) return false;
        if (productType != that.productType) return false;
        return size == that.size;

    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + productType.hashCode();
        result = 31 * result + size.hashCode();
        return result;
    }

    public static class Builder {
        private ProductType productType;
        private Brand brand;
        private Size size;

        public Builder() {
        }

        public Builder withProductTypeId(int id) {
            productType = ProductType.createFromId(id);

            return this;
        }

        public Builder withBrandValue(String value) {
            brand = Brand.createFromValue(value);

            return this;
        }

        public Builder withShirtSizeValue(String value) {
            size = Size.createFromValue(value);

            return this;
        }

        public Builder withShirtSize(Size size) {
            this.size = size;

            return this;
        }

        public Builder withProductType(ProductType productType) {
            this.productType = productType;

            return this;
        }

        public Builder withBrand(Brand brand) {
            this.brand = brand;

            return this;
        }

        public ProductDto build() {
            if (productType == null) {
                productType = ProductType.getDefaultValue();
            }

            if (brand == null) {
                brand = Brand.getDefaultValue();
            }

            if (!productType.equals(ProductType.SHIRT)) {
                size = Size.getDefaultValue();
            } else if (size == null || size == Size.UNIQUE) {
                size = Size.getDefaultShirtValue();
            }

            return new ProductDto(productType, brand, size);
        }
    }
}
