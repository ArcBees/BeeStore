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

package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.client.events.BrandChangedEventHandler;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.gwt.query.client.GQuery;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
        implements BrandChangedEventHandler, NavigationHandler {
    @ProxyStandard
    @NoGatekeeper
    interface MyProxy extends Proxy<ApplicationPresenter> {
    }

    interface MyView extends View {
        void changeBrand(Brand brand);

        void updateNavigationHref();
    }

    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    @Inject
    ApplicationPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        GQuery.console.log("Changing request");
    }

    @ProxyEvent
    @Override
    public void onBrandChanged(BrandChangedEvent event) {
        getView().changeBrand(event.getBrand());
    }

    @Override
    public void onNavigation(NavigationEvent navigationEvent) {
        getView().updateNavigationHref();
    }

    @Override
    protected void onBind() {
        addVisibleHandler(BrandChangedEvent.TYPE, this);
        addVisibleHandler(NavigationEvent.getType(), this);
    }
}
