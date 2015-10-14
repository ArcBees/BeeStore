package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import com.arcbees.beeshop.common.dto.Brand;

public class CurrentBrandImpl implements CurrentBrand {
    private final ThemeChanger themeChanger;

    private Brand brand;

    @Inject
    public CurrentBrandImpl(
            ThemeChanger themeChanger) {
        this.themeChanger = themeChanger;

        brand = Brand.getDefaultValue();
    }

    @Override
    public void update(Brand brand) {
        if (this.brand == brand) {
            return;
        }

        this.brand = brand;

        themeChanger.changeBrand(brand);
    }

    @Override
    public Brand get() {
        return brand;
    }
}
