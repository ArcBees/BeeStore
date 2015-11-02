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
