package com.arcbees.beeshop.client.application.home;

import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;

@RunWith(JukitoRunner.class)
public class HomePresenterTest {
    @Inject
    private HomePresenter presenter;
    @Inject
    private HomePresenter.MyView view;

    @Test
    public void onBrandChange_changeProductsBrand() {
        Brand brand = Brand.CHOSEN;
        BrandChangedEvent event = new BrandChangedEvent(brand);

        presenter.onBrandChanged(event);

        verify(view).changeBrand(brand);
    }
}
