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

package com.ejt.vaadin.sizereporter;


import com.ejt.vaadin.sizereporter.shared.SizeReporterRpc;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.AbstractComponent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Extension that reports the size of a particular component in the browser.
 * Each time the size of the component changes in the browser, the new component size is reported to the server and
 * can be retrieved via {@link #getHeight()} and {@link #getWidth()}.
 * <p>
 * To react to size changes, register a {@link ComponentResizeListener} with
 * {@link #addResizeListener(ComponentResizeListener)}.
 * <p>
 * Note: the size is reported after layout is realized in the browser,
 * so you cannot use the size right away when constructing a component.
 */
public class SizeReporter extends AbstractExtension {

    private AbstractComponent component;

    private Set<ComponentResizeListener> resizeListeners = new LinkedHashSet<ComponentResizeListener>();
    private Set<ComponentResizeListener> resizeListenersOnce = new LinkedHashSet<ComponentResizeListener>();

    private int width = -1;
    private int height = -1;

    /**
     * Construct a size reporter for a specified component.
     * @param component the component for which the size is reported
     */
    public SizeReporter(AbstractComponent component) {
        this.component = component;
        extend(component);
        registerRpc(new SizeReporterRpc() {
            @Override
            public void sizeChanged(int width, int height) {
                SizeReporter.this.width = width;
                SizeReporter.this.height = height;
                fireResizeEvent();
            }
        });
    }

    /**
     * Returns the width of the component in the browser in pixels.
     * @return the width or -1 if the width is unknown
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the component in the browser in pixels.
     * @return the height or -1 if the height is unknown
     */
    public int getHeight() {
        return height;
    }

    /**
     * Add a listener for resize events.
     * @param listener the listener
     */
    public void addResizeListener(ComponentResizeListener listener) {
        resizeListeners.add(listener);
    }

    /**
     * Add a listener for resize events that is removed after the first invocation.
     * @param listener the listener
     */
    public void addResizeListenerOnce(ComponentResizeListener listener) {
        resizeListenersOnce.add(listener);
    }

    /**
     * Remove a previously registered resize listener.
     * @param listener
     */
    public void removeResizeListener(ComponentResizeListener listener) {
        resizeListeners.remove(listener);
    }

    @Override
    public boolean isConnectorEnabled() {
        return true; // we want to get resize events for disabled components, too
    }

    private void fireResizeEvent() {
        ComponentResizeEvent event = new ComponentResizeEvent(component, width, height);
        for (ComponentResizeListener listener : resizeListeners) {
            listener.sizeChanged(event);
        }
        for (ComponentResizeListener listener : resizeListenersOnce) {
            listener.sizeChanged(event);
        }
        resizeListenersOnce.clear();
    }

}
