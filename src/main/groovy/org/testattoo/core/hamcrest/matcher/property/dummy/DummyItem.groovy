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
package org.testattoo.core.hamcrest.matcher.property.dummy

import org.testattoo.core.component.Item

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DummyItem extends Item {
    String value

    DummyItem(String value) {
        this.value = value
    }

    @Override
    boolean selected() { return false }

    @Override
    Object value() { return value }
}
