package com.arcbees.beeshop.client.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.common.dto.Brand;

@RunWith(JukitoRunner.class)
public class CurrentBrandImplTest {
    @Inject
    CurrentBrandImpl currentBrand;
    @Inject
    ThemeChanger themeChanger;

    @Test
    public void getCurrentBrand_returnsCurrentBrand() {
        Brand brand = Brand.ARCBEES;
        currentBrand.update(brand);

        Brand result = currentBrand.get();

        assertThat(result).isEqualTo(brand);
    }

    @Test
    public void ctor_defaultBrand_isSet() {
        assertThat(currentBrand.get()).isEqualTo(Brand.getDefaultValue());
    }

    @Test
    public void updateBrand_doesNotChangeThemeWhenBrandIsTheSame() {
        Brand brand = Brand.getDefaultValue();

        currentBrand.update(brand);

        verify(themeChanger, never()).changeBrand(any(Brand.class));
    }

    @Test
    public void updateBrand_changesThemeWhenBrandChanges() {
        Brand brand = Brand.CHOSEN;

        currentBrand.update(brand);

        verify(themeChanger).changeBrand(brand);
    }
}
