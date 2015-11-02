package com.arcbees.beestore.client.application.widget.sidepanel;

import javax.inject.Inject;

import com.arcbees.beestore.client.application.widget.sidepanel.cart.ShoppingCartPresenter;
import com.arcbees.beestore.client.application.widget.sidepanel.checkout.AddressPresenter;
import com.arcbees.beestore.client.application.widget.sidepanel.checkout.OrderPresenter;
import com.arcbees.beestore.client.application.widget.sidepanel.checkout.PaymentPresenter;
import com.arcbees.beestore.client.events.CheckoutContinueEvent;
import com.arcbees.beestore.client.events.CheckoutContinueEventHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.Slot;

public class SidePanelPresenter extends PresenterWidget<SidePanelPresenter.MyView>
        implements CheckoutContinueEventHandler {
    interface MyView extends View {
    }

    public static Slot SLOT_MAIN = new Slot();

    private final ShoppingCartPresenter shoppingCartPresenter;
    private final AddressPresenter addressPresenter;
    private final OrderPresenter orderPresenter;
    private final PaymentPresenter paymentPresenter;

    @Inject
    SidePanelPresenter(
            EventBus eventBus,
            MyView view,
            ShoppingCartPresenter shoppingCartPresenter,
            AddressPresenter addressPresenter,
            OrderPresenter orderPresenter,
            PaymentPresenter paymentPresenter) {
        super(eventBus, view);

        this.shoppingCartPresenter = shoppingCartPresenter;
        this.addressPresenter = addressPresenter;
        this.orderPresenter = orderPresenter;
        this.paymentPresenter = paymentPresenter;
    }

    @Override
    protected void onBind() {
        setInSlot(SLOT_MAIN, shoppingCartPresenter);

        addRegisteredHandler(CheckoutContinueEvent.TYPE, this);
    }

    @Override
    public void onCheckoutContinue(CheckoutContinueEvent event) {
        if (getChildren(SLOT_MAIN).contains(shoppingCartPresenter)) {
            setInSlot(SLOT_MAIN, addressPresenter);
        } else if (event.getSource() == addressPresenter) {
            addToSlot(SLOT_MAIN, orderPresenter);
        } else if (event.getSource() == orderPresenter) {
            addToSlot(SLOT_MAIN, paymentPresenter);
        }
    }
}
