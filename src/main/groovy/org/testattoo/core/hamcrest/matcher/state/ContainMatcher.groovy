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
package org.testattoo.core.hamcrest.matcher.state

import org.hamcrest.Description
import org.testattoo.core.Config
import org.testattoo.core.component.Component
import org.testattoo.core.hamcrest.StateMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ContainMatcher extends StateMatcher<Component> {
    private List<Component> components = new ArrayList<>()
    private Component container
    private List ret

    ContainMatcher(Component... components) {
        this.components.addAll(components)
    }

    @Override
    protected boolean matchesSafely(Component container, Description mismatchDescription) {
        this.container = container
        ret = container.provider.getJson("\$().org.testattoo({method: 'contains', id:'${container.id()}', ids: [${components.collect { "'${it.id()}'" }.join(', ')}]});")
        mismatchDescription.appendText("does not contains expected component(s): ${components.findAll { it.id() in ret }}")
        ret.empty
    }

    @Override
    void describeTo(Description description) {
        description.appendText("Component ${container} contains ${components}")
    }
}
