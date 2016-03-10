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

package com.arcbees.beestore.client.application.product;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import com.arcbees.beestore.client.application.CurrentOrder;
import com.arcbees.beestore.client.application.ShoppingCartItem;
import com.arcbees.beestore.common.api.ProductResource;
import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.Product;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;
import com.arcbees.beestore.common.dto.Size;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import static com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils.givenDelegate;

@RunWith(JukitoRunner.class)
public class ProductPresenterTest {
    @Inject
    private ProductPresenter presenter;
    @Inject
    private ProductPresenter.MyView view;
    @Inject
    private CurrentProduct currentProduct;
    @Inject
    private CurrentOrder currentOrder;
    @Inject
    private ResourceDelegate<ProductResource> productDelegate;

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
    public void onPrepareRequest_setsProduct() {
        Product expectedProduct = Product.createProduct(ProductType.BAG);
        ProductDto productDto = new ProductDto(expectedProduct, Brand.JUKITO);

        String brandValue = productDto.getBrand().getValue();
        String productId = String.valueOf(productDto.getProduct().getProductType().getId());

        PlaceRequest request = new PlaceRequest.Builder()
                .with("brand", brandValue)
                .with("id", productId)
                .build();

        given(currentProduct.get()).willReturn(productDto);
        givenDelegate(productDelegate).useResource(ProductResource.class)
                .and().succeed().withResult(productDto)
                .when().getProduct(Integer.parseInt(productId), brandValue);

        presenter.prepareFromRequest(request);

        verify(view).setProduct(productDto);
    }

    @Test
    public void onAddToCartButtonClicked_addItemToCurrentOrder() {
        int itemQuantity = 32;
        ProductDto productToAdd = new ProductDto();
        given(currentProduct.get()).willReturn(productToAdd);

        presenter.onAddToCartButtonClicked(itemQuantity);

        ArgumentCaptor<ShoppingCartItem> captor = ArgumentCaptor.forClass(ShoppingCartItem.class);
        verify(currentOrder).addItem(captor.capture());

        ShoppingCartItem addedItem = captor.getValue();
        assertThat(addedItem.getProductDto()).isEqualTo(productToAdd);
        assertThat(addedItem.getQuantity()).isEqualTo(itemQuantity);
    }
}
