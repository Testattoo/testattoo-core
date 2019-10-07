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

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testattoo.core.support.Selectable
import org.testattoo.core.support.property.GroupSupport
import org.testattoo.core.support.property.ItemSupport
import org.testattoo.core.support.property.LabelSupport
import org.testattoo.core.support.property.SelectedItemSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Dropdown")
class DropdownTest {
    @Test
    @DisplayName("Should have expected inheritance")
    void inheritance() {
        assert Dropdown in Component
        assert Dropdown in ItemSupport
        assert Dropdown in GroupSupport
        assert Dropdown in LabelSupport
        assert Dropdown in Selectable
        assert Dropdown in SelectedItemSupport
    }
}
