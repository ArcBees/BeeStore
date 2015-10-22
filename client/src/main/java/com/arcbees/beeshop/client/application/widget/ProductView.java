package com.arcbees.beeshop.client.application.widget;

import com.arcbees.beeshop.client.resources.AppResources;
import com.arcbees.beeshop.client.resources.PageHomeResources;
import com.arcbees.beeshop.client.resources.ProductBrandUtil;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.arcbees.ui.ReplacePanel;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class ProductView extends ViewImpl implements ProductPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, ProductView> {
    }

    @UiField
    HTMLPanel panel;
    @UiField
    PageHomeResources page;
    @UiField
    AppResources res;
    @UiField
    Image image;
    @UiField
    ReplacePanel pricePanel;

    private final ProductBrandUtil productBrandUtil;

    @Inject
    ProductView(
            Binder binder,
            ProductBrandUtil productBrandUtil) {
        this.productBrandUtil = productBrandUtil;

        initWidget(binder.createAndBindUi(this));

        bindSlot(ProductPresenter.SLOT_PRICE, pricePanel);
    }

    @Override
    public void setStyle(ProductWidgetType productWidgetType) {
        if (ProductWidgetType.MAIN_LEFT == productWidgetType) {
            panel.addStyleName(page.style().mainProducts_left());
            panel.addStyleName(res.style().mainProducts_bg());
        } else if (ProductWidgetType.MAIN_RIGHT == productWidgetType) {
            panel.addStyleName(page.style().mainProducts_right());
        } else {
            panel.setStyleName(page.style().itemForSale());
            image.setStyleName(page.style().itemForSale_img());
        }
    }

    @Override
    public void setProduct(ProductDto productDto) {
        image.setResource(productBrandUtil.getImage(productDto.getProduct(), productDto.getBrand()));
    }
}