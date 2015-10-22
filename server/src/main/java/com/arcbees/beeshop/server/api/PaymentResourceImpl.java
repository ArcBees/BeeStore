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

import com.arcbees.beeshop.common.api.PaymentResource;
import com.arcbees.beeshop.common.dto.PaymentInfoDto;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

public class PaymentResourceImpl implements PaymentResource {
    @Override
    public void pay(PaymentInfoDto paymentInfo) {
        RequestOptions requestOptions = (new RequestOptions.RequestOptionsBuilder()).setApiKey("PRIVATE_KEY").build();
        Map<String, Object> chargeMap = new HashMap<>();
        chargeMap.put("amount", 100);
        chargeMap.put("currency", "cad");
        chargeMap.put("source", paymentInfo.getCreditCardToken());

        try {
            Charge charge = Charge.create(chargeMap, requestOptions);
            System.out.println(charge);
        } catch (CardException e) {
            e.printStackTrace();
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
