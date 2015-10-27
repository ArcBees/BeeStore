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

package com.arcbees.beeshop.client.resources;

import com.arcbees.beeshop.common.dto.Brand;

public class Colors {
    /* -> App colors -- */
    public static final String C_PRIMARY = "#ffe200";

    public static final String C_LIGHT = "#fff";
    public static final String C_DARK = "#000";
    public static final String C_NEGATIVE = "#ff0000";

    /* -> Text colors -- */
    public static final String C_TEXT = C_DARK;
    public static final String C_TEXT_LIGHT = C_LIGHT;
    public static final String C_TEXT_ERROR = "#ff0000";

    /* -> Stripe colors -- */
    public static final String C_STRIPE_LIGHT = C_LIGHT;
    public static final String C_STRIPE_MEDIUM1 = "#f1f1f1";
    public static final String C_STRIPE_MEDIUM2 = "#e6e6e6";
    public static final String C_STRIPE_MEDIUM3 = "#dedddd";
    public static final String C_STRIPE_MEDIUM4 = "#d4d4d4";
    public static final String C_STRIPE_DARK = C_DARK;
    public static final String C_STRIPE_COLORED = C_PRIMARY;

    /* -> Product colors -- */
    public static final String C_PRODUCT_CHOSEN = "#b4d333";
    public static final String C_PRODUCT_GAE = "#36baec";
    public static final String C_PRODUCT_GWTP = "#f7941e";
    public static final String C_PRODUCT_JUKITO = "#ed1c24";
    public static final String C_PRODUCT_GQUERY = "#0768ac";
    public static final String C_PRODUCT_GSS = "#594588";

    public static String getBrandColor(Brand brand) {
        switch (brand) {
            case ARCBEES:
                return C_PRIMARY;
            case CHOSEN:
                return C_PRODUCT_CHOSEN;
            case JUKITO:
                return C_PRODUCT_JUKITO;
            case GWTP:
                return C_PRODUCT_GWTP;
            case GAE_STUDIO:
                return C_PRODUCT_GAE;
            case GQUERY:
                return C_PRODUCT_GQUERY;
            case GSSS:
                return C_PRODUCT_GSS;
            default:
                return C_PRIMARY;
        }
    }
}
