/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.beeshop.client.application.widget.sidepanel.checkout;

import javax.inject.Inject;
import javax.ws.rs.core.Response.Status;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.arcbees.beeshop.client.Config;
import com.arcbees.beeshop.common.api.PaymentResource;
import com.arcbees.beeshop.common.dto.PaymentInfoDto;
import com.arcbees.stripe.client.CreditCard;
import com.arcbees.stripe.client.CreditCardResponseHandler;
import com.arcbees.stripe.client.Stripe;
import com.arcbees.stripe.client.jso.CreditCardResponse;
import com.google.gwt.core.client.Callback;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.AutobindDisable;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import static javax.ws.rs.core.Response.Status.PAYMENT_REQUIRED;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import static com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils.givenDelegate;

@RunWith(JukitoRunner.class)
public class PaymentPresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            bind(AutobindDisable.class).toInstance(new AutobindDisable(true));
        }
    }

    private static final String NAME = "name";
    private static final String CARD_NUMBER = "4242424242424242";
    private static final String CVS = "314";
    private static final int EXP_MONTH = 10;
    private static final int EXP_YEAR = 2020;

    @Inject
    private PaymentPresenter presenter;
    @Inject
    private PaymentPresenter.MyView view;
    @Inject
    private ResourceDelegate<PaymentResource> paymentDelegate;
    @Inject
    private PaymentResource paymentResource;
    @Inject
    private Stripe stripe;

    private CreditCardResponse creditCardResponse = mock(CreditCardResponse.class);

    @Before
    public void setUp() {
        givenDelegate(paymentDelegate).useResource(paymentResource);
    }

    @Test
    public void onBind_givenInjectStripeSucceeds_setPublishableKeyAndEnablesPaymentInView(Config config) {
        String publishableKey = "pk_hello";
        given(config.stripePublicKey()).willReturn(publishableKey);
        givenStripeInjectWillCallOnSuccess();

        presenter.onBind();

        verify(stripe).setPublishableKey(publishableKey);
        verify(view).enablePaymentSubmit();
    }

    private void givenStripeInjectWillCallOnSuccess() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback callback = (Callback) invocation.getArguments()[0];
                callback.onSuccess(null);
                return null;
            }
        }).when(stripe).inject(any(Callback.class));
    }

    @Test
    public void onBind_givenInjectStripeFails_showsErrorMessageAndDoesNotEnablePaymentInView() {
        givenStripeInjectWillCallOnFailure();

        presenter.onBind();

        verify(view).showErrorMessage(anyString());
        verify(view, never()).enablePaymentSubmit();
    }

    private void givenStripeInjectWillCallOnFailure() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback callback = (Callback) invocation.getArguments()[0];
                callback.onFailure(null);
                return null;
            }
        }).when(stripe).inject(any(Callback.class));
    }

    @Test
    public void onSubmit_disablesPaymentSubmitInViewToAvoidSubmittingPaymentMultipleTimes() {
        presenter.onSubmit(NAME, CARD_NUMBER, CVS, EXP_MONTH, EXP_YEAR);

        verify(view).disablePaymentSubmit();
    }

    @Test
    public void onSubmit_givenPaymentStatusIsPaymentRequired_showsErrorMessageAndDoesNotPay() {
        givenGetCreditCardTokenReturnsStatus(PAYMENT_REQUIRED);

        presenter.onSubmit(NAME, CARD_NUMBER, CVS, EXP_MONTH, EXP_YEAR);

        verify(view).showErrorMessage(anyString());
        verify(paymentResource, never()).pay(any(PaymentInfoDto.class));
    }

    @Test
    public void onSubmit_givenPaymentStatusIsNotOk_showsErrorMessageAndDoesNotPay() {
        givenGetCreditCardTokenReturnsStatus(FORBIDDEN);

        presenter.onSubmit(NAME, CARD_NUMBER, CVS, EXP_MONTH, EXP_YEAR);

        verify(view).showErrorMessage(anyString());
        verify(paymentResource, never()).pay(any(PaymentInfoDto.class));
    }

    // TODO Test StatusOK.

    private void givenGetCreditCardTokenReturnsStatus(final Status status) {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                CreditCardResponseHandler callback = (CreditCardResponseHandler) invocation.getArguments()[1];
                callback.onCreditCardReceived(status.getStatusCode(), creditCardResponse);
                return null;
            }
        }).when(stripe).getCreditCardToken(any(CreditCard.class), any(CreditCardResponseHandler.class));
    }
}
