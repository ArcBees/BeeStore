
package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class PriceView extends ViewWithUiHandlers<PriceUiHandlers>
        implements PricePresenter.MyView {
    interface Binder extends UiBinder<Widget, PriceView> {
    }

    @UiField
    SpanElement brandName;
    @UiField
    SpanElement productName;
    @UiField
    DivElement price;

    @Inject
    PriceView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setProduct(ProductDto product) {
        brandName.setInnerText(product.getBrandName());
        productName.setInnerText(product.getProductName());
        price.setInnerText(product.getPrice() + "$");
    }
}
