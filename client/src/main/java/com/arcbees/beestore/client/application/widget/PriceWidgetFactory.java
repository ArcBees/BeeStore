package com.arcbees.beestore.client.application.widget;

import com.arcbees.beestore.common.dto.ProductDto;

public interface PriceWidgetFactory {
    PricePresenter create(ProductDto product);
}
