package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.client.application.ShoppingBagItem;
import com.arcbees.beeshop.client.application.CurrentShoppingBag;
import com.arcbees.beeshop.client.events.ShoppingBagChangedEvent;
import com.arcbees.beeshop.client.events.ShoppingBagChangedEventHandler;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.Slot;

public class ShoppingBagPresenter extends PresenterWidget<ShoppingBagPresenter.MyView>
        implements ShoppingBagUiHandlers, ShoppingBagChangedEventHandler {
    interface MyView extends View, HasUiHandlers<ShoppingBagUiHandlers> {
        void updateItemNumber(int Number);
    }

    static final Slot<ShoppingBagItemPresenter> SLOT_BAG_ITEM = new Slot<>();

    private ShoppingBagItemFactory shoppingBagItemFactory;
    private CurrentShoppingBag currentShoppingBag;

    @Inject
    ShoppingBagPresenter(
            EventBus eventBus,
            MyView view,
            ShoppingBagItemFactory shoppingBagItemFactory,
            CurrentShoppingBag currentShoppingBag) {
        super(eventBus, view);

        this.shoppingBagItemFactory = shoppingBagItemFactory;
        this.currentShoppingBag = currentShoppingBag;

        getView().setUiHandlers(this);
    }

    @Override
    public void onShoppingBagChanged(ShoppingBagChangedEvent event) {
        getView().updateItemNumber(currentShoppingBag.getSize());

        if (!event.isRemoved()) {
            addToSlot(SLOT_BAG_ITEM, shoppingBagItemFactory.create(event.getItem()));
        }
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ShoppingBagChangedEvent.TYPE, this);
    }
}
