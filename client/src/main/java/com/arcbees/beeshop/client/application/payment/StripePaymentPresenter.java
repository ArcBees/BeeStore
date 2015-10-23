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

package com.arcbees.beeshop.client.application.payment;

import com.arcbees.beeshop.client.Config;
import com.arcbees.beeshop.client.RestCallbackImpl;
import com.arcbees.beeshop.common.api.PaymentResource;
import com.arcbees.beeshop.common.dto.PaymentInfoDto;
import com.arcbees.stripe.client.CreditCard;
import com.arcbees.stripe.client.CreditCardResponseHandler;
import com.arcbees.stripe.client.Stripe;
import com.arcbees.stripe.client.jso.CreditCardResponse;
import com.google.gwt.core.client.Callback;
import com.google.gwt.http.client.Response;
import com.google.gwt.query.client.GQuery;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import static com.google.gwt.http.client.Response.SC_OK;
import static com.google.gwt.http.client.Response.SC_PAYMENT_REQUIRED;

public class StripePaymentPresenter extends PresenterWidget<StripePaymentPresenter.MyView>
        implements StripePaymentUiHandlers {
    interface MyView extends View, HasUiHandlers<StripePaymentUiHandlers> {
        void showErrorMessage(String message);

        void showSuccessMessage(String message);
    }

    private final String stripePublicKey;
    private final Stripe stripe;
    private final ResourceDelegate<PaymentResource> paymentResource;

    @Inject
    StripePaymentPresenter(
            EventBus eventBus,
            MyView view,
            Config config,
            Stripe stripe,
            ResourceDelegate<PaymentResource> paymentResource) {
        super(eventBus, view);

        this.stripePublicKey = config.stripePublicKey();
        this.stripe = stripe;
        this.paymentResource = paymentResource;

        getView().setUiHandlers(this);
    }

    @Override
    public void onSubmit(String name, String number, String cvs, int expMonth, int expYear) {
        final CreditCard creditCard = new CreditCard.Builder()
                .name(name)
                .creditCardNumber(number)
                .cvc(cvs)
                .expirationMonth(expMonth)
                .expirationYear(expYear)
                .build();

        stripe.getCreditCardToken(creditCard, new CreditCardResponseHandler() {
            @Override
            public void onCreditCardReceived(int status, CreditCardResponse creditCardResponse) {
                if (status == SC_PAYMENT_REQUIRED) {
                    getView().showErrorMessage("An error occurred. Please verify your credit card details.");
                } else if (status != SC_OK) {
                    getView().showErrorMessage("An error occurred.");
                } else {
                    PaymentInfoDto paymentInfo = new PaymentInfoDto(creditCardResponse.getId());
                    pay(paymentInfo);
                }
            }
        });
    }

    private void pay(PaymentInfoDto paymentInfo) {
        paymentResource.withCallback(new RestCallbackImpl<Void>() {
            @Override
            public void onSuccess(Void result) {
                getView().showSuccessMessage("Success!");
            }

            @Override
            public void onError(Response response) {
                getView().showErrorMessage(response.getText());
            }
        }).pay(paymentInfo);
    }

    @Override
    protected void onBind() {
        stripe.inject(new Callback<Void, Exception>() {
            @Override
            public void onFailure(Exception e) {
                GQuery.console.error("Failed to inject stripe");
            }

            @Override
            public void onSuccess(Void aVoid) {
                onStripeInjected();
            }
        });
    }

    private void onStripeInjected() {
        GQuery.console.log("Stripe injected");

        stripe.setPublishableKey(stripePublicKey);
    }
}
