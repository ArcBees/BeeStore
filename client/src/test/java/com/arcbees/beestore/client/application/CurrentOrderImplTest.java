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

package com.arcbees.beestore.client.application;

import java.util.List;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.arcbees.beestore.client.events.ShoppingCartQuantityChangeEvent;
import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.Product;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;
import com.google.web.bindery.event.shared.EventBus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class CurrentOrderImplTest {
    @Inject
    private CurrentOrderImpl currentOrder;
    @Inject
    private EventBus eventBus;

    @Test
    public void sumWith1Item1Quantity() {
        currentOrder.addItem(createItem(ProductType.SHIRT, 1));

        float result = currentOrder.calculateSubTotal();

        assertThat(result).isEqualTo(30f);
    }

    @Test
    public void sumWith2Items1Quantity() {
        currentOrder.addItem(createItem(ProductType.SHIRT, 1));
        currentOrder.addItem(createItem(ProductType.MUG, 1));

        float result = currentOrder.calculateSubTotal();

        assertThat(result).isEqualTo(40f);
    }

    @Test
    public void sumWithSame2Items1Quantity() {
        currentOrder.addItem(createItem(ProductType.SHIRT, 1));
        currentOrder.addItem(createItem(ProductType.SHIRT, 1));

        float result = currentOrder.calculateSubTotal();

        assertThat(result).isEqualTo(60f);
    }

    @Test
    public void sumWith1Item2Quantity() {
        currentOrder.addItem(createItem(ProductType.SHIRT, 2));

        float result = currentOrder.calculateSubTotal();

        assertThat(result).isEqualTo(60f);
    }

    private ShoppingCartItem createItem(ProductType productType, int quantity) {
        ProductDto productDto = new ProductDto(Product.createProduct(productType), Brand.getDefaultValue());
        return new ShoppingCartItem(productDto, quantity);
    }

    @Test
    public void addingTheSameKindOfItemWithTheSameBrand_mergesItWithTheOriginalOrderItem() {
        ShoppingCartItem firstItem = createItem(ProductType.PHONE_CASE, 2);
        ShoppingCartItem secondItem = createItem(ProductType.PHONE_CASE, 4);
        currentOrder.addItem(firstItem);
        currentOrder.addItem(secondItem);

        List<ShoppingCartItem> orderItems = currentOrder.getItems();

        assertThat(orderItems.size()).isEqualTo(1);
        assertThat(orderItems.get(0).getQuantity()).isEqualTo(6);
    }

    @Test
    public void addingTheSameKindOfItemWithTheSameBrand_firesCartQuantityUpdatedEvent() {
        ShoppingCartItem firstItem = createItem(ProductType.PHONE_CASE, 2);
        ShoppingCartItem secondItem = createItem(ProductType.PHONE_CASE, 4);
        currentOrder.addItem(firstItem);
        Mockito.reset(eventBus);

        currentOrder.addItem(secondItem);

        ArgumentCaptor<ShoppingCartQuantityChangeEvent> captor =
                ArgumentCaptor.forClass(ShoppingCartQuantityChangeEvent.class);
        verify(eventBus).fireEventFromSource(captor.capture(), same(currentOrder));
        assertThat(captor.getValue().getExistingItem()).isEqualTo(secondItem);
        assertThat(captor.getValue().getNewQuantity()).isEqualTo(6);
    }

    @Test
    public void getSize_withManyOfTheSameItem_countsForMany() {
        currentOrder.addItem(createItem(ProductType.SHIRT, 3));
        currentOrder.addItem(createItem(ProductType.BAG, 5));

        int result = currentOrder.getSize();

        assertThat(result).isEqualTo(8);
    }
}
