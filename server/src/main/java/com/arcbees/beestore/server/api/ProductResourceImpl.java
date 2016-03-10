package com.arcbees.beestore.server.api;

import com.arcbees.beestore.common.api.ProductResource;
import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.Product;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;

public class ProductResourceImpl implements ProductResource {
    @Override
    public ProductDto getProduct(int productId, String brandValue) {
        Product product = Product.createProduct(ProductType.createFromId(productId));
        Brand brand = Brand.createFromValue(brandValue);

        return new ProductDto(product, brand);
    }
}
