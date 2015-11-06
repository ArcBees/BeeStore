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

package com.arcbees.beestore.client.application;

import java.io.Serializable;

import com.arcbees.beestore.common.dto.ProductDto;

public class ShoppingCartItem implements Serializable {
    private ProductDto productDto;
    private int quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(ProductDto productDto, int quantity) {
        this.productDto = productDto;
        this.quantity = quantity;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCartItem that = (ShoppingCartItem) o;

        return !(productDto != null ? !productDto.equals(that.productDto) : that.productDto != null);
    }

    @Override
    public int hashCode() {
        return productDto != null ? productDto.hashCode() : 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addMore(int quantity) {
        this.quantity += quantity;
    }

    public String getIdentifier() {
        return productDto.getBrand().getValue() + "" + productDto.getProductType().name();
    }
}
