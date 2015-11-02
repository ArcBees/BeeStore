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

public interface PageProductResources extends ClientBundle {
    interface Style extends CssResource {
        String product_info();

        String product_nav();

        String product();

        String product_img();

        String product_description();

        String product_option();

        String previous();

        String next();

        String product_description_content();

        String product_size();

        String active();
    }

    @Source({"com/arcbees/gsss/mixin/client/mixins.gss",
            "css/colors.gss",
            "fonts/fonts.gss",
            "css/pages/product.gss"})
    Style style();
}
