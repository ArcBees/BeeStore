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

package com.arcbees.beestore.common;

import java.util.HashMap;
import java.util.Map;

import com.arcbees.beestore.common.dto.Brand;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class NameTokens {
    public static final String PARAM_SIZE = "size";
    public static final String PARAM_BRAND = "brand";
    public static final String PARAM_ID = "id";

    public static final String WRAPPED_ID = "/{" + PARAM_ID + "}";

    public static final String HOME = "!/";

    public static final String PRODUCTS = "!/products";
    public static final String PRODUCTS_FR = "!/produits";

    public static final String PRODUCT = PRODUCTS + WRAPPED_ID;
    public static final String PRODUCT_FR = PRODUCTS_FR + WRAPPED_ID;

    public static final String GAE_STUDIO = Brand.GAE_STUDIO.getValue();
    public static final String GWTP = Brand.GWTP.getValue();
    public static final String CHOSEN = Brand.CHOSEN.getValue();
    public static final String JUKITO = Brand.JUKITO.getValue();
    public static final String GSSS = Brand.GSSS.getValue();
    public static final String GQUERY = Brand.GQUERY.getValue();
    public static final String ARCBEES = Brand.ARCBEES.getValue();

    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_FRENCH = "fr";

    public static final String NOT_FOUND = "!/notfound";

    private static final BiMap<String, String> placeKeys;

    public static boolean matchesAnyLanguageOfNameToken(String nametokenToCheck, String nametokenToCheckAgainst) {
        String translated = translate(nametokenToCheckAgainst);

        return nametokenToCheck.equals(translated) || nametokenToCheck.equals(nametokenToCheckAgainst);
    }

    public static String translate(String nameToken) {
        if (isEnglish(nameToken)) {
            return placeKeys.get(nameToken);
        } else {
            return placeKeys.inverse().get(nameToken);
        }
    }

    public static boolean isEnglish(String nameToken) {
        return placeKeys.containsKey(nameToken);
    }

    static {
        Map<String, String> keys = new HashMap<>();
        keys.put(HOME, HOME);
        keys.put(PRODUCTS, PRODUCTS_FR);
        keys.put(PRODUCT, PRODUCT_FR);

        placeKeys = HashBiMap.create(keys);
    }
}
