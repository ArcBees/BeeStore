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
import com.google.gwt.resources.client.DataResource;

public interface FontResources extends ClientBundle {
    String MIME_TYPE_TTF = "application/font-sfnt";
    String MIME_TYPE_EOT = "application/vnd.ms-fontobject";
    String MIME_TYPE_WOFF = "application/font-woff";
    String MIME_TYPE_SVG = "image/svg+xml";

    public interface Font extends CssResource {
    }

    public interface Icons extends CssResource {
        String iconArcbees();

        String iconGitHub();

        String iconPhone();

        String iconHeadQuestion();

        String iconChat();

        String iconGwt();

        String iconArrowDown();

        String iconInfinite();

        String iconArcbeesName();

        String iconHeart();

        String iconLkd();

        String iconShare();

        String iconWebSupport();

        String iconGCloudPlatform();

        String iconTwitter();

        String iconFb();

        String iconDevTime();

        String iconKnowledgeBase();

        String iconGae();

        String iconHexaEmpty();

        String iconWordpress();

        String iconInfo();

        String iconEmail();

        String iconSignCode();

        String iconUser();

        String iconGPlus();

        String iconGearing();

        String iconGquery();

        String iconMapPin();

        String iconSupportCases();

        String iconBeer();

        String iconBees();

        String iconInternetSupport();

        String iconChosen();

        String iconArrowOutside();

        String iconCommRes();

        String iconFbSquare();

        String iconRespTime();

        String iconCode();

        String iconTrophe();

        String iconJukito();

        String iconPhoneSupport();

        String iconClose();

        String iconRubic();

        String valignSup();

        String iconRadar();

        String iconSearch();

        String iconHtml5();

        String iconHero();

        String iconLike();

        String iconSkull();

        String iconPyramid();

        String iconHive();

        String iconGwtp();

        String iconHexagon();

        String iconCart();

        String iconWhishlist();

        String iconInfoHexa();

        String iconDesktop();

        String iconTabletDevice();

        String iconPhoneDevice();

        String iconGsss();
    }

    @Source("fonts/icons/icons.gss")
    Icons icons();

    @Source({"fonts/fonts.gss", "fonts/geometria/geometria.gss"})
    Font font();

    @Source("fonts/icons/icons.ttf")
    DataResource iconsTtf();

    @Source("fonts/icons/icons.eot")
    DataResource iconsEot();

    @Source("fonts/icons/icons.svg")
    DataResource iconsSvg();

    @Source("fonts/icons/icons.woff")
    DataResource iconsWoff();

    @DataResource.MimeType(MIME_TYPE_TTF)
    @Source("fonts/geometria/geometria-webfont.ttf")
    DataResource geometriaTtf();

    @DataResource.MimeType(MIME_TYPE_EOT)
    @Source("fonts/geometria/geometria-webfont.eot")
    DataResource geometriaEot();

    @DataResource.MimeType(MIME_TYPE_SVG)
    @Source("fonts/geometria/geometria-webfont.svg")
    DataResource geometriaSvg();

    @DataResource.MimeType(MIME_TYPE_WOFF)
    @Source("fonts/geometria/geometria-webfont.woff")
    DataResource geometriaWoff();

    @DataResource.MimeType(MIME_TYPE_TTF)
    @Source("fonts/geometria/geometriabold-webfont.ttf")
    DataResource geometriaBoldTtf();

    @DataResource.MimeType(MIME_TYPE_EOT)
    @Source("fonts/geometria/geometriabold-webfont.eot")
    DataResource geometriaBoldEot();

    @DataResource.MimeType(MIME_TYPE_SVG)
    @Source("fonts/geometria/geometriabold-webfont.svg")
    DataResource geometriaBoldSvg();

    @DataResource.MimeType(MIME_TYPE_WOFF)
    @Source("fonts/geometria/geometriabold-webfont.woff")
    DataResource geometriaBoldWoff();

    @DataResource.MimeType(MIME_TYPE_TTF)
    @Source("fonts/geometria/geometrialight-webfont.ttf")
    DataResource geometriaLightTtf();

    @DataResource.MimeType(MIME_TYPE_EOT)
    @Source("fonts/geometria/geometrialight-webfont.eot")
    DataResource geometriaLightEot();

    @DataResource.MimeType(MIME_TYPE_SVG)
    @Source("fonts/geometria/geometrialight-webfont.svg")
    DataResource geometriaLightSvg();

    @DataResource.MimeType(MIME_TYPE_WOFF)
    @Source("fonts/geometria/geometrialight-webfont.woff")
    DataResource geometriaLightWoff();

    @DataResource.MimeType(MIME_TYPE_TTF)
    @Source("fonts/geometria/geometrialightitalic-webfont.ttf")
    DataResource geometriaLightItalicTtf();

    @DataResource.MimeType(MIME_TYPE_EOT)
    @Source("fonts/geometria/geometrialightitalic-webfont.eot")
    DataResource geometriaLightItalicEot();

    @DataResource.MimeType(MIME_TYPE_SVG)
    @Source("fonts/geometria/geometrialightitalic-webfont.svg")
    DataResource geometriaLightItalicSvg();

    @DataResource.MimeType(MIME_TYPE_WOFF)
    @Source("fonts/geometria/geometrialightitalic-webfont.woff")
    DataResource geometriaLightItalicWoff();
}
