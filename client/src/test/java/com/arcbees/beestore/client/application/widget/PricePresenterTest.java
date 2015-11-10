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

import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beestore.common.dto.ProductDto;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.gwtplatform.mvp.client.AutobindDisable;

@RunWith(JukitoRunner.class)
public class PricePresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            install(new FactoryModuleBuilder().build(PriceWidgetFactory.class));

            bind(AutobindDisable.class).toInstance(new AutobindDisable(true));
        }
    }

    @Inject
    private PricePresenter.MyView view;
    @Inject
    private PriceWidgetFactory factory;

    private PricePresenter presenter;
    private ProductDto product;

    @Before
    public void setUp() throws Exception {
        product = new ProductDto();
        presenter = factory.create(product);
    }

    @Test
    public void onBind_setsProductInformationIntoView() {
        presenter.onBind();

        verify(view).setProduct(product);
    }
}
