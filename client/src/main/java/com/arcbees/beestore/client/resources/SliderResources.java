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

public interface SliderResources extends ClientBundle {
    interface Style extends CssResource {
        String productIcon();

        String devProducts_list_chosen();

        String devProducts_list_gae();

        String iconText();

        String gwtpIcon();

        String jukitoIcon();

        String arcbeesIcon();

        String gqueryIcon();

        String gsssIcon();

        String chosenIcon();

        String activeProduct();

        String gaeIcon();

        String devProducts_list();

        String brandPicker();

        String arcbees();
    }

    @Source({"com/arcbees/gsss/mixin/client/mixins.gss", "css/colors.gss", "css/slider.gss"})
    Style style();
}
