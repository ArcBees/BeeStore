package com.arcbees.beeshop.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface ComponentsResources extends ClientBundle {
    interface Style extends CssResource {

        String btn();

        String btn_outline();

        String btn_small();

        String btn_large();

        String btn_wide();

        String btn_primary();

        String btn_secondary();

        String btn_content();

        String btn_alert();

        String btn_disabled();

        String btns_group();

        String btns_group__round();
    }

    @Source({"css/colors.gss", "css/btns.gss"})
    Style style();

}
