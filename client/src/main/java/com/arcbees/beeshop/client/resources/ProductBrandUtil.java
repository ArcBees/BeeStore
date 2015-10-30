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
    private final Map<SimpleEntry<Brand, ProductType>, ImageResource> thumbnailImages;
    private final Map<SimpleEntry<Brand, ProductType>, ImageResource> bigImages;

    @Inject
    public ProductBrandUtil(PageHomeResources resources) {
        images = initializeNormalImages(resources);
        thumbnailImages = initializeThumbnailImages(resources);
        bigImages = initializeBigImages(resources);
    }

    private Map<SimpleEntry<Brand, ProductType>, ImageResource> initializeNormalImages(PageHomeResources resources) {
        Map<SimpleEntry<Brand, ProductType>, ImageResource> images;
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

        return images;
    }

    private Map<SimpleEntry<Brand, ProductType>, ImageResource> initializeThumbnailImages(PageHomeResources resources) {
        Map<SimpleEntry<Brand, ProductType>, ImageResource> images = new HashMap<>();


        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.SHIRT), resources.arcbeesShirtThumb());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.BAG), resources.arcbeesBagThumb());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.MUG), resources.arcbeesCupThumb());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.THERMOS), resources.arcbeesThermosThumb());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.USB_KEY), resources.arcbeesUsbThumb());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.PHONE_CASE), resources.arcbeesCaseThumb());

        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.SHIRT), resources.gwtpShirtThumb());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.BAG), resources.gwtpBagThumb());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.MUG), resources.gwtpCupThumb());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.THERMOS), resources.gwtpThermosThumb());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.USB_KEY), resources.gwtpUsbThumb());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.PHONE_CASE), resources.gwtpCaseThumb());

        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.SHIRT), resources.chosenShirtThumb());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.BAG), resources.chosenBagThumb());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.MUG), resources.chosenCup());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.THERMOS), resources.chosenThermos());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.USB_KEY), resources.chosenUsbThumb());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.PHONE_CASE), resources.chosenCaseThumb());

        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.SHIRT), resources.jukitoShirtThumb());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.BAG), resources.jukitoBagThumb());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.MUG), resources.jukitoCupThumb());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.THERMOS), resources.jukitoThermosThumb());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.USB_KEY), resources.jukitoUsbThumb());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.PHONE_CASE), resources.jukitoCaseThumb());

        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.SHIRT), resources.gqueryShirtThumb());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.BAG), resources.gqueryBagThumb());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.MUG), resources.gqueryCupThumb());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.THERMOS), resources.gqueryThermosThumb());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.USB_KEY), resources.gqueryUsbThumb());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.PHONE_CASE), resources.gqueryCaseThumb());

        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.SHIRT), resources.gaeShirtThumb());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.BAG), resources.gaeBagThumb());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.MUG), resources.gaeCupThumb());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.THERMOS), resources.gaeThermosThumb());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.USB_KEY), resources.gaeUsbThumb());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.PHONE_CASE), resources.gaeCaseThumb());

        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.SHIRT), resources.gsssShirtThumb());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.BAG), resources.gsssBagThumb());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.MUG), resources.gsssCupThumb());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.THERMOS), resources.gsssThermosThumb());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.USB_KEY), resources.gsssUsbThumb());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.PHONE_CASE), resources.gsssCaseThumb());

        return images;
    }

    private Map<SimpleEntry<Brand, ProductType>, ImageResource> initializeBigImages(PageHomeResources resources) {
        Map<SimpleEntry<Brand, ProductType>, ImageResource> images = new HashMap<>();

        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.SHIRT), resources.arcbeesShirt());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.BAG), resources.arcbeesBag());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.MUG), resources.arcbeesCupBig());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.THERMOS), resources.arcbeesThermosBig());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.USB_KEY), resources.arcbeesUsbBig());
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.PHONE_CASE), resources.arcbeesCaseBig());

        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.SHIRT), resources.gwtpShirt());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.BAG), resources.gwtpBag());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.MUG), resources.gwtpCupBig());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.THERMOS), resources.gwtpThermosBig());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.USB_KEY), resources.gwtpUsbBig());
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.PHONE_CASE), resources.gwtpCaseBig());

        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.SHIRT), resources.chosenShirt());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.BAG), resources.chosenBag());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.MUG), resources.chosenCup());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.THERMOS), resources.chosenThermos());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.USB_KEY), resources.chosenUsbBig());
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.PHONE_CASE), resources.chosenCaseBig());

        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.SHIRT), resources.jukitoShirt());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.BAG), resources.jukitoBag());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.MUG), resources.jukitoCupBig());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.THERMOS), resources.jukitoThermosBig());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.USB_KEY), resources.jukitoUsbBig());
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.PHONE_CASE), resources.jukitoCaseBig());

        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.SHIRT), resources.gqueryShirt());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.BAG), resources.gqueryBag());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.MUG), resources.gqueryCupBig());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.THERMOS), resources.gqueryThermosBig());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.USB_KEY), resources.gqueryUsbBig());
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.PHONE_CASE), resources.gqueryCaseBig());

        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.SHIRT), resources.gaeShirt());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.BAG), resources.gaeBag());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.MUG), resources.gaeCupBig());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.THERMOS), resources.gaeThermosBig());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.USB_KEY), resources.gaeUsbBig());
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.PHONE_CASE), resources.gaeCaseBig());

        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.SHIRT), resources.gsssShirt());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.BAG), resources.gsssBag());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.MUG), resources.gsssCupBig());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.THERMOS), resources.gsssThermosBig());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.USB_KEY), resources.gsssUsbBig());
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.PHONE_CASE), resources.gsssCaseBig());

        return images;
    }

    public ImageResource getBigImage(ProductType productType, Brand brand) {
        return bigImages.get(new SimpleEntry<>(brand, productType));
    }

    public ImageResource getImage(ProductType productType, Brand brand) {
        return images.get(new SimpleEntry<>(brand, productType));
    }

    public ImageResource getThumbnail(ProductType productType, Brand brand) {
        return thumbnailImages.get(new SimpleEntry<>(brand, productType));
    }
}
