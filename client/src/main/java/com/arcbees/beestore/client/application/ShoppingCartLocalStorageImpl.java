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

import com.github.nmorel.gwtjackson.client.ObjectMapper;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.inject.Inject;
import com.seanchenxi.gwt.storage.client.StorageExt;
import com.seanchenxi.gwt.storage.client.StorageKey;
import com.seanchenxi.gwt.storage.client.StorageKeyFactory;

public class ShoppingCartLocalStorageImpl implements ShoppingCartLocalStorage {
    interface CartMapper extends ObjectMapper<List<ShoppingCartItem>> {
    }

    private static final StorageKey<String> STORAGE_KEY = StorageKeyFactory.stringKey("shopping-cart");

    private final StorageExt localStorage = StorageExt.getLocalStorage();
    private final CartMapper mapper;

    @Inject
    ShoppingCartLocalStorageImpl(
            CartMapper mapper) {
        this.mapper = mapper;
    }

    public List<ShoppingCartItem> getItems() {
        List<ShoppingCartItem> items = new ArrayList<>();
        try {
            String shoppingCartItems = localStorage.get(STORAGE_KEY);
            if (shoppingCartItems != null) {
                items = mapper.read(shoppingCartItems);
            }
        } catch (SerializationException e) {
            GQuery.console.error(e);
        }

        return items;
    }

    public void update(List<ShoppingCartItem> cartItems) {
        try {
            localStorage.put(STORAGE_KEY, mapper.write(cartItems));
        } catch (SerializationException e) {
            GQuery.console.error(e);
        }
    }
}
