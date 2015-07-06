package com.arcbees.beeshop.client.resources;

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

        String footer_contact__arcbees();

        String footer_contact__social();

        String wrapper();

        String header_top();

        String header_bottom();

        String header_bottom__right();

        String wishlist();

        String cart();

        String beestore_logo();

        String mobile_display();

        String language();

        String footer_menu();

        String footer_logo_arcbees();

        String clearfix();

        String case_study();

        String stripe_light();

        String txt_center();

        String active();

        String header_developersPlatform();

        String backTop();
    }

    @Source("img/Beestore-logo.png")
    ImageResource beestoreLogo();

    @Source("css/normalize.gss")
    Normalize normalize();

    @Source({"com/arcbees/gsss/mixin/client/mixins.gss", "css/colors.gss", "css/layout.gss", "css/style.gss"})
    Style style();

    @Source({"com/arcbees/gsss/grid/client/gridsettings.gss", "com/arcbees/gsss/grid/client/grid.gss"})
    GridResources.Grid grid();
}
