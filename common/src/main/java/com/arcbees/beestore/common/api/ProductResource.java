package com.arcbees.beestore.common.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.arcbees.beestore.common.dto.ProductDto;

import static com.arcbees.beestore.common.api.ApiPaths.PRODUCT;

@Path(PRODUCT)
public interface ProductResource {
    @GET
    @Path("/{id}")
    @Produces("application/json")
    ProductDto getProduct(@PathParam("id") int productId, @QueryParam("brand") String brandValue);
}
