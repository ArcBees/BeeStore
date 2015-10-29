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

package com.arcbees.beeshop.server.api;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.arcbees.beeshop.common.api.PaymentResource;
import com.arcbees.beeshop.common.dto.PaymentInfoDto;
import com.arcbees.beeshop.server.Config;
import com.arcbees.beeshop.server.exception.CreditCardException;
import com.arcbees.beeshop.server.exception.InternalServerErrorException;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

public class PaymentResourceImpl implements PaymentResource {
    private final RequestOptions requestOptions;

    @Inject
    public PaymentResourceImpl(
            Config config) {
        this.requestOptions = new RequestOptions.RequestOptionsBuilder()
                .setApiKey(config.stripePrivateKey())
                .build();
    }

    @Override
    public void pay(PaymentInfoDto paymentInfo) {
        Map<String, Object> chargeMap = new HashMap<>();
        chargeMap.put("amount", 100);
        chargeMap.put("currency", "cad");
        chargeMap.put("source", paymentInfo.getCreditCardToken());

        try {
            Charge charge = Charge.create(chargeMap, requestOptions);
        } catch (CardException e) {
            throw new CreditCardException(e.getMessage());
        } catch (StripeException e) {
            throw new InternalServerErrorException("An error occurred on the server. Please try again later.");
        }
    }
}
