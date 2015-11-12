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

import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.ProductType;
import com.arcbees.seo.Image;

import static com.arcbees.beestore.common.Constants.IMAGES_ROOT_URL;
import static com.arcbees.seo.widget.Image.MimeType.PNG;

public class SeoImages {
    private static final int WIDTH = 622;
    private static final int HEIGHT = 761;

    public Image getImage(ProductType productType, Brand brand) {
        return image(brand.getValue() + "-" + productType.name().toLowerCase());
    }

    private Image image(String imageFileName) {
        return new Image(IMAGES_ROOT_URL + imageFileName + ".png", HEIGHT, WIDTH, PNG);
    }
}
