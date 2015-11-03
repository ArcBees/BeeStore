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
import com.arcbees.beestore.common.dto.Size;
import com.google.gwt.i18n.client.Messages;

public interface AppMessages extends Messages {
    String brandName(@Select Brand brand);

    String productName(@Select ProductType productType);

    String size(@Select Size size);

    String itemColor(@Select ProductType productType, @Select Brand brand);

    String logoColor(@Select ProductType productType, @Select Brand brand);
}
