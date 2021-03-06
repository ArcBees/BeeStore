/*
 * Copyright 2016 ArcBees Inc.
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

package com.arcbees.beestore.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class CheckoutEvent extends GwtEvent<CheckoutEventHandler> {
    public static final Type<CheckoutEventHandler> TYPE = new Type<>();

    private Status status;

    public enum Status {
        OPEN, CLOSE
    }

    public CheckoutEvent(Status status) {
        this.status = status;
    }

    public static void fire(HasHandlers source, Status status) {
        source.fireEvent(new CheckoutEvent(status));
    }

    @Override
    public Type<CheckoutEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CheckoutEventHandler handler) {
        handler.onCheckout(this);
    }

    public boolean isCheckoutOpen() {
        if (status == Status.OPEN) {
            return true;
        }

        return false;
    }
}
