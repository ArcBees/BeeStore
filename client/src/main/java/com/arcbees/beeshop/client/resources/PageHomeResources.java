package com.arcbees.beeshop.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface PageHomeResources extends ClientBundle {
    interface Style extends CssResource {
        String products_items();

        String products_list();

        String products_items__item();

        String product_items__img();

        String product_items__name();

        String product_items__price();

        String product_items__info();

        String welcome();

        String shopping_by_product();

        String main_products();

        String main_products__left();

        String main_products__right();
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

    @Source({"css/colors.gss", "css/pages/home.gss"})
    Style style();

}
