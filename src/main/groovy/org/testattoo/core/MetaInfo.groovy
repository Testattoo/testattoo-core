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
package org.testattoo.core

import com.google.common.reflect.ClassPath
import groovy.transform.Immutable
import org.testattoo.core.component.Component
import org.testattoo.core.internal.Identifiers

import static java.lang.Thread.currentThread

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
@Immutable
class MetaInfo {
    static final Collection<Class<Component>> componentTypes = new HashSet<>()

    String node
    String id

    static {
        scan 'org.testattoo.bundle'
    }

    @Override
    String toString() { "id=${id}, node=${node}" }

    Object asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            return $("[id=\"${id}\"]").asType(clazz)
        }
        return super.asType(clazz)
    }

    static void scan(String... packageNames) {
        componentTypes.addAll(packageNames
            .collect { ClassPath.from(currentThread().contextClassLoader).getTopLevelClassesRecursive(it) }
            .flatten()
            .collect { it.load() }
            .findAll { Component.isAssignableFrom(it) && Identifiers.hasIdentifier(it) })
    }
}
