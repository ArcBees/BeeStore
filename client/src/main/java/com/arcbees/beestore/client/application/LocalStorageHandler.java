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

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.inject.Inject;
import com.seanchenxi.gwt.storage.client.StorageExt;
import com.seanchenxi.gwt.storage.client.StorageKey;

public class LocalStorageHandler {
    private final LocalStorageKeyProvider keyProvider;
    private final StorageExt localStorage = StorageExt.getLocalStorage();

    @Inject
    LocalStorageHandler(
            LocalStorageKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    public List<ShoppingCartItem> getItems() {
        List<ShoppingCartItem> items = new ArrayList<>();
        try {
            ShoppingCartItem[] shoppingCartItems = localStorage.get(getStorageKey());
            if (shoppingCartItems != null) {
                items = Lists.newArrayList(shoppingCartItems);
            }
        } catch (SerializationException e) {
            GQuery.console.error(e);
        }

        return items;
    }

    public void update(List<ShoppingCartItem> cartItems) {
        ShoppingCartItem[] items = cartItems.toArray(new ShoppingCartItem[cartItems.size()]);

        try {
            localStorage.put(getStorageKey(), items);
        } catch (SerializationException e) {
            GQuery.console.error(e);
        }
    }

    private StorageKey<ShoppingCartItem[]> getStorageKey() {
        return keyProvider.shoppingCartItemsKey("SHOPPING_CART");
    }
}
