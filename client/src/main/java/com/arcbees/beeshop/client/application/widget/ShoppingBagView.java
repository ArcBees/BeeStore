package com.arcbees.beeshop.client.application.widget;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class ShoppingBagView extends ViewWithUiHandlers<ShoppingBagUiHandlers>
        implements ShoppingBagPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, ShoppingBagView> {
    }

    @UiField
    SpanElement numberOfItems;
    @UiField
    HTMLPanel itemsContainer;

    @Inject
    ShoppingBagView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        bindSlot(ShoppingBagPresenter.SLOT_BAG_ITEM, itemsContainer);
    }

    @Override
    public void updateItemNumber(int number) {
        $(numberOfItems).text(String.valueOf(number));
    }
}
