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

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface PageHomeResources extends ClientBundle {
    interface Style extends CssResource {
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

        String itemPrice();
    }

    @Source("img/products/arcbees/arcbees-shirt.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesShirt();

    @Source("img/products/arcbees/arcbees-shirt-thumb.png")
    ImageResource arcbeesShirtThumb();

    @Source("img/products/gwtp/gwtp-shirt.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpShirt();

    @Source("img/products/gwtp/gwtp-shirt-thumb.png")
    ImageResource gwtpShirtThumb();

    @Source("img/products/gae/gae-shirt.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeShirt();

    @Source("img/products/gae/gae-shirt-thumb.png")
    ImageResource gaeShirtThumb();

    @Source("img/products/gquery/gquery-shirt.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryShirt();

    @Source("img/products/gquery/gquery-shirt-thumb.png")
    ImageResource gqueryShirtThumb();

    @Source("img/products/chosen/chosen-shirt.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenShirt();

    @Source("img/products/chosen/chosen-shirt-thumb.png")
    ImageResource chosenShirtThumb();

    @Source("img/products/jukito/jukito-shirt.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource jukitoShirt();

    @Source("img/products/jukito/jukito-shirt-thumb.png")
    ImageResource jukitoShirtThumb();

    @Source("img/products/gsss/gsss-shirt.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssShirt();

    @Source("img/products/gsss/gsss-shirt-thumb.png")
    ImageResource gsssShirtThumb();

    @Source("img/products/arcbees/arcbees-bag.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesBag();

    @Source("img/products/chosen/chosen-bag.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenBag();

    @Source("img/products/gae/gae-bag.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeBag();

    @Source("img/products/gquery/gquery-bag.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryBag();

    @Source("img/products/gsss/gsss-bag.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssBag();

    @Source("img/products/gwtp/gwtp-bag.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpBag();

    @Source("img/products/jukito/jukito-bag.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource jukitoBag();

    @Source("img/products/arcbees/arcbees-bag-thumb.png")
    ImageResource arcbeesBagThumb();

    @Source("img/products/chosen/chosen-bag-thumb.png")
    ImageResource chosenBagThumb();

    @Source("img/products/gae/gae-bag-thumb.png")
    ImageResource gaeBagThumb();

    @Source("img/products/gquery/gquery-bag-thumb.png")
    ImageResource gqueryBagThumb();

    @Source("img/products/gsss/gsss-bag-thumb.png")
    ImageResource gsssBagThumb();

    @Source("img/products/gwtp/gwtp-bag-thumb.png")
    ImageResource gwtpBagThumb();

    @Source("img/products/jukito/jukito-bag-thumb.png")
    ImageResource jukitoBagThumb();

    @Source("img/products/arcbees/arcbees-case.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesCase();

    @Source("img/products/chosen/chosen-case.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenCase();

    @Source("img/products/gae/gae-case.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeCase();

    @Source("img/products/gquery/gquery-case.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryCase();

    @Source("img/products/gsss/gsss-case.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssCase();

    @Source("img/products/gwtp/gwtp-case.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpCase();

    @Source("img/products/jukito/jukito-case.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource jukitoCase();

    @Source("img/products/arcbees/arcbees-case-thumb.png")
    ImageResource arcbeesCaseThumb();

    @Source("img/products/chosen/chosen-case-thumb.png")
    ImageResource chosenCaseThumb();

    @Source("img/products/gae/gae-case-thumb.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeCaseThumb();

    @Source("img/products/gquery/gquery-case-thumb.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryCaseThumb();

    @Source("img/products/gsss/gsss-case-thumb.png")
    ImageResource gsssCaseThumb();

    @Source("img/products/gwtp/gwtp-case-thumb.png")
    ImageResource gwtpCaseThumb();

    @Source("img/products/jukito/jukito-case-thumb.png")
    ImageResource jukitoCaseThumb();

    @Source("img/products/arcbees/arcbees-case-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesCaseBig();

    @Source("img/products/chosen/chosen-case-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenCaseBig();

    @Source("img/products/gae/gae-case-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeCaseBig();

    @Source("img/products/gquery/gquery-case-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryCaseBig();

    @Source("img/products/gsss/gsss-case-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssCaseBig();

    @Source("img/products/gwtp/gwtp-case-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpCaseBig();

    @Source("img/products/jukito/jukito-case-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource jukitoCaseBig();

    @Source("img/products/arcbees/arcbees-usb.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesUsb();

    @Source("img/products/chosen/chosen-usb.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenUsb();

    @Source("img/products/gae/gae-usb.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeUsb();

    @Source("img/products/gquery/gquery-usb.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryUsb();

    @Source("img/products/gsss/gsss-usb.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssUsb();

    @Source("img/products/gwtp/gwtp-usb.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpUsb();

    @Source("img/products/jukito/jukito-usb.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource jukitoUsb();

    @Source("img/products/arcbees/arcbees-usb-thumb.png")
    ImageResource arcbeesUsbThumb();

    @Source("img/products/chosen/chosen-usb-thumb.png")
    ImageResource chosenUsbThumb();

    @Source("img/products/gae/gae-usb-thumb.png")
    ImageResource gaeUsbThumb();

    @Source("img/products/gquery/gquery-usb-thumb.png")
    ImageResource gqueryUsbThumb();

    @Source("img/products/gsss/gsss-usb-thumb.png")
    ImageResource gsssUsbThumb();

    @Source("img/products/gwtp/gwtp-usb-thumb.png")
    ImageResource gwtpUsbThumb();

    @Source("img/products/jukito/jukito-usb-thumb.png")
    ImageResource jukitoUsbThumb();

    @Source("img/products/arcbees/arcbees-usb-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesUsbBig();

    @Source("img/products/chosen/chosen-usb-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenUsbBig();

    @Source("img/products/gae/gae-usb-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeUsbBig();

    @Source("img/products/gquery/gquery-usb-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryUsbBig();

    @Source("img/products/gsss/gsss-usb-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssUsbBig();

    @Source("img/products/gwtp/gwtp-usb-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpUsbBig();

    @Source("img/products/jukito/jukito-usb-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource jukitoUsbBig();

    @Source("img/products/arcbees/arcbees-thermos.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesThermos();

    @Source("img/products/chosen/chosen-thermos.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenThermos();

    @Source("img/products/gae/gae-thermos.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeThermos();

    @Source("img/products/gquery/gquery-thermos.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryThermos();

    @Source("img/products/gsss/gsss-thermos.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssThermos();

    @Source("img/products/gwtp/gwtp-thermos.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpThermos();

    @Source("img/products/jukito/jukito-thermos.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource jukitoThermos();

    @Source("img/products/arcbees/arcbees-thermos-thumb.png")
    ImageResource arcbeesThermosThumb();

    @Source("img/products/chosen/chosen-thermos-thumb.png")
    ImageResource chosenThermosThumb();

    @Source("img/products/gae/gae-thermos-thumb.png")
    ImageResource gaeThermosThumb();

    @Source("img/products/gquery/gquery-thermos-thumb.png")
    ImageResource gqueryThermosThumb();

    @Source("img/products/gsss/gsss-thermos-thumb.png")
    ImageResource gsssThermosThumb();

    @Source("img/products/gwtp/gwtp-thermos-thumb.png")
    ImageResource gwtpThermosThumb();

    @Source("img/products/jukito/jukito-thermos-thumb.png")
    ImageResource jukitoThermosThumb();

    @Source("img/products/arcbees/arcbees-thermos-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesThermosBig();

    @Source("img/products/chosen/chosen-thermos-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenThermosBig();

    @Source("img/products/gae/gae-thermos-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeThermosBig();

    @Source("img/products/gquery/gquery-thermos-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryThermosBig();

    @Source("img/products/gsss/gsss-thermos-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssThermosBig();

    @Source("img/products/gwtp/gwtp-thermos-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpThermosBig();

    @Source("img/products/jukito/jukito-thermos-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource jukitoThermosBig();

    @Source("img/products/arcbees/arcbees-cup.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesCup();

    @Source("img/products/chosen/chosen-cup.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenCup();

    @Source("img/products/gae/gae-cup.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeCup();

    @Source("img/products/gquery/gquery-cup.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryCup();

    @Source("img/products/gsss/gsss-cup.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssCup();

    @Source("img/products/gwtp/gwtp-cup.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpCup();

    @Source("img/products/jukito/jukito-cup.png")
    ImageResource jukitoCup();

    @Source("img/products/arcbees/arcbees-cup-thumb.png")
    ImageResource arcbeesCupThumb();

    @Source("img/products/chosen/chosen-cup-thumb.png")
    ImageResource chosenCupThumb();

    @Source("img/products/gae/gae-cup-thumb.png")
    ImageResource gaeCupThumb();

    @Source("img/products/gquery/gquery-cup-thumb.png")
    ImageResource gqueryCupThumb();

    @Source("img/products/gsss/gsss-cup-thumb.png")
    ImageResource gsssCupThumb();

    @Source("img/products/gwtp/gwtp-cup-thumb.png")
    ImageResource gwtpCupThumb();

    @Source("img/products/jukito/jukito-cup-thumb.png")
    ImageResource jukitoCupThumb();

    @Source("img/products/arcbees/arcbees-cup-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource arcbeesCupBig();

    @Source("img/products/chosen/chosen-cup-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource chosenCupBig();

    @Source("img/products/gae/gae-cup-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gaeCupBig();

    @Source("img/products/gquery/gquery-cup-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gqueryCupBig();

    @Source("img/products/gsss/gsss-cup-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gsssCupBig();

    @Source("img/products/gwtp/gwtp-cup-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource gwtpCupBig();

    @Source("img/products/jukito/jukito-cup-Big.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource jukitoCupBig();

    @Source({"com/arcbees/gsss/mixin/client/mixins.gss",
            "css/colors.gss",
            "fonts/fonts.gss",
            "css/pages/home.gss"})
    Style style();
}
