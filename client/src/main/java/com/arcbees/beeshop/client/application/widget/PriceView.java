package com.arcbees.beeshop.client.application.widget;

import static com.google.gwt.query.client.GQuery.$;

import com.arcbees.beeshop.client.NameTokens;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

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
    @UiField
    AnchorElement shopView;
    @UiField
    AnchorElement priceView;

    private final TokenFormatter tokenFormatter;

    @Inject
    PriceView(
            Binder binder,
            TokenFormatter tokenFormatter) {
        this.tokenFormatter = tokenFormatter;
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setProduct(ProductDto product) {
        brandName.setInnerText(product.getBrandName());
        productName.setInnerText(product.getProductName());
        price.setInnerText(product.getPrice() + "$");

        buildAnchorUri(product);
    }

    private void buildAnchorUri(ProductDto product) {
        String productId = String.valueOf(product.getId());

        PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.PRODUCTS)
                .with("productId", productId)
                .build();

        String token = tokenFormatter.toPlaceToken(request);
        shopView.setHref("#" + token);
    }

    @Override
    protected void onAttach() {
        setShopViewVisible(false);

        $(asWidget()).mouseover(new Function() {
            @Override
            public void f() {
                setShopViewVisible(true);
            }
        });

        $(asWidget()).mouseout(new Function() {
            @Override
            public void f() {
                setShopViewVisible(false);
            }
        });
    }

    private void setShopViewVisible(boolean visible) {
        if (visible) {
            $(shopView).show();
            $(priceView).hide();
        } else {
            $(shopView).hide();
            $(priceView).show();
        }
    }
}
