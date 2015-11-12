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

import javax.inject.Inject;

import com.arcbees.beestore.client.application.gin.TwitterCardProvider;
import com.arcbees.beestore.client.application.widget.brandpicker.BrandPicker;
import com.arcbees.beestore.client.resources.AppMessages;
import com.arcbees.beestore.client.resources.AppResources;
import com.arcbees.beestore.client.resources.Colors;
import com.arcbees.beestore.client.resources.FontResources;
import com.arcbees.beestore.client.resources.PageProductResources;
import com.arcbees.beestore.client.resources.ProductBrandUtil;
import com.arcbees.beestore.client.resources.ProductMessages;
import com.arcbees.beestore.client.resources.SeoImages;
import com.arcbees.beestore.common.NameTokens;
import com.arcbees.beestore.common.dto.Brand;
import com.arcbees.beestore.common.dto.ProductDto;
import com.arcbees.beestore.common.dto.ProductType;
import com.arcbees.seo.SeoElements;
import com.arcbees.seo.TagsInjector;
import com.arcbees.ui.ReplacePanel;
import com.google.common.base.Strings;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static com.arcbees.beestore.client.application.product.ProductPresenter.SLOT_SHARE_PANEL;
import static com.google.gwt.query.client.GQuery.$;

public class ProductView extends ViewWithUiHandlers<ProductPresenterUiHandlers> implements ProductPresenter.MyView {
    interface Binder extends UiBinder<Widget, ProductView> {
    }

    @UiField
    FontResources font;
    @UiField
    AppResources res;
    @UiField
    Element shareButton;
    @UiField
    ReplacePanel sharePanel;
    @UiField
    ButtonElement addToCart;
    @UiField
    SpanElement brandName;
    @UiField
    SpanElement productName;
    @UiField
    ParagraphElement productDescription;
    @UiField
    SpanElement priceText;
    @UiField
    Image productImage;
    @UiField
    AnchorElement previous;
    @UiField
    AnchorElement next;
    @UiField
    DivElement productImageDiv;
    @UiField
    PageProductResources page;
    @UiField
    DivElement productInfoDiv;
    @UiField
    DivElement sizeDiv;
    @UiField(provided = true)
    BrandPicker brandPicker;
    @UiField
    UListElement sizes;
    @UiField
    InputElement quantity;

    private final AppMessages appMessages;
    private final ProductBrandUtil productBrandUtil;
    private final PlaceManager placeManager;
    private final ProductMessages productMessages;
    private final TagsInjector tagsInjector;
    private final SeoImages seoImages;
    private final TwitterCardProvider twitterCardProvider;

    @Inject
    ProductView(
            Binder uiBinder,
            ProductBrandUtil productBrandUtil,
            PlaceManager placeManager,
            BrandPicker brandPicker,
            AppMessages appMessages,
            ProductMessages productMessages,
            TagsInjector tagsInjector,
            SeoImages seoImages,
            TwitterCardProvider twitterCardProvider) {
        this.productBrandUtil = productBrandUtil;
        this.placeManager = placeManager;
        this.brandPicker = brandPicker;
        this.appMessages = appMessages;
        this.productMessages = productMessages;
        this.tagsInjector = tagsInjector;
        this.seoImages = seoImages;
        this.twitterCardProvider = twitterCardProvider;

        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(SLOT_SHARE_PANEL, sharePanel);

        bind();
    }

    private void bind() {
        $(shareButton).click(new Function() {
            @Override
            public void f() {
                getUiHandlers().onShareButtonClicked();
            }
        });

        $(addToCart).click(new Function() {
            @Override
            public void f() {
                int quantity = getQuantity();
                if (quantity > 0) {
                    getUiHandlers().onAddToCartButtonClicked(quantity);
                }
            }
        });
    }

    private int getQuantity() {
        int quantity = 0;
        if (!Strings.isNullOrEmpty(this.quantity.getValue())) {
            quantity = Integer.parseInt(this.quantity.getValue());
        }

        return quantity;
    }

    @Override
    public void hideSharePanel() {
        shareButton.setClassName(font.icons().iconShare());
        $(sharePanel).click().removeClass(res.style().animateShare());
    }

    @Override
    public void showSharePanel() {
        shareButton.setClassName(font.icons().iconClose() + " " + res.style().share_close());
        $(sharePanel).click().removeClass(res.style().share_icons__hidden());
        $(sharePanel).click().addClass(res.style().animateShare());
    }

    @Override
    public void setProduct(ProductDto productDto) {
        Brand brand = productDto.getBrand();
        ProductType productType = productDto.getProductType();

        $(brandName).text(appMessages.brandName(brand));
        $(productName).text(appMessages.productName(productType));
        $(productDescription).text(productMessages.productDescription(productType));
        $(priceText).text(String.valueOf(productType.getPrice() + " $"));

        productImage.setResource(productBrandUtil.getBigImage(productType, brand));

        if (productType.equals(ProductType.SHIRT)) {
            $(productImageDiv).css("background-color", Colors.getBrandColor(brand));
            $(productInfoDiv).css("background-color", Colors.getBrandColor(brand));
            $(sizeDiv).show();

            toggleActiveShirtSizeIcon(productDto);

            setTargetToSizeAnchors();
        } else {
            $(productImageDiv).css("background-color", "");
            $(productInfoDiv).css("background-color", "");
            $(sizeDiv).hide();
        }

        setTargetToNavigationAnchor(previous, productType.getPreviousProduct());
        setTargetToNavigationAnchor(next, productType.getNextProduct());

        brandPicker.updateAnchors();
    }

    @Override
    public void setSeoElements(ProductDto productDto) {
        ProductType productType = productDto.getProductType();
        Brand brand = productDto.getBrand();

        String title = "BeeStore - " + appMessages.productName(productType);
        String description = appMessages.itemColor(productType, brand);

        SeoElements seoElements = new SeoElements.Builder()
                .withTitle(title)
                .withTwitterCard(twitterCardProvider.get())
                .withDescription(description)
                .withImage(seoImages.getImage(productType, brand))
                .build();

        tagsInjector.inject(seoElements);
    }

    private void setTargetToSizeAnchors() {
        $("a", sizes).each(new Function() {
            @Override
            public void f(Element e) {
                String size = $(e).attr("data-size");

                PlaceRequest request = new PlaceRequest.Builder(placeManager.getCurrentPlaceRequest())
                        .with(NameTokens.PARAM_SIZE, size)
                        .build();

                $(e).attr("href", "#" + placeManager.buildHistoryToken(request));
            }
        });
    }

    private void toggleActiveShirtSizeIcon(ProductDto productDto) {
        $("li", sizes).removeClass(page.style().active());

        $("li[data-size='" + productDto.getProduct().getSize().getValue() + "']", sizes).addClass(page.style().active());
    }

    private void setTargetToNavigationAnchor(AnchorElement anchor, ProductType productType) {
        PlaceRequest request = new PlaceRequest.Builder(placeManager.getCurrentPlaceRequest())
                .with(NameTokens.PARAM_ID, String.valueOf(productType.getId()))
                .build();

        anchor.setHref("#" + placeManager.buildHistoryToken(request));
    }
}
