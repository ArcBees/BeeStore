package com.arcbees.beeshop.client.application.widget.sidepanel;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class SidePanelView extends ViewWithUiHandlers<SidePanelUiHandlers>
        implements SidePanelPresenter.MyView {
    interface Binder extends UiBinder<Widget, SidePanelView> {
    }

    @UiField
    HTMLPanel main;

    @Inject
    SidePanelView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        bindSlot(SidePanelPresenter.SLOT_MAIN, main);
    }
}
