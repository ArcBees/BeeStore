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

package com.arcbees.beestore.client.application.gin;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.UmbrellaException;

public class SuperDevModeUncaughtExceptionHandler implements GWT.UncaughtExceptionHandler {
    @Override
    public void onUncaughtException(Throwable t) {
        logException(t, false);
    }

    private void logException(Throwable t, boolean isCause) {
        String msg = t.toString();
        if (isCause) {
            msg = "caused by: " + msg;
        }
        groupStart(msg);
        log(t);
        if (t instanceof UmbrellaException) {
            UmbrellaException umbrella = (UmbrellaException) t;
            for (Throwable cause : umbrella.getCauses()) {
                logException(cause, true);
            }
        } else if (t.getCause() != null) {
            logException(t.getCause(), true);
        }
        groupEnd();
    }

    private native void groupStart(String msg) /*-{
        var groupStart = console.groupCollapsed || console.group || console.error || console.log;
        groupStart.call(console, msg);
    }-*/;

    private native void groupEnd() /*-{
        var groupEnd = console.groupEnd || function () {
            };
        groupEnd.call(console);
    }-*/;

    private native void log(Throwable t) /*-{
        var logError = console.error || console.log;
        var backingError = t.__gwt$backingJsError;
        logError.call(console, backingError && backingError.stack);
    }-*/;
}
