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
    SHIRT(1, 30),
    BAG(2, 25),
    USB_KEY(3, 10),
    MUG(4, 10),
    THERMOS(5, 5),
    PHONE_CASE(6, 15);

    private final int value;

    public int getPrice() {
        return price;
    }

    private final int price;

    Product(int value, int price) {
        this.value = value;
        this.price = price;
    }

    public int getValue() {
        return value;
    }
}
