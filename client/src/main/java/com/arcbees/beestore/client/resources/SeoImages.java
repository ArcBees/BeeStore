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

package com.arcbees.beestore.client.resources;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.ProductType;
import com.arcbees.seo.Image;

import static com.arcbees.seo.widget.OgImage.MimeType.PNG;

public class SeoImages {
    private final Map<SimpleEntry<Brand, ProductType>, Image> images;

    public SeoImages() {
        images = initializeImages();
    }

    private Map<SimpleEntry<Brand, ProductType>, Image> initializeImages() {
        Map<SimpleEntry<Brand, ProductType>, Image> images = new HashMap<>();

        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.SHIRT), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.BAG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.MUG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.THERMOS), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.USB_KEY), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.ARCBEES, ProductType.PHONE_CASE), image("http://store.arcbees.com/img/arcbees-shirt.png"));

        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.SHIRT), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.BAG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.MUG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.THERMOS), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.USB_KEY), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GWTP, ProductType.PHONE_CASE), image("http://store.arcbees.com/img/arcbees-shirt.png"));

        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.SHIRT), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.BAG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.MUG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.THERMOS), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.USB_KEY), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.CHOSEN, ProductType.PHONE_CASE), image("http://store.arcbees.com/img/arcbees-shirt.png"));

        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.SHIRT), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.BAG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.MUG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.THERMOS), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.USB_KEY), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.JUKITO, ProductType.PHONE_CASE), image("http://store.arcbees.com/img/arcbees-shirt.png"));

        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.SHIRT), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.BAG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.MUG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.THERMOS), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.USB_KEY), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GQUERY, ProductType.PHONE_CASE), image("http://store.arcbees.com/img/arcbees-shirt.png"));

        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.SHIRT), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.BAG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.MUG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.THERMOS), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.USB_KEY), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GAE_STUDIO, ProductType.PHONE_CASE), image("http://store.arcbees.com/img/arcbees-shirt.png"));

        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.SHIRT), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.BAG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.MUG), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.THERMOS), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.USB_KEY), image("http://store.arcbees.com/img/arcbees-shirt.png"));
        images.put(new SimpleEntry<>(Brand.GSSS, ProductType.PHONE_CASE), image("http://store.arcbees.com/img/arcbees-shirt.png"));

        return images;
    }

    private Image image(String url) {
        return new Image(url, 600, 315, PNG);
    }

    public Image getImage(ProductType productType, Brand brand) {
        return images.get(new SimpleEntry<>(brand, productType));
    }
}
