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
package org.testattoo.core.internal

import org.hamcrest.Matcher
import org.testattoo.core.ComponentException
import org.testattoo.core.Config
import org.testattoo.core.component.Component
import org.testattoo.core.component.Item
import org.testattoo.core.hamcrest.Matchers
import org.testattoo.core.hamcrest.PropertyMatcher
import org.testattoo.core.hamcrest.StateMatcher
import org.testattoo.core.hamcrest.matcher.property.*
import org.testattoo.core.hamcrest.matcher.state.ContainMatcher
import org.testattoo.core.input.Key
import org.testattoo.core.support.Selectable
import org.testattoo.core.support.UnSelectable

import java.time.Duration

import static org.testattoo.core.Testattoo.waitUntil
import static org.testattoo.core.input.Key.*
import static org.testattoo.core.input.MouseModifiers.SINGLE

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {
    static Duration getSeconds(Number self) { Duration.ofSeconds(self.longValue()) }

    static Collection<?> plus(Key a, Key b) { [a, b] }

    static Collection<?> plus(Key a, String b) { [a, b] }

    static void click(Key key, Component c) { click([key], c) }

    static void click(Collection<Key> keys, Component c) {
        Config.provider.click(c.id(), [LEFT, SINGLE], keys)
    }

    // Not testable in current browsers cause handled by browsers
    // ====================================================================
    static void rightClick(Key key, Component c) { rightClick([key], c) }

    static void rightClick(Collection<Key> keys, Component c) {
        Config.provider.click(c.id(), [RIGHT, SINGLE], keys)
    }

    // ====================================================================

    static void select(Selectable component, String... values) {
        values.each { value ->
            component.items().find { it.value() == value }.each { select(component, it) }
        }
    }

    static void select(Selectable component, Item... items) {
        items.each {
            if (component.items().contains(it)) {
                if (!it.enabled())
                    throw new ComponentException("${it.class.simpleName} ${it} is disabled and cannot be selected")
                if (it.selected()) {
                    throw new ComponentException("${it.class.simpleName} ${it} is already selected and cannot be selected")
                }
                CTRL.click it
            }
        }
    }

    static void unselect(UnSelectable component, String... values) {
        values.each { value ->
            component.items().find { it.value() == value }.each { unselect(component, it) }
        }
    }

    static void unselect(UnSelectable component, Item... items) {
        items.each {
            if (component.items().contains(it)) {
                if (!it.enabled())
                    throw new ComponentException("${it.class.simpleName} ${it} is disabled and cannot be deselected")
                if (!it.selected()) {
                    throw new ComponentException("${it.class.simpleName} ${it} is already unselected and cannot be deselected")
                }
                CTRL.click it
            }
        }
    }

    // ====================================================================

    static PropertyMatcher getItems(Integer number) {
        new ItemSizeMatcher(number)
    }

    static PropertyMatcher getVisibleItems(Integer number) {
        new VisibleItemsSizeMatcher(number)
    }

    static PropertyMatcher getGroups(Integer number) {
        new GroupSizeMatcher(number)
    }

    static PropertyMatcher getColumns(Integer number) {
        new ColumnSizeMatcher(number)
    }

    static PropertyMatcher getRows(Integer number) {
        new RowSizeMatcher(number)
    }

    static PropertyMatcher getCells(Integer number) {
        new CellSizeMatcher(number)
    }

    static void should(Component component, Closure closure) {
        closure.delegate = component
        closure(this)
        for (Matcher matcher : component.blocks) {
            waitUntil(closure, matcher)
        }
        component.clearBlocks()
    }

    static void be(Component component, Class<StateMatcher> matcher) {
        component.addBlock(org.hamcrest.Matchers.is(matcher.newInstance()))
    }

    static void contain(Component component, Component... components) {
        component.addBlock(new ContainMatcher(components))
    }

    static void have(Component component, PropertyMatcher matcher) {
        component.addBlock(Matchers.has((matcher)))
    }
}
