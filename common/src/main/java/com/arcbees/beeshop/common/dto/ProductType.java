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

package com.arcbees.beeshop.common.dto;

public enum ProductType {
    SHIRT(1, 30),
    BAG(2, 25),
    THERMOS(3, 5),
    PHONE_CASE(4, 15),
    USB_KEY(5, 10),
    MUG(6, 10);

    private final int id;
    private final int price;

    ProductType(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public static ProductType createFromId(int id) {
        for (ProductType productType : ProductType.values()) {
            if (productType.getId() == id) {
                return productType;
            }
        }

        return getDefaultValue();
    }

    private static ProductType getDefaultValue() {
        return SHIRT;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public ProductType getPreviousProduct() {
        int previousId = getId() - 1;
        if (previousId == 0) {
            previousId = values().length;
        }

        return createFromId(previousId);
    }

    public ProductType getNextProduct() {
        int nextId = getId() + 1;
        if (nextId == values().length + 1) {
            nextId = 1;
        }

        return createFromId(nextId);
    }
}
