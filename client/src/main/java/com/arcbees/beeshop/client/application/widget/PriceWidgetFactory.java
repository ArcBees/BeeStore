package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.common.dto.ProductDto;

public interface PriceWidgetFactory {
    PricePresenter create(ProductDto product);
}
