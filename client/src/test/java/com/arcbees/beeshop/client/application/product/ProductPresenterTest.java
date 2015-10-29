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

package com.arcbees.beeshop.client.application.product;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beeshop.common.dto.ProductDto;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class ProductPresenterTest {
    @Inject
    private ProductPresenter presenter;
    @Inject
    private ProductPresenter.MyView view;
    @Inject
    private CurrentProduct currentProduct;

    @Test
    public void onReveal_hidesSharePanel() {
        presenter.onReveal();

        verify(view).hideSharePanel();
        verify(view, never()).showSharePanel();
    }

    @Test
    public void onShareButtonClicked_givenSharePanelIsHidden_showsSharePanel() {
        // given
        presenter.onReveal();
        reset(view);

        // when
        presenter.onShareButtonClicked();

        // then
        verify(view).showSharePanel();
        verify(view, never()).hideSharePanel();
    }

    @Test
    public void onShareButtonClicked_givenSharePanelIsShown_hidesSharePanel() {
        // given
        presenter.onReveal();
        presenter.onShareButtonClicked();
        reset(view);

        // when
        presenter.onShareButtonClicked();

        // then
        verify(view).hideSharePanel();
        verify(view, never()).showSharePanel();
    }

    @Test
    public void givenPageWasRevealedBeforeAndShareButtonIsClicked_showsSharePanel() {
        // given
        presenter.onShareButtonClicked();
        presenter.onReveal();
        reset(view);

        // when
        presenter.onShareButtonClicked();

        // then
        verify(view).showSharePanel();
        verify(view, never()).hideSharePanel();
    }

    @Test
    public void prepareFromRequest_setsProduct() {
        PlaceRequest request = new PlaceRequest();
        ProductDto productDto = new ProductDto();
        given(currentProduct.get()).willReturn(productDto);

        presenter.prepareFromRequest(request);

        verify(view).setProduct(productDto);
    }
}
