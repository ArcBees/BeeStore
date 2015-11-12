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

package com.arcbees.beestore.client.application.home;

import javax.inject.Inject;

import com.arcbees.beestore.client.application.gin.TwitterCardProvider;
import com.arcbees.beestore.client.application.widget.brandpicker.HomeBrandPicker;
import com.arcbees.beestore.common.Seo;
import com.arcbees.seo.Image;
import com.arcbees.seo.SeoElements;
import com.arcbees.seo.TagsInjector;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import static com.arcbees.seo.widget.Image.MimeType.PNG;

public class HomeView extends ViewImpl implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    HTMLPanel secondaryProducts;
    @UiField
    HTMLPanel mainProducts;
    @UiField(provided = true)
    HomeBrandPicker brandPicker;

    private final TagsInjector tagsInjector;
    private final TwitterCardProvider twitterCardProvider;
    private final Seo seoMessages;

    @Inject
    HomeView(
            Binder uiBinder,
            HomeBrandPicker brandPicker,
            TagsInjector tagsInjector,
            TwitterCardProvider twitterCardProvider,
            Seo seoMessages) {

        this.brandPicker = brandPicker;
        this.tagsInjector = tagsInjector;
        this.twitterCardProvider = twitterCardProvider;
        this.seoMessages = seoMessages;

        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(HomePresenter.SLOT_MAIN_PRODUCTS, mainProducts);
        bindSlot(HomePresenter.SLOT_SECONDARY_PRODUCTS, secondaryProducts);
    }

    @Override
    public void updateSeo() {
        int imageHeight = Integer.parseInt(seoMessages.imageHeight());
        int imageWidth = Integer.parseInt(seoMessages.imageWidth());
        Image seoImage = new Image(seoMessages.image(), imageHeight, imageWidth, PNG);

        SeoElements seoElements = new SeoElements.Builder()
                .withTitle(seoMessages.title())
                .withImage(seoImage)
                .withKeywords(seoMessages.keywords())
                .withDescription(seoMessages.description())
                .withTwitterCard(twitterCardProvider.get())
                .build();

        tagsInjector.inject(seoElements);
    }
}
