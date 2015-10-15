package com.arcbees.beeshop.client.resources;

import com.arcbees.beeshop.common.dto.Brand;
import com.google.gwt.i18n.client.Messages;

public interface AppMessages extends Messages {
    String brandName(@Select Brand brand);

    String shirt();

    String bag();

    String cup();

    String mug();

    String key();

    String phoneCase();
}
