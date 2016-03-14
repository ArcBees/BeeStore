/*
 * Copyright 2016 ArcBees Inc.
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

package com.arcbees.beestore.common.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.arcbees.beestore.common.dto.ProductDto;

import static com.arcbees.beestore.common.api.ApiPaths.PRODUCT;

@Path(PRODUCT)
@Produces(MediaType.APPLICATION_JSON)
public interface ProductResource {
    @GET
    @Path(ApiPaths.ID)
    ProductDto getProduct(@PathParam(ApiParameters.ID) int productId,
                          @QueryParam(ApiParameters.BRAND) String brandValue,
                          @QueryParam(ApiParameters.SIZE) String size);

    @GET
    List<ProductDto> getProductsByBrand(@QueryParam(ApiParameters.BRAND) String brand);
}