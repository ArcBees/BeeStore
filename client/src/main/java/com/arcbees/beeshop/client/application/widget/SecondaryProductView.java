package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.client.resources.ProductBrandUtil;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.arcbees.ui.ReplacePanel;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class SecondaryProductView extends ViewImpl implements SecondaryProductPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, SecondaryProductView> {
    }

    @UiField
    Image image;
    @UiField
    ReplacePanel pricePanel;

    private final ProductBrandUtil productBrandUtil;

    @Inject
    SecondaryProductView(Binder binder,
            ProductBrandUtil productBrandUtil) {
        this.productBrandUtil = productBrandUtil;

        initWidget(binder.createAndBindUi(this));

        bindSlot(SecondaryProductPresenter.SLOT_PRICE, pricePanel);
    }

    @Override
    public void setProduct(ProductDto productDto) {
        image.setResource(productBrandUtil.getImage(productDto.getProduct(), productDto.getBrand()));
    }
}
