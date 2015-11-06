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

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.inject.Inject;
import com.seanchenxi.gwt.storage.client.StorageExt;
import com.seanchenxi.gwt.storage.client.StorageKey;
import com.seanchenxi.gwt.storage.client.StorageKeyFactory;

public class SessionStorageHandler {
    private final SessionStorageKeyProvider keyProvider;
    private final StorageExt sessionStorage = StorageExt.getSessionStorage();

    @Inject
    SessionStorageHandler(
            SessionStorageKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    public void addToSessionStorage(ShoppingCartItem item) {
        try {
            sessionStorage.put(keyProvider.shoppingCartItemKey(item.getStorageKeyName()), item);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromSessionStorage(ShoppingCartItem item) {
        String keyName = item.getStorageKeyName();
        StorageKey<ShoppingCartItem> key = StorageKeyFactory.serializableKey(keyName);

        sessionStorage.remove(key);
    }

    public void updateFromSessionStorage(String keyName, int newQuantity) {
        StorageKey<ShoppingCartItem> key = StorageKeyFactory.serializableKey(keyName);

        try {
            ShoppingCartItem itemToUpdate = sessionStorage.get(key);
            itemToUpdate.setQuantity(newQuantity);
            sessionStorage.put(key, itemToUpdate);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
    }

    public List<ShoppingCartItem> getItems() {
        List<ShoppingCartItem> items = new ArrayList<>();

        for (int i = 0; i < getSize(); i++) {
            String keyValue = sessionStorage.key(i);
            StorageKey<ShoppingCartItem> key = StorageKeyFactory.serializableKey(keyValue);

            try {
                ShoppingCartItem item = sessionStorage.get(key);
                items.add(item);
            } catch (SerializationException e) {
                e.printStackTrace();
            }
        }

        return items;
    }

    public int getSize() {
        return sessionStorage.size();
    }
}
