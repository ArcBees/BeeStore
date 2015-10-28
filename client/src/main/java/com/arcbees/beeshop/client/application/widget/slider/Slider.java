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

import java.util.List;
import java.util.Stack;

import javax.inject.Inject;

import com.arcbees.beeshop.client.application.CurrentBrand;
import com.arcbees.beeshop.client.resources.SliderResources;
import com.arcbees.beeshop.common.dto.Brand;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.plugins.Events;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import sun.plugin2.message.EventMessage;

import static com.google.gwt.query.client.GQuery.$;

public class Slider implements IsWidget, AttachEvent.Handler {
    interface Binder extends UiBinder<HTMLPanel, Slider> {
    }

    private static final String DATA_INDEX = "data-index";
    private static final String TRANSITION_END = "transitionend webkitTransitionEnd oTransitionEnd MSTransitionEnd";

    @Inject
    private static CurrentBrand currentBrand;
    private static Binder BINDER = GWT.create(Binder.class);

    @UiField
    HTMLPanel contents;
    @UiField
    SliderResources sliderResources;

    private final HTMLPanel root;
    private final Stack<Function> calls;

    private List<IsWidget> children = Lists.newArrayList();
    private GQuery activeItem;
    private boolean activeAnimation;

    public Slider() {
        calls = new Stack<>();

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
                    $(contents).find("a").each(new Function() {
                        @Override
                        public void f() {
                            Brand brand = currentBrand.get();
                            String dataBrand = $(this).attr("data-brand");

                            if (brand.getValue().equals(dataBrand)) {
                                handleClick($(this).parent());
                            }
                        }
                    });
                }
            });
        } else {
            for (final IsWidget child : children) {
                $(child).unbind(BrowserEvents.CLICK);
            }

            children.clear();
        }
    }

    public void setAddStyleNames(String style) {
        asWidget().addStyleName(style);
    }

    Function doIt(final GQuery w) {
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

                        setOrder(w, String.valueOf(3));
                        setOrder(activeItem, indexOfSelected);

                        w.add(activeItem).one(Event.ONCLICK, null, createProductClickHandler());

                        $(children).css("transform", "scale(1)");
                        activeItem = w;
                        activeAnimation = false;
                        if (!calls.isEmpty()) {
                            Function nextCall = calls.pop();
                            nextCall.f();
                        }
                    }
                });

                $(elements).css("transform", "scale(0.1)");
            }
        };
    }

    private Function createProductClickHandler() {
        return new Function() {
            @Override
            public void f() {
                handleClick($(this));
            }
        };
    }

    private void handleClick(final GQuery w) {
        Function function = doIt(w);
        if (activeAnimation) {
            calls.add(function);
        } else {
            function.f();
        }
    }

    private void setOrder(GQuery child, String order) {
        child.get(0).getStyle().setProperty("order", order);
    }
}
