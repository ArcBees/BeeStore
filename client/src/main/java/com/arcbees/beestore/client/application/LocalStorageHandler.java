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

import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.inject.Inject;
import com.seanchenxi.gwt.storage.client.StorageExt;
import com.seanchenxi.gwt.storage.client.StorageKey;
import com.seanchenxi.gwt.storage.client.StorageKeyFactory;

public class LocalStorageHandler {
    private final LocalStorageKeyProvider keyProvider;
    private final StorageExt localStorage = StorageExt.getLocalStorage();

    @Inject
    LocalStorageHandler(
            LocalStorageKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    public void addToSessionStorage(ShoppingCartItem item) {
        try {
            localStorage.put(keyProvider.shoppingCartItemKey(item.getStorageKeyName()), item);
        } catch (SerializationException e) {
            GQuery.console.error(e);
        }
    }

    public void deleteFromSessionStorage(ShoppingCartItem item) {
        String keyName = item.getStorageKeyName();
        StorageKey<ShoppingCartItem> key = StorageKeyFactory.serializableKey(keyName);

        localStorage.remove(key);
    }

    public void updateFromSessionStorage(String keyName, int newQuantity) {
        StorageKey<ShoppingCartItem> key = StorageKeyFactory.serializableKey(keyName);

        try {
            ShoppingCartItem itemToUpdate = localStorage.get(key);
            itemToUpdate.setQuantity(newQuantity);
            localStorage.put(key, itemToUpdate);
        } catch (SerializationException e) {
            GQuery.console.error(e);
        }
    }

    public List<ShoppingCartItem> getItems() {
        List<ShoppingCartItem> items = new ArrayList<>();

        for (int i = 0; i < getSize(); i++) {
            String keyValue = localStorage.key(i);
            StorageKey<ShoppingCartItem> key = StorageKeyFactory.serializableKey(keyValue);

            try {
                ShoppingCartItem item = localStorage.get(key);
                items.add(item);
            } catch (SerializationException e) {
                GQuery.console.error(e);
            }
        }

        return items;
    }

    public int getSize() {
        return localStorage.size();
    }
}
