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
