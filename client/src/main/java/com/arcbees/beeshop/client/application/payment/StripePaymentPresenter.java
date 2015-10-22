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

import com.arcbees.beeshop.client.RestCallbackImpl;
import com.arcbees.beeshop.common.api.PaymentResource;
import com.arcbees.beeshop.common.dto.PaymentInfoDto;
import com.arcbees.stripe.client.CreditCard;
import com.arcbees.stripe.client.CreditCardResponseHandler;
import com.arcbees.stripe.client.Stripe;
import com.arcbees.stripe.client.jso.CreditCardResponse;
import com.google.gwt.core.client.Callback;
import com.google.gwt.query.client.GQuery;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class StripePaymentPresenter extends PresenterWidget<StripePaymentPresenter.MyView> implements StripePaymentUiHandlers {
    interface MyView extends View, HasUiHandlers<StripePaymentUiHandlers> {
    }

    private static final String STRIPE_PUBLIC_KEY = "pk_test_K9Oer0kZTz5qqJMxyCNSoIhr";

    private final Stripe stripe;
    private final ResourceDelegate<PaymentResource> paymentResource;

    @Inject
    StripePaymentPresenter(
            EventBus eventBus,
            MyView view,
            Stripe stripe,
            ResourceDelegate<PaymentResource> paymentResource) {
        super(eventBus, view);

        this.stripe = stripe;
        this.paymentResource = paymentResource;

        getView().setUiHandlers(this);
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

        stripe.setPublishableKey(STRIPE_PUBLIC_KEY);

        gogoStripe();
    }

    private void gogoStripe() {
        CreditCard creditCard = new CreditCard.Builder()
                .creditCardNumber("4000000000000127")
                .cvc("317")
                .expirationMonth(10)
                .expirationYear(2019)
                .name("Robert Bob")
                .build();

        stripe.getCreditCardToken(creditCard, new CreditCardResponseHandler() {
            @Override
            public void onCreditCardReceived(int status, CreditCardResponse creditCardResponse) {
                GQuery.console.log("status = ", status);
                GQuery.console.log("id = ", creditCardResponse.getId());
                GQuery.console.log("card = ", creditCardResponse.getCard());
                GQuery.console.log("created = ", creditCardResponse.getCreated());
                GQuery.console.log("live mode = ", creditCardResponse.getLiveMode());
                GQuery.console.log("object = ", creditCardResponse.getObject());
                GQuery.console.log("type = ", creditCardResponse.getType());
                GQuery.console.log("used = ", creditCardResponse.getUsed());

                paymentResource.withCallback(new RestCallbackImpl<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        GQuery.console.log("Success!");
                    }


                }).pay(new PaymentInfoDto(creditCardResponse.getId()));
            }
        });
    }
}
