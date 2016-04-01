package com.arcbees.beestore.client.application.widget.sidepanel.checkout;

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.beestore.client.application.CurrencyFormat;
import com.arcbees.beestore.client.application.CurrentOrder;
import com.arcbees.beestore.client.application.ShippingMethod;
import com.arcbees.beestore.client.events.CheckoutEvent;
import com.arcbees.beestore.client.events.EmptyCartEvent;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class OrderPresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            forceMock(CurrencyFormat.class);
        }
    }

    @Inject
    private OrderPresenter presenter;
    @Inject
    private CurrentOrder currentOrder;

    @Test
    public void onEmptyCart_shouldRemoveCartItemsPresenterFromSlot() throws Exception {
        presenter.onEmptyCart(mock(EmptyCartEvent.class));

        assertNull(presenter.getChild(OrderPresenter.SLOT_CART_ITEMS));
    }

    @Test
    public void setShippingMethod_shouldSetShippingMethodInCurrentOrder() throws Exception {
        ShippingMethod expectedMethod = ShippingMethod.STANDARD;
        presenter.setShippingMethod(expectedMethod);

        verify(currentOrder).setShippingMethod(expectedMethod);
    }

    @Test
    public void onCheckout_whenCheckoutIsClosed_shouldBeSetInSlot() throws Exception {
        CheckoutEvent event = new CheckoutEvent(CheckoutEvent.Status.CLOSE);

        presenter.onCheckout(event);

        assertNotNull(presenter.getChild(OrderPresenter.SLOT_CART_ITEMS));
    }

    @Test
    public void onCheckout_whenCheckoutIsOpen_shouldBeRemovedFromSlot() throws Exception {
        CheckoutEvent event = new CheckoutEvent(CheckoutEvent.Status.OPEN);

        presenter.onCheckout(event);

        assertNull(presenter.getChild(OrderPresenter.SLOT_CART_ITEMS));
    }
}