package com.arcbees.beeshop.client.resources;

import javax.inject.Inject;

public class ResourceLoader {
    @Inject
    ResourceLoader(
            AppResources appResources,
            FontResources fontResources,
            ComponentsResources componentsResources,
            PageHomeResources pageHomeResources) {
        appResources.normalize().ensureInjected();
        appResources.style().ensureInjected();
        fontResources.icons().ensureInjected();
        appResources.grid().ensureInjected();
        pageHomeResources.style().ensureInjected();
        componentsResources.style().ensureInjected();
    }
}
