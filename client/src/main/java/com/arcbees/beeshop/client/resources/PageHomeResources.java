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

        String welcome();

        String shoppingByProduct();

        String mainProducts();

        String mainProducts_left();

        String mainProducts_right();

        String itemForSale_info_shop();

        String pricePresenter();

        String gsssIcon();

        String iconText();

        String productIcon();

        String chosenIcon();

        String arcbeesIcon();

        String gaeIcon();

        String gqueryIcon();

        String jukitoIcon();

        String gwtpIcon();

        String devProducts_list_gae();

        String devProducts_list_chosen();
    }
       
    @Source("img/t-shirt-men.png")
    ImageResource tshirt();

    @Source("img/products/product-bag.png")
    ImageResource productBag();

    @Source("img/products/arcbees/arcbees-shirt.png")
    ImageResource arcbeesShirt();

    @Source("img/products/gwtp/gwtp-shirt.png")
    ImageResource gwtpShirt();

    @Source("img/products/gae/gae-shirt.png")
    ImageResource gaeShirt();

    @Source("img/products/gquery/gquery-shirt.png")
    ImageResource gqueryShirt();

    @Source("img/products/chosen/chosen-shirt.png")
    ImageResource chosenShirt();

    @Source("img/products/jukito/jukito-shirt.png")
    ImageResource jukitoShirt();

    @Source("img/products/gsss/gsss-shirt.png")
    ImageResource gsssShirt();

    @Source("img/products/arcbees/arcbees-bag.png")
    ImageResource arcbeesBag();

    @Source("img/products/chosen/chosen-bag.png")
    ImageResource chosenBag();

    @Source("img/products/gae/gae-bag.png")
    ImageResource gaeBag();

    @Source("img/products/gquery/gquery-bag.png")
    ImageResource gqueryBag();

    @Source("img/products/gsss/gsss-bag.png")
    ImageResource gsssBag();

    @Source("img/products/gwtp/gwtp-bag.png")
    ImageResource gwtpBag();

    @Source("img/products/jukito/jukito-bag.png")
    ImageResource jukitoBag();

    @Source("img/products/arcbees/arcbees-case.png")
    ImageResource arcbeesCase();

    @Source("img/products/chosen/chosen-case.png")
    ImageResource chosenCase();

    @Source("img/products/gae/gae-case.png")
    ImageResource gaeCase();

    @Source("img/products/gquery/gquery-case.png")
    ImageResource gqueryCase();

    @Source("img/products/gsss/gsss-case.png")
    ImageResource gsssCase();

    @Source("img/products/gwtp/gwtp-case.png")
    ImageResource gwtpCase();

    @Source("img/products/jukito/jukito-case.png")
    ImageResource jukitoCase();

    @Source("img/products/arcbees/arcbees-usb.png")
    ImageResource arcbeesUsb();

    @Source("img/products/chosen/chosen-usb.png")
    ImageResource chosenUsb();

    @Source("img/products/gae/gae-usb.png")
    ImageResource gaeUsb();

    @Source("img/products/gquery/gquery-usb.png")
    ImageResource gqueryUsb();

    @Source("img/products/gsss/gsss-usb.png")
    ImageResource gsssUsb();

    @Source("img/products/gwtp/gwtp-usb.png")
    ImageResource gwtpUsb();

    @Source("img/products/jukito/jukito-usb.png")
    ImageResource jukitoUsb();

    @Source("img/products/arcbees/arcbees-thermos.png")
    ImageResource arcbeesThermos();

    @Source("img/products/chosen/chosen-thermos.png")
    ImageResource chosenThermos();

    @Source("img/products/gae/gae-thermos.png")
    ImageResource gaeThermos();

    @Source("img/products/gquery/gquery-thermos.png")
    ImageResource gqueryThermos();

    @Source("img/products/gsss/gsss-thermos.png")
    ImageResource gsssThermos();

    @Source("img/products/gwtp/gwtp-thermos1.png")
    ImageResource gwtpThermos();

    @Source("img/products/jukito/jukito-thermos.png")
    ImageResource jukitoThermos();

    @Source("img/products/arcbees/arcbees-cup.png")
    ImageResource arcbeesCup();

    @Source("img/products/chosen/chosen-cup.png")
    ImageResource chosenCup();

    @Source("img/products/gae/gae-cup.png")
    ImageResource gaeCup();

    @Source("img/products/gquery/gquery-cup.png")
    ImageResource gqueryCup();

    @Source("img/products/gsss/gsss-cup.png")
    ImageResource gsssCup();

    @Source("img/products/gwtp/gwtp-cup.png")
    ImageResource gwtpCup();

    @Source("img/products/jukito/jukito-cup.png")
    ImageResource jukitoCup();

    @Source({"com/arcbees/gsss/mixin/client/mixins.gss",
            "css/colors.gss",
            "fonts/fonts.gss",
            "css/pages/home.gss"})
    Style style();
}
