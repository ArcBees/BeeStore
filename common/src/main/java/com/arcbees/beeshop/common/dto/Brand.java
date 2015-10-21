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

public enum Brand {
    GAE_STUDIO("gae-studio"),
    CHOSEN("chosen"),
    GWTP("gwtp"),
    JUKITO("jukito"),
    GSSS("gsss"),
    GQUERY("gquery"),
    ARCBEES("arcbees");

    private final String value;

    Brand(String value) {
        this.value = value;
    }

    public static Brand createFromValue(String value) {
        for (Brand brand : values()) {
            if (brand.value.equals(value)) {
                return brand;
            }
        }

        return getDefaultValue();
    }

    public static Brand getDefaultValue() {
        return Brand.ARCBEES;
    }

    public String getValue() {
        return value;
    }
}
