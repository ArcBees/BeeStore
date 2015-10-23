package com.arcbees.beeshop.client.resources;

import com.arcbees.beeshop.common.NameTokens;
import com.google.gwt.i18n.client.Constants;

public interface NameTokensConstants extends Constants {
    @DefaultStringValue(NameTokens.HOME)
    String HOME();

    @DefaultStringValue(NameTokens.PRODUCTS)
    String PRODUCTS();
}
