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

package com.arcbees.beestore.client.application.product;

import com.arcbees.beestore.client.resources.SocialSharingUrls;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class SharePanelView extends ViewImpl implements SharePanelPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, SharePanelView> {
    }

    private final SocialSharingUrls socialSharingUrls;

    @UiField
    AnchorElement facebook;
    @UiField
    AnchorElement pinterest;
    @UiField
    AnchorElement googleplus;
    @UiField
    AnchorElement twitter;
    @UiField
    AnchorElement tumblr;
    @UiField
    AnchorElement mail;

    @Inject
    SharePanelView(
            Binder binder,
            SocialSharingUrls socialSharingUrls) {
        this.socialSharingUrls = socialSharingUrls;

        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void updateShareUrls(String urlToShare) {
        facebook.setHref(socialSharingUrls.facebook(urlToShare));
        pinterest.setHref(socialSharingUrls.pinterest(urlToShare));
        googleplus.setHref(socialSharingUrls.googlePlus(urlToShare));
        twitter.setHref(socialSharingUrls.twitter(urlToShare));
        tumblr.setHref(socialSharingUrls.tumblr(urlToShare));
        mail.setHref(socialSharingUrls.email(urlToShare));
    }
}
