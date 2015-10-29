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

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.ProductType;
import com.google.gwt.resources.client.ImageResource;

import static java.util.AbstractMap.SimpleEntry;

public class ProductBrandUtil {
    private final Map<SimpleEntry<Brand, ProductType>, ImageResource> images;

    @Inject
    public ProductBrandUtil(PageHomeResources resources) {
        images = new HashMap<>();

        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.SHIRT), resources.arcbeesShirt());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.BAG), resources.arcbeesBag());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.MUG), resources.arcbeesCup());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.THERMOS), resources.arcbeesThermos());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.USB_KEY), resources.arcbeesUsb());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.PHONE_CASE), resources.arcbeesCase());

        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.SHIRT), resources.gwtpShirt());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.BAG), resources.gwtpBag());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.MUG), resources.gwtpCup());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.THERMOS), resources.gwtpThermos());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.USB_KEY), resources.gwtpUsb());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.PHONE_CASE), resources.gwtpCase());

        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.SHIRT), resources.chosenShirt());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.BAG), resources.chosenBag());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.MUG), resources.chosenCup());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.THERMOS), resources.chosenThermos());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.USB_KEY), resources.chosenUsb());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.PHONE_CASE), resources.chosenCase());

        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.SHIRT), resources.jukitoShirt());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.BAG), resources.jukitoBag());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.MUG), resources.jukitoCup());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.THERMOS), resources.jukitoThermos());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.USB_KEY), resources.jukitoUsb());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.PHONE_CASE), resources.jukitoCase());

        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.SHIRT), resources.gqueryShirt());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.BAG), resources.gqueryBag());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.MUG), resources.gqueryCup());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.THERMOS), resources.gqueryThermos());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.USB_KEY), resources.gqueryUsb());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.PHONE_CASE), resources.gqueryCase());

        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.SHIRT), resources.gaeShirt());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.BAG), resources.gaeBag());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.MUG), resources.gaeCup());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.THERMOS), resources.gaeThermos());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.USB_KEY), resources.gaeUsb());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.PHONE_CASE), resources.gaeCase());

        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.SHIRT), resources.gsssShirt());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.BAG), resources.gsssBag());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.MUG), resources.gsssCup());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.THERMOS), resources.gsssThermos());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.USB_KEY), resources.gsssUsb());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.PHONE_CASE), resources.gsssCase());
    }

    public ImageResource getImage(ProductType productType, Brand brand) {
        return images.get(new SimpleEntry<>(brand, productType));
    }
}
