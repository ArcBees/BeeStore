package com.arcbees.beeshop.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface BrandChangedEventHandler extends EventHandler {
    void onBrandChanged(BrandChangedEvent event);
}
