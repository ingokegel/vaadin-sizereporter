/*
 * Copyright 2013 ej-technologies GmbH.
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

package com.ejt.vaadin.sizereporter.shared;

import com.ejt.vaadin.sizereporter.SizeReporter;
import com.google.gwt.user.client.ui.Widget;

import com.vaadin.client.ComponentConnector;
import com.vaadin.client.LayoutManager;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.layout.ElementResizeEvent;
import com.vaadin.client.ui.layout.ElementResizeListener;
import com.vaadin.shared.ui.Connect;

@Connect(SizeReporter.class)
public class SizeReporterConnector extends AbstractExtensionConnector {

    private SizeReporterRpc sizeReporterRpc;

    @Override
    protected void init() {
        super.init();
        sizeReporterRpc = getRpcProxy(SizeReporterRpc.class);
    }

    @Override
    protected void extend(ServerConnector target) {
        if (target instanceof ComponentConnector) {
            final Widget widget = ((ComponentConnector)target).getWidget();

            LayoutManager.get(getConnection()).addElementResizeListener(widget.getElement(),
                new ElementResizeListener() {
                    @Override
                    public void onElementResize(ElementResizeEvent event) {
                        sizeReporterRpc.sizeChanged(widget.getOffsetWidth(), widget.getOffsetHeight());
                    }
                }
            );
        }
    }

}
