package com.arcbees.beeshop.client.application.widget;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import com.arcbees.beeshop.client.application.CurrentBrand;
import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.gwtplatform.mvp.client.AutobindDisable;

@RunWith(JukitoRunner.class)
public class MainProductPresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            install(new FactoryModuleBuilder().build(ProductFactory.class));

            bind(AutobindDisable.class).toInstance(new AutobindDisable(true));
        }
    }

    public static final Brand BRAND = Brand.CHOSEN;

    @Inject
    private ProductFactory productFactory;
    @Inject
    private MainProductPresenter.MyView view;
    @Inject
    private CurrentBrand currentBrand;
    private Side side;
    private MainProductPresenter presenter;

    @Before
    public void setUp() {
        ProductDto dto = new ProductDto();
        side = Side.LEFT;

        presenter = productFactory.create(side, dto);
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

        verify(view).setStyle(side);
    }

    @Test
    public void onReveal_styleViewBasedOnBrand() {
        given(currentBrand.get()).willReturn(BRAND);

        presenter.onReveal();

        Brand actualBrand = getActualBrandPassedToView();
        assertThat(actualBrand).isEqualTo(BRAND);
    }
}
