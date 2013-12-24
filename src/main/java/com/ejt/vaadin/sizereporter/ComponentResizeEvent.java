/*
 * Copyright 2013 Ingo Kegel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ejt.vaadin.sizereporter;

import com.vaadin.ui.Component;

import java.util.EventObject;

public class ComponentResizeEvent extends EventObject {
    private final int width;
    private final int height;

    public ComponentResizeEvent(Component source, int width, int height) {
        super(source);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
