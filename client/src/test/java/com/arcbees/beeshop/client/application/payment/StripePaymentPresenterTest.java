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

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.arcbees.beeshop.client.Config;
import com.arcbees.stripe.client.Stripe;
import com.google.gwt.core.client.Callback;
import com.gwtplatform.mvp.client.AutobindDisable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class StripePaymentPresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            bind(AutobindDisable.class).toInstance(new AutobindDisable(true));
        }
    }

    @Inject
    private StripePaymentPresenter presenter;
    @Inject
    private StripePaymentPresenter.MyView view;
    @Inject
    private Stripe stripe;

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
}
