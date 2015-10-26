package com.arcbees.beeshop.client.application.widget;

import static com.google.gwt.query.client.GQuery.$;

import com.arcbees.beeshop.client.application.LocaleHelper;
import com.arcbees.beeshop.common.NameTokens;
import com.arcbees.beeshop.client.resources.AppMessages;
import com.arcbees.beeshop.common.dto.ProductDto;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
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
    private final AppMessages messages;
    private final PlaceManager placeManager;
    private final LocaleHelper localeHelper;

    @Inject
    PriceView(
            Binder binder,
            TokenFormatter tokenFormatter,
            AppMessages messages,
            PlaceManager placeManager,
            LocaleHelper localeHelper) {
        this.tokenFormatter = tokenFormatter;
        this.messages = messages;
        this.placeManager = placeManager;
        this.localeHelper = localeHelper;

        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setProduct(ProductDto product) {
        brandName.setInnerText(messages.brandName(product.getBrand()));
        productName.setInnerText(messages.productName(product.getProduct()));
        price.setInnerText(product.getPrice() + " $");

        buildAnchorUri(product);
    }

    private void buildAnchorUri(ProductDto product) {
        String productId = String.valueOf(product.getProduct().getId());
        String nameToken = NameTokens.PRODUCT;

        if (localeHelper.isFrench()) {
            nameToken = NameTokens.translate(nameToken);
        }

        PlaceRequest request = new PlaceRequest.Builder(placeManager.getCurrentPlaceRequest())
                .nameToken(nameToken)
                .with(NameTokens.PARAM_ID, productId)
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
