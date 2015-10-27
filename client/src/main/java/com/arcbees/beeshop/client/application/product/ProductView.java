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

import javax.inject.Inject;

import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.client.resources.Colors;
import com.arcbees.beeshop.client.resources.FontResources;
import com.arcbees.beeshop.client.resources.PageProductResources;
import com.arcbees.beeshop.client.resources.ProductBrandUtil;
import com.arcbees.beeshop.common.NameTokens;
import com.arcbees.beeshop.common.dto.Brand;
import com.arcbees.beeshop.common.dto.Product;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.arcbees.ui.ReplacePanel;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
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

    private final ProductBrandUtil productBrandUtil;
    private final PlaceManager placeManager;

    @Inject
    ProductView(
            Binder uiBinder,
            ProductBrandUtil productBrandUtil,
            PlaceManager placeManager) {
        this.productBrandUtil = productBrandUtil;
        this.placeManager = placeManager;

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
                getUiHandlers().onAddToCartButtonClicked();
            }
        });
    }

    @Override
    public void hideSharePanel() {
        shareButton.setClassName(font.icons().iconShare());
        $(sharePanel).hide();
    }

    @Override
    public void showSharePanel() {
        shareButton.setClassName(font.icons().iconClose() + " " + res.style().share_close());
        $(sharePanel).show();
    }

    @Override
    public void setProduct(ProductDto productDto) {
        Brand brand = productDto.getBrand();
        $(brandName).text(brand.getValue());

        Product product = productDto.getProduct();
        $(productName).text(product.getName());
        $(productDescription).text(product.getDescription());
        $(priceText).text(String.valueOf(product.getPrice() + " $"));

        productImage.setResource(productBrandUtil.getImage(product, brand));

        if (product.equals(Product.SHIRT)) {
            $(productImageDiv).css("background-color", Colors.getBrandColor(brand));
            $(productInfoDiv).css("background-color", Colors.getBrandColor(brand));
            $(sizeDiv).show();
        } else {
            $(productImageDiv).css("background-color", "");
            $(productInfoDiv).css("background-color", "");
            $(sizeDiv).hide();
        }

        setAnchorToProduct(previous, product.getPreviousProduct());
        setAnchorToProduct(next, product.getNextProduct());
    }

    private void setAnchorToProduct(AnchorElement anchor, Product product) {
        PlaceRequest request = new PlaceRequest.Builder(placeManager.getCurrentPlaceRequest())
                .with(NameTokens.PARAM_ID, String.valueOf(product.getId()))
                .build();

        anchor.setHref("#" + placeManager.buildHistoryToken(request));
    }
}
