package com.arcbees.beeshop.client.application;

import com.arcbees.beeshop.common.dto.ProductDto;

public class ShoppingBagItem {
    private ProductDto productDto;
    private int quantity;

    public ShoppingBagItem(ProductDto productDto, int quantity) {
        this.productDto = productDto;
        this.quantity = quantity;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public int getQuantity() {
        return quantity;
    }
}
