package com.arcbees.beeshop.client.application.widget.sidepanel;

import javax.inject.Inject;

import com.arcbees.beeshop.client.application.widget.sidepanel.cart.ShoppingBagPresenter;
import com.arcbees.beeshop.client.application.widget.sidepanel.checkout.AddressPresenter;
import com.arcbees.beeshop.client.application.widget.sidepanel.checkout.OrderPresenter;
import com.arcbees.beeshop.client.application.widget.sidepanel.checkout.PaymentPresenter;
import com.arcbees.beeshop.client.events.CheckoutContinueEvent;
import com.arcbees.beeshop.client.events.CheckoutContinueEventHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.Slot;

public class SidePanelPresenter extends PresenterWidget<SidePanelPresenter.MyView>
        implements CheckoutContinueEventHandler {
    interface MyView extends View {
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
    }

    @Override
    protected void onBind() {
        setInSlot(SLOT_MAIN, shoppingBagPresenter);

        addRegisteredHandler(CheckoutContinueEvent.TYPE, this);
    }

    @Override
    public void onCheckoutContinue(CheckoutContinueEvent event) {
        if (getChildren(SLOT_MAIN).contains(shoppingBagPresenter)) {
            setInSlot(SLOT_MAIN, addressPresenter);
        } else if (event.getSource() == addressPresenter) {
            addToSlot(SLOT_MAIN, orderPresenter);
        } else if (event.getSource() == orderPresenter) {
            addToSlot(SLOT_MAIN, paymentPresenter);
        }
    }
}
