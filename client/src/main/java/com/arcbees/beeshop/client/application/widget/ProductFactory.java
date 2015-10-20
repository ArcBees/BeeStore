package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.common.dto.ProductDto;

public interface ProductFactory {
    MainProductPresenter create(ProductWidgetType productWidgetType, ProductDto productDto);
}
