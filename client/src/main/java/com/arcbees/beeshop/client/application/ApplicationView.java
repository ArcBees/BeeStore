package com.arcbees.beeshop.client.application;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Label;
import com.gwtplatform.mvp.client.ViewImpl;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    @Inject
    public ApplicationView() {
        initWidget(new Label("Hello"));
    }
}
