package com.arcbees.beeshop.client.application.widget.sidepanel;

import javax.inject.Inject;

import com.arcbees.beeshop.client.application.payment.PaymentPresenter;
import com.arcbees.beeshop.client.application.widget.sidepanel.cart.ShoppingBagPresenter;
import com.arcbees.beeshop.client.application.widget.sidepanel.checkout.AddressPresenter;
import com.arcbees.beeshop.client.application.widget.sidepanel.checkout.OrderPresenter;
import com.arcbees.beeshop.client.events.CheckoutClickEventHandler;
import com.arcbees.beeshop.client.events.CheckoutClickedEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.Slot;

public class SidePanelPresenter extends PresenterWidget<SidePanelPresenter.MyView>
        implements SidePanelUiHandlers, CheckoutClickEventHandler {
    interface MyView extends View, HasUiHandlers<SidePanelUiHandlers> {
    }

    public static Slot SLOT_MAIN = new Slot();

    private final ShoppingBagPresenter shoppingBagPresenter;
    private final AddressPresenter addressPresenter;
    private final OrderPresenter orderPresenter;
    private final PaymentPresenter paymentPresenter;

    @Inject
    SidePanelPresenter(
            EventBus eventBus,
            MyView view,
            ShoppingBagPresenter shoppingBagPresenter,
            AddressPresenter addressPresenter,
            OrderPresenter orderPresenter,
            PaymentPresenter paymentPresenter) {
        super(eventBus, view);

        this.shoppingBagPresenter = shoppingBagPresenter;
        this.addressPresenter = addressPresenter;
        this.orderPresenter = orderPresenter;
        this.paymentPresenter = paymentPresenter;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        setInSlot(SLOT_MAIN, shoppingBagPresenter);

        addRegisteredHandler(CheckoutClickedEvent.TYPE, this);
    }

    @Override
    public void onCheckout(CheckoutClickedEvent event) {
        clearSlot(SLOT_MAIN);

        addToSlot(SLOT_MAIN, addressPresenter);
    }
}
