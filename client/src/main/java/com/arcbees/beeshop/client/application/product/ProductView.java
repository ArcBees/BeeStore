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

package com.arcbees.beeshop.client.application.product;

import java.util.HashMap;

import javax.inject.Inject;

import com.arcbees.beeshop.client.application.widget.brandpicker.BrandPicker;
import com.arcbees.beeshop.client.resources.AppMessages;
import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.client.resources.Colors;
import com.arcbees.beeshop.client.resources.FontResources;
import com.arcbees.beeshop.client.resources.PageProductResources;
import com.arcbees.beeshop.client.resources.ProductBrandUtil;
import com.arcbees.beeshop.client.resources.ProductMessages;
import com.arcbees.beeshop.common.NameTokens;
import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.arcbees.beeshop.common.dto.ProductType;
import com.arcbees.beeshop.common.dto.Size;
import com.arcbees.ui.ReplacePanel;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static com.arcbees.beeshop.client.application.product.ProductPresenter.SLOT_SHARE_PANEL;
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
    LIElement smallSizeListItem;
    @UiField
    LIElement mediumSizeListItem;
    @UiField
    LIElement largeSizeListItem;
    @UiField
    LIElement xlargeSizeListItem;
    @UiField
    AnchorElement smallAnchor;
    @UiField
    AnchorElement mediumAnchor;
    @UiField
    AnchorElement largeAnchor;
    @UiField
    AnchorElement xLargeAnchor;
    @UiField
    InputElement quantity;

    private final AppMessages appMessages;
    private final ProductBrandUtil productBrandUtil;
    private final PlaceManager placeManager;
    private final ProductMessages productMessages;
    private final HashMap<Size, LIElement> sizeListItemAssociation;
    private final HashMap<Size, AnchorElement> sizeAnchorAssociation;

    @Inject
    ProductView(
            Binder uiBinder,
            ProductBrandUtil productBrandUtil,
            PlaceManager placeManager,
            BrandPicker brandPicker,
            AppMessages appMessages,
            ProductMessages productMessages) {
        this.productBrandUtil = productBrandUtil;
        this.placeManager = placeManager;
        this.brandPicker = brandPicker;
        this.appMessages = appMessages;
        this.productMessages = productMessages;
        sizeListItemAssociation = new HashMap<>();
        sizeAnchorAssociation = new HashMap<>();

        initWidget(uiBinder.createAndBindUi(this));

        sizeListItemAssociation.put(Size.SMALL, smallSizeListItem);
        sizeListItemAssociation.put(Size.MEDIUM, mediumSizeListItem);
        sizeListItemAssociation.put(Size.LARGE, largeSizeListItem);
        sizeListItemAssociation.put(Size.XLARGE, xlargeSizeListItem);

        sizeAnchorAssociation.put(Size.SMALL, smallAnchor);
        sizeAnchorAssociation.put(Size.MEDIUM, mediumAnchor);
        sizeAnchorAssociation.put(Size.LARGE, largeAnchor);
        sizeAnchorAssociation.put(Size.XLARGE, xLargeAnchor);

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
                int itemQuantity = Integer.parseInt(quantity.getValue());
                getUiHandlers().onAddToCartButtonClicked(itemQuantity);
            }
        });
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

        productImage.setResource(productBrandUtil.getImage(productType, brand));

        if (productType.equals(ProductType.SHIRT)) {
            $(productImageDiv).css("background-color", Colors.getBrandColor(brand));
            $(productInfoDiv).css("background-color", Colors.getBrandColor(brand));
            $(sizeDiv).show();

            toggleActiveShirtSizeIcon(productDto);

            for (Size size : sizeAnchorAssociation.keySet()) {
                setAnchorToSizeIcon(size, sizeAnchorAssociation.get(size));
            }
        } else {
            $(productImageDiv).css("background-color", "");
            $(productInfoDiv).css("background-color", "");
            $(sizeDiv).hide();
        }

        setAnchorToProduct(previous, productType.getPreviousProduct());
        setAnchorToProduct(next, productType.getNextProduct());

        for (Size size : sizeAnchorAssociation.keySet()) {
            setAnchorToSizeIcon(size, sizeAnchorAssociation.get(size));
        }

        brandPicker.updateAnchors();
    }

    private void setAnchorToSizeIcon(Size size, AnchorElement anchor) {
        PlaceRequest request = new PlaceRequest.Builder(placeManager.getCurrentPlaceRequest())
                .with(NameTokens.PARAM_SIZE, size.getValue())
                .build();

        anchor.setHref("#" + placeManager.buildHistoryToken(request));
    }

    private void toggleActiveShirtSizeIcon(ProductDto productDto) {
        for (LIElement element : sizeListItemAssociation.values()) {
            $(element).removeClass(page.style().active());
        }

        LIElement activeLIElement = sizeListItemAssociation.get(productDto.getProduct().getSize());
        $(activeLIElement).addClass(page.style().active());
    }

    private void setAnchorToProduct(AnchorElement anchor, ProductType productType) {
        PlaceRequest request = new PlaceRequest.Builder(placeManager.getCurrentPlaceRequest())
                .with(NameTokens.PARAM_ID, String.valueOf(productType.getId()))
                .build();

        anchor.setHref("#" + placeManager.buildHistoryToken(request));
    }
}
