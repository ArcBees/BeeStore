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

import org.vectomatic.dom.svg.ui.SVGResource;
import com.arcbees.gsss.grid.client.GridResources;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface AppResources extends ClientBundle {
    interface Normalize extends CssResource {
    }

    interface Style extends CssResource {
        String stripe();

        String footer_contact();

        String footer_linkArcbees();

        String footer_linkSocial();

        String wrapper();

        String wrap();

        String header_top();

        String header_menu();

        String header_bottom();

        String header_bottom__right();

        String wishlist();

        String cart();

        String beestore_logo();

        String mobileDisplay();

        String language();

        String footer_menu();

        String footer_logoArcbees();

        String clearfix();

        String footer_caseStudy();

        String txt_center();

        String active();

        String header_developersPlatform();

        String backTop();

        String inputQuantity();

        String visuallyHidden();

        String push();

        String container();

        String main();

        String arcbees();

        String gwtp();

        String chosen();

        String jukito();

        String gquery();

        String gsss();

        String gaestudio();

        String itemPrice();

        String mainProducts_bg();

        String gwtp_img();

        String arcbees_img();

        String jukito_img();

        String chosen_img();

        String gquery_img();

        String gaestudio_img();

        String gsss_img();
    }

    @Source("img/Beestore-logo.png")
    ImageResource beestoreLogo();

    SVGResource logoBeestore();

    SVGResource arcbees();

    SVGResource chosen();

    SVGResource gae();

    SVGResource gquery();

    SVGResource gsss();

    SVGResource gwtp();

    SVGResource jukito();

    @Source("css/normalize.gss")
    Normalize normalize();

    @Source({"com/arcbees/gsss/mixin/client/mixins.gss",
            "css/base.gss",
            "css/colors.gss",
            "fonts/fonts.gss",
            "css/layout.gss",
            "css/style.gss"})
    Style style();

    @Source({"com/arcbees/gsss/grid/client/gridsettings.gss", "com/arcbees/gsss/grid/client/grid.gss"})
    GridResources.Grid grid();
}
