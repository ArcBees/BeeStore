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

public enum Product {
    SHIRT(1, 30, "Bee Shirt", "Grey Logo", "M"),
    BAG(2, 25, "Bee Bag", "Black Logo", "Unique"),
    THERMOS(3, 5, "Bee Cup", "Black Logo", "Unique"),
    PHONE_CASE(4, 15, "Bee Case", "Black Logo", "Unique"),
    USB_KEY(5, 10, "Bee Key", "Black Logo", "Unique"),
    MUG(6, 10, "Bee Mug", "Yellow Logo", "Unique");

    private final int id;
    private final int price;
    private final String name;
    private final String description;
    private final String size;

    Product(int id, int price, String name, String description, String size) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.size = size;
    }

    public static Product createFromId(int id) {
        for (Product product : Product.values()) {
            if (product.getId() == id) {
                return product;
            }
        }

        return getDefaultValue();
    }

    private static Product getDefaultValue() {
        return SHIRT;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public Product getPreviousProduct() {
        int previousId = getId() - 1;
        if (previousId == 0) {
            previousId = values().length;
        }

        return createFromId(previousId);
    }

    public Product getNextProduct() {
        int nextId = getId() + 1;
        if (nextId == values().length + 1) {
            nextId = 1;
        }

        return createFromId(nextId);
    }
}
