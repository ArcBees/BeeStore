package com.arcbees.beeshop.client.application;

import com.arcbees.beeshop.common.dto.Brand;

public interface CurrentBrand {
    void update();

    Brand get();
}
