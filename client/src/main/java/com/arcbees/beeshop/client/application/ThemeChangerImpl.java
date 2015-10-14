package com.arcbees.beeshop.client.application;

import static com.google.gwt.query.client.GQuery.$;

import javax.inject.Inject;

import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.common.dto.Brand;

public class ThemeChangerImpl implements ThemeChanger {
    private final AppResources resources;

    @Inject
    public ThemeChangerImpl(
            AppResources resources) {
        this.resources = resources;
    }

    @Override
    public void changeBrand(Brand brand) {
        $("body").removeClass();
        $("body").addClass(getStyle(brand));
    }

    private String getStyle(Brand brand) {
        switch (brand) {
            case ARCBEES:
                return resources.style().arcbees();
            case GWTP:
                return resources.style().gwtp();
            case CHOSEN:
                return resources.style().chosen();
            case GQUERY:
                return resources.style().gquery();
            case GSSS:
                return resources.style().gsss();
            case GAE_STUDIO:
                return resources.style().gaestudio();
            case JUKITO:
                return resources.style().jukito();
            default:
                throw new RuntimeException("Cannot select brand style");
        }
    }
}
