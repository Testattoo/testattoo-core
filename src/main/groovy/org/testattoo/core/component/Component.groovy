/**
 * Copyright © 2019 Testattoo (altus34@gmail.com)
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
package org.testattoo.core.component

import org.hamcrest.Matcher
import org.testattoo.core.By
import org.testattoo.core.ComponentException
import org.testattoo.core.MetaDataProvider
import org.testattoo.core.Provider
import org.testattoo.core.input.DragBuilder
import org.testattoo.core.support.Clickable
import org.testattoo.core.support.Draggable

import static java.util.Collections.unmodifiableCollection
import static org.testattoo.core.input.MouseModifiers.*

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Component implements Clickable, Draggable {
    private final Queue<Matcher> BLOCKS = new LinkedList<>()

    Provider provider
    MetaDataProvider meta

    protected Component() {}

    Component(Provider provider, MetaDataProvider meta) {
        super()
        this.provider = provider
        this.meta = meta
    }

    String id() throws ComponentException {
        meta.metaInfo(this).id
    }

    boolean enabled() {
        !provider.enabled(this)
    }

    boolean available() {
        try {
            meta.metaInfo(this)
            return true
        } catch (ComponentException ignored) {
            return false
        }
    }

    boolean visible() {
        provider.visible(this)
    }

    protected <T extends Component> List<T> find(By by, Class<T> type = Component) {
        provider.metaInfo(by.getExpression(this)).collect { it.asType(type) } as List<T>
    }

    void clearBlocks() {
        BLOCKS.clear()
    }

    boolean addBlock(Matcher matcher) {
        BLOCKS.add(matcher)
    }

    Collection<Matcher> getBlocks() {
        unmodifiableCollection(BLOCKS)
    }

    @Override
    void click() {
        provider.click(id(), [LEFT, SINGLE], [])
    }

    @Override
    void rightClick() {
        provider.click(id(), [RIGHT, SINGLE], [])
    }

    @Override
    void doubleClick() {
        provider.click(id(), [LEFT, DOUBLE], [])
    }

    @Override
    DragBuilder drag() {
        new DragBuilder(this)
    }

    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false
        Component component = (Component) o
        id() == component.id()
    }

    @Override
    int hashCode() { id().hashCode() }

    @Override
    String toString() { getClass().simpleName + ":${this.id()}" }

    Object asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            Component c = (Component) clazz.newInstance()
            c.provider = provider
            c.meta = this.meta
            return c
        }
        return super.asType(clazz)
    }
}
