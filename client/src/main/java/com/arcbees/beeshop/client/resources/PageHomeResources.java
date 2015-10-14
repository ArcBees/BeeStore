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

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface PageHomeResources extends ClientBundle {
    interface Style extends CssResource {
        String devProducts_list();

        String itemsForSale();

        String itemForSale();

        String itemForSale_img();

        String itemForSale_info();

        String itemForSale_info_name();

        String itemForSale_info_price();

        String welcome();

        String shoppingByProduct();

        String mainProducts();

        String mainProducts_left();

        String mainProducts_right();
    }

    @Source("img/t-shirt-men.png")
    ImageResource tshirt();

    @Source("img/products/product-bag.png")
    ImageResource productBag();

    @Source("img/products/product-case.png")
    ImageResource productCase();

    @Source("img/products/product-usb.png")
    ImageResource productUsb();

    @Source("img/products/product-thermos.png")
    ImageResource productThermos();

    @Source("img/products/product-cup.png")
    ImageResource productCup();

    @Source({"com/arcbees/gsss/mixin/client/mixins.gss",
            "css/colors.gss",
            "fonts/fonts.gss",
            "css/pages/home.gss"})
    Style style();
}
