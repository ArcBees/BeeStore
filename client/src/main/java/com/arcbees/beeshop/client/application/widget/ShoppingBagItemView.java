package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.client.application.ShoppingBagItem;
import com.arcbees.beeshop.common.dto.Product;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class ShoppingBagItemView extends ViewWithUiHandlers<ShoppingBagItemUiHandlers>
        implements ShoppingBagItemPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, ShoppingBagItemView> {
    }

    @UiField
    HeadingElement name;
    @UiField
    SpanElement color;
    @UiField
    SpanElement brand;
    @UiField
    SpanElement size;
    @UiField
    InputElement quantity;
    @UiField
    SpanElement price;
    @UiField
    Element delete;

    @Inject
    ShoppingBagItemView(
            Binder binder) {
        initWidget(binder.createAndBindUi(this));

        init();
    }

    private void init() {
        $(delete).click(new Function() {
            @Override
            public void f() {
                getUiHandlers().delete();
            }
        });
    }

    @Override
    public void setShoppingBagItem(ShoppingBagItem item) {
        Product product = item.getProductDto().getProduct();

        name.setInnerText(product.getName());
        color.setInnerText(product.getDescription());
        size.setInnerText(product.getSize());
        price.setInnerText(String.valueOf(product.getPrice()));
        quantity.setInnerText(String.valueOf(item.getQuantity()));
    }
}
