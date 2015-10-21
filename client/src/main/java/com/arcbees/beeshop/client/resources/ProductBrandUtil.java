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
import com.arcbees.beeshop.common.dto.Product;
import com.google.gwt.resources.client.ImageResource;

import static java.util.AbstractMap.SimpleEntry;

public class ProductBrandUtil {
    private final Map<SimpleEntry<Brand, Product>, ImageResource> images;

    @Inject
    public ProductBrandUtil(PageHomeResources resources) {
        images = new HashMap<>();

        images.put(new SimpleEntry<>(Brand.ARCBEES, Product.SHIRT), resources.tshirt());
        images.put(new SimpleEntry<>(Brand.ARCBEES, Product.BAG), resources.arcbeesBag());
        images.put(new SimpleEntry<>(Brand.ARCBEES, Product.MUG), resources.arcbeesCup());
        images.put(new SimpleEntry<>(Brand.ARCBEES, Product.THERMOS), resources.arcbeesThermos());
        images.put(new SimpleEntry<>(Brand.ARCBEES, Product.USB_KEY), resources.arcbeesUsb());
        images.put(new SimpleEntry<>(Brand.ARCBEES, Product.PHONE_CASE), resources.arcbeesCase());

        images.put(new SimpleEntry<>(Brand.GWTP, Product.SHIRT), resources.tshirt());
        images.put(new SimpleEntry<>(Brand.GWTP, Product.BAG), resources.gwtpBag());
        images.put(new SimpleEntry<>(Brand.GWTP, Product.MUG), resources.gwtpCup());
        images.put(new SimpleEntry<>(Brand.GWTP, Product.THERMOS), resources.gwtpThermos());
        images.put(new SimpleEntry<>(Brand.GWTP, Product.USB_KEY), resources.gwtpUsb());
        images.put(new SimpleEntry<>(Brand.GWTP, Product.PHONE_CASE), resources.gwtpCase());

        images.put(new SimpleEntry<>(Brand.CHOSEN, Product.SHIRT), resources.tshirt());
        images.put(new SimpleEntry<>(Brand.CHOSEN, Product.BAG), resources.chosenBag());
        images.put(new SimpleEntry<>(Brand.CHOSEN, Product.MUG), resources.chosenCup());
        images.put(new SimpleEntry<>(Brand.CHOSEN, Product.THERMOS), resources.chosenThermos());
        images.put(new SimpleEntry<>(Brand.CHOSEN, Product.USB_KEY), resources.chosenUsb());
        images.put(new SimpleEntry<>(Brand.CHOSEN, Product.PHONE_CASE), resources.chosenCase());

        images.put(new SimpleEntry<>(Brand.JUKITO, Product.SHIRT), resources.tshirt());
        images.put(new SimpleEntry<>(Brand.JUKITO, Product.BAG), resources.jukitoBag());
        images.put(new SimpleEntry<>(Brand.JUKITO, Product.MUG), resources.jukitoCup());
        images.put(new SimpleEntry<>(Brand.JUKITO, Product.THERMOS), resources.jukitoThermos());
        images.put(new SimpleEntry<>(Brand.JUKITO, Product.USB_KEY), resources.jukitoUsb());
        images.put(new SimpleEntry<>(Brand.JUKITO, Product.PHONE_CASE), resources.jukitoCase());

        images.put(new SimpleEntry<>(Brand.GQUERY, Product.SHIRT), resources.tshirt());
        images.put(new SimpleEntry<>(Brand.GQUERY, Product.BAG), resources.gqueryBag());
        images.put(new SimpleEntry<>(Brand.GQUERY, Product.MUG), resources.gqueryCup());
        images.put(new SimpleEntry<>(Brand.GQUERY, Product.THERMOS), resources.gqueryThermos());
        images.put(new SimpleEntry<>(Brand.GQUERY, Product.USB_KEY), resources.gqueryUsb());
        images.put(new SimpleEntry<>(Brand.GQUERY, Product.PHONE_CASE), resources.gqueryCase());

        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, Product.SHIRT), resources.tshirt());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, Product.BAG), resources.gaeBag());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, Product.MUG), resources.gaeCup());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, Product.THERMOS), resources.gaeThermos());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, Product.USB_KEY), resources.gaeUsb());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, Product.PHONE_CASE), resources.gaeCase());

        images.put(new SimpleEntry<>(Brand.GSSS, Product.SHIRT), resources.tshirt());
        images.put(new SimpleEntry<>(Brand.GSSS, Product.BAG), resources.gsssBag());
        images.put(new SimpleEntry<>(Brand.GSSS, Product.MUG), resources.gsssCup());
        images.put(new SimpleEntry<>(Brand.GSSS, Product.THERMOS), resources.gsssThermos());
        images.put(new SimpleEntry<>(Brand.GSSS, Product.USB_KEY), resources.gsssUsb());
        images.put(new SimpleEntry<>(Brand.GSSS, Product.PHONE_CASE), resources.gsssCase());
    }

    public ImageResource getImage(Product product, Brand brand) {
        return images.get(new SimpleEntry<>(brand, product));
    }
}
