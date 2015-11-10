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

package com.arcbees.beestore.client.application.widget.sidepanel;

import com.arcbees.beestore.client.resources.AppResources;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class SidePanelView extends ViewImpl implements SidePanelPresenter.MyView {
    interface Binder extends UiBinder<Widget, SidePanelView> {
    }

    @UiField
    AppResources res;
    @UiField
    HTMLPanel main;

    @Inject
    SidePanelView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        bindSlot(SidePanelPresenter.SLOT_MAIN, main);
    }
}
