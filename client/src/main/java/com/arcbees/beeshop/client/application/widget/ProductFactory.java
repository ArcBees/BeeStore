package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.common.dto.ProductDto;

public interface ProductFactory {
    SecondaryProductPresenter create(ProductDto productDto);

    MainProductPresenter create(Side side, ProductDto productDto);
}
