/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.arcbees.beeshop.client.application.widget.slider;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import com.arcbees.beeshop.client.application.CurrentBrand;
import com.arcbees.beeshop.client.events.BrandChangedEvent;
import com.arcbees.beeshop.client.events.BrandChangedEventHandler;
import com.arcbees.beeshop.client.resources.SliderResources;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

import static com.google.gwt.query.client.GQuery.$;

public class Slider implements IsWidget, AttachEvent.Handler, BrandChangedEventHandler {
    interface Binder extends UiBinder<HTMLPanel, Slider> {
    }

    private static final String TRANSITION_END = "transitionend webkitTransitionEnd oTransitionEnd MSTransitionEnd";
    public static final int ANIMATION_DURATION = 550;

    @Inject
    private static CurrentBrand currentBrand;
    @Inject
    private static EventBus eventBus;
    private static Binder BINDER = GWT.create(Binder.class);

    @UiField
    HTMLPanel contents;
    @UiField
    SliderResources sliderResources;

    private final HTMLPanel root;
    private final LinkedList<Function> calls;

    private List<IsWidget> children = Lists.newArrayList();
    private GQuery activeItem;
    private boolean activeAnimation;
    private HandlerRegistration handlerRegistration;
    private Brand brand;

    public Slider() {
        calls = new LinkedList<>();

        root = BINDER.createAndBindUi(this);

        asWidget().addAttachHandler(this);
    }

    @Override
    public Widget asWidget() {
        return root;
    }

    @UiChild(tagname = "content")
    public void addContent(IsWidget content) {
        children.add(content);
        contents.add(content);
    }

    @Override
    public void onAttachOrDetach(AttachEvent event) {
        if (event.isAttached()) {
            handlerRegistration = eventBus.addHandler(BrandChangedEvent.TYPE, this);

            $(contents).find("li")
                    .one(Event.ONCLICK, null, createProductClickHandler())
                    .removeClass(sliderResources.style().activeProduct());

            for (final IsWidget child : children) {
                setOrder($(child), String.valueOf(children.indexOf(child)));
            }

            activeItem = $(contents.getWidget(3));
            $(activeItem).addClass(sliderResources.style().activeProduct());

            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    updateFromCurrentBrand();
                }
            });
        } else {
            handlerRegistration.removeHandler();

            for (final IsWidget child : children) {
                $(child).unbind(BrowserEvents.CLICK);
            }

            children.clear();
        }
    }

    public void updateFromCurrentBrand() {
        Brand newBrand = currentBrand.get();
        if (newBrand == brand || activeAnimation) {
            return;
        }

        brand =  newBrand;

        $(contents).find("a").each(new Function() {
            @Override
            public void f() {
                String dataBrand = $(this).attr("data-brand");
                if (brand.getValue().equals(dataBrand)) {
                    GQuery li = $(this).parent();
                    li.unbind(BrowserEvents.CLICK);
                    handleClick(li);
                }
            }
        });
    }

    public void setAddStyleNames(String style) {
        asWidget().addStyleName(style);
    }

    @Override
    public void onBrandChanged(BrandChangedEvent event) {
        updateFromCurrentBrand();
    }

    Function createAnimation(final GQuery w) {
        return new Function() {
            @Override
            public void f() {
                final List<Element> elements = Lists.newArrayList(w.get(0), activeItem.get(0));
                final String indexOfSelected = w.css("order");

                activeAnimation = true;
                activeItem.bind(TRANSITION_END, new Function() {
                    @Override
                    public void f() {
                        $(this).unbind(TRANSITION_END);

                        activeItem.removeClass(sliderResources.style().activeProduct());
                        w.addClass(sliderResources.style().activeProduct());

                        $(elements).attr("style", "transform: scale(1);")
                                .one(Event.ONCLICK, null, createProductClickHandler());

                        setOrder(w, String.valueOf(3));
                        setOrder(activeItem, indexOfSelected);

                        activeItem = $(w.get(0));
                        queueFinishAnimation();
                    }
                });

                $(elements).css("transform", "scale(0.1)");
            }
        };
    }

    private void queueFinishAnimation() {
        new Timer() {
            @Override
            public void run() {
                activeAnimation = false;
                if (!calls.isEmpty()) {
                    Function nextCall = calls.poll();
                    nextCall.f();
                }
            }
        }.schedule(ANIMATION_DURATION);
    }

    private Function createProductClickHandler() {
        return new Function() {
            @Override
            public void f() {
                handleClick($(getElement()));
            }
        };
    }

    private void handleClick(final GQuery w) {
        Function function = createAnimation(w);
        if (activeAnimation) {
            calls.add(function);
        } else {
            function.f();
        }
    }

    private void setOrder(GQuery child, String order) {
        String currentStyle = child.attr("style");
        child.attr("style", Strings.nullToEmpty(currentStyle) + "order:" + order + ";-webkit-order:" + order);
    }
}
