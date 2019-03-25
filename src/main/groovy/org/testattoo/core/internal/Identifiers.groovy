/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
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

import io.github.classgraph.ClassGraph
import io.github.classgraph.ScanResult
import org.testattoo.core.ComponentException
import org.testattoo.core.CssIdentifier
import org.testattoo.core.Identifier
import org.testattoo.core.component.Component

import java.lang.annotation.Annotation

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Identifiers {
    private static Map<Class, List<Class>> cachedComponents = new HashMap<>()

    private static ScanResult scan = new ClassGraph()
        .whitelistPackages('org.org.testattoo.bundle')
        .scan()

    static Map factories = [
        (CssIdentifier): { CssIdentifier annotation -> return "it.is('${annotation.value()}')" }
    ]

    static boolean hasIdentifier(Class<? extends Component> c) {
        return c.annotations.find { it.annotationType().isAnnotationPresent(Identifier) }
    }

    static String identifyingExpression(Class<? extends Component> c) {
        Annotation annotation = c.declaredAnnotations.find { it.annotationType().isAnnotationPresent(Identifier) }
        if (!annotation) {
            annotation = c.annotations.find { it.annotationType().isAnnotationPresent(Identifier) }
        }
        if (!annotation) {
            throw new ComponentException("Missing @Identifier annotation on type " + c.name)
        }
        Closure<String> handler = factories[annotation.annotationType()]
        if (!handler) {
            throw new ComponentException("Missing handler for annotation type " + annotation.annotationType().name)
        }
        return handler.call(annotation)
    }

    static Map<Class, String> findSelectorsFor(Class clazz) {
        Map<Class, String> selectors = new HashMap<>()

        if (!cachedComponents.get(clazz)) {
            cachedComponents.put(clazz, scan.getSubclasses(clazz.name).loadClasses())
        }

        cachedComponents.get(clazz).each {
            Annotation annotation = it.declaredAnnotations.find { it.annotationType().isAnnotationPresent(Identifier) }
            if (annotation == null) {
                throw new ComponentException("Unable to find any component definition for: " + clazz)
            }
            selectors.put(it, annotation.value())
        }
        selectors
    }
}
