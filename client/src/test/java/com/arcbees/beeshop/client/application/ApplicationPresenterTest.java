package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.common.dto.Brand;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class ApplicationPresenterTest {
    @Inject
    private ApplicationPresenter presenter;
    @Inject
    private ApplicationPresenter.MyView view;

    @Test
    public void onBrandChanged_changeBrandInView() {
        Brand brand = Brand.JUKITO;
        BrandChangedEvent event = new BrandChangedEvent(brand);

        presenter.onBrandChanged(event);

        verify(view).changeBrand(brand);
    }

    @Test
    public void onNavigation_updateLanguageSwitchAnchors() {
        NavigationEvent event = new NavigationEvent(mock(PlaceRequest.class));

        presenter.onNavigation(event);

        verify(view).updateNavigationHref();
    }
}
