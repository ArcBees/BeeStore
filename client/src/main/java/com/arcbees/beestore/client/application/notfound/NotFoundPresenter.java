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

package com.arcbees.beestore.client.application.notfound;

import com.arcbees.beestore.client.application.ApplicationPresenter;
import com.arcbees.beestore.client.events.PageScrollEvent;
import com.arcbees.beestore.common.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class NotFoundPresenter extends Presenter<NotFoundPresenter.MyView, NotFoundPresenter.MyProxy> {
    interface MyView extends View {
    }

    @ProxyStandard
    @NameToken(NameTokens.NOT_FOUND)
    interface MyProxy extends ProxyPlace<NotFoundPresenter> {
    }

    private static final boolean NOT_SCROLLABLE = false;
    private static final boolean SCROLLABLE = true;

    @Inject
    NotFoundPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    @Override
    protected void onReveal() {
        PageScrollEvent.fire(this, NOT_SCROLLABLE);
    }

    @Override
    protected void onHide() {
        PageScrollEvent.fire(this, SCROLLABLE);
    }
}
