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
package org.testattoo.core

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Window {
    final String id

    Window(String id) { this.id = id }

    void close() { Testattoo.config.evaluator.closeWindow(this.id) }

    @Override
    String toString() { this.id }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Window window = (Window) o
        id == window.id
    }

    int hashCode() { id.hashCode() }
}
