/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.beestore.client.application.widget;

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import com.arcbees.beestore.client.application.CurrentBrand;
import com.arcbees.beestore.client.events.BrandChangedEvent;
import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.ProductDto;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.gwtplatform.mvp.client.AutobindDisable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class ProductPresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            install(new FactoryModuleBuilder().build(ProductWidgetFactory.class));

            bind(AutobindDisable.class).toInstance(new AutobindDisable(true));
        }
    }

    public static final Brand BRAND = Brand.CHOSEN;

    @Inject
    private ProductWidgetFactory productWidgetFactory;
    @Inject
    private ProductPresenter.MyView view;
    @Inject
    private CurrentBrand currentBrand;
    private ProductWidgetType productWidgetType;
    private ProductPresenter presenter;

    @Before
    public void setUp() {
        ProductDto dto = new ProductDto.Builder().build();
        productWidgetType = ProductWidgetType.MAIN_LEFT;

        presenter = productWidgetFactory.create(productWidgetType, dto);
    }

    @Test
    public void onBrandChanged_updatesBrandInView() {
        BrandChangedEvent event = new BrandChangedEvent(BRAND);

        presenter.onBrandChanged(event);

        Brand actualBrand = getActualBrandPassedToView();
        assertThat(actualBrand).isEqualTo(BRAND);
    }

    private Brand getActualBrandPassedToView() {
        ArgumentCaptor<ProductDto> captor = ArgumentCaptor.forClass(ProductDto.class);
        verify(view).setProduct(captor.capture());
        return captor.getValue().getBrand();
    }

    @Test
    public void onBind_putsCorrectStyleInView() {
        presenter.onBind();

        verify(view).setStyle(productWidgetType);
    }

    @Test
    public void onReveal_styleViewBasedOnBrand() {
        given(currentBrand.get()).willReturn(BRAND);

        presenter.onReveal();

        Brand actualBrand = getActualBrandPassedToView();
        assertThat(actualBrand).isEqualTo(BRAND);
    }
}
