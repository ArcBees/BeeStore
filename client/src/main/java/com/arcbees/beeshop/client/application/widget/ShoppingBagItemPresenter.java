package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.client.application.CurrentShoppingBag;
import com.arcbees.beeshop.client.application.ShoppingBagItem;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class ShoppingBagItemPresenter extends PresenterWidget<ShoppingBagItemPresenter.MyView>
        implements ShoppingBagItemUiHandlers {
    interface MyView extends View, HasUiHandlers<ShoppingBagItemUiHandlers> {
        void setShoppingBagItem(ShoppingBagItem item);
    }

    private ShoppingBagItem item;
    private CurrentShoppingBag currentShoppingBag;

    @Inject
    ShoppingBagItemPresenter(
            EventBus eventBus,
            MyView view,
            @Assisted ShoppingBagItem item,
            CurrentShoppingBag currentShoppingBag) {
        super(eventBus, view);
        this.currentShoppingBag = currentShoppingBag;

        getView().setUiHandlers(this);
        this.item = item;
    }

    @Override
    public void delete() {
        currentShoppingBag.removeItem(item);

        this.removeFromParentSlot();
    }

    @Override
    protected void onReveal() {
        getView().setShoppingBagItem(item);
    }
}
