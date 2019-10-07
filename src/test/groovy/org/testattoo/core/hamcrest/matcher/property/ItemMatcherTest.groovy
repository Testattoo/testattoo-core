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
package org.testattoo.core.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testattoo.core.component.Item
import org.testattoo.core.support.property.ItemSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.jupiter.api.Assertions.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testattoo.core.hamcrest.Matchers.has
import static org.testattoo.core.hamcrest.Matchers.items

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Item Property Matcher")
class ItemMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        ItemSupport cmp = mock(ItemSupport)

        Item item_1 = mock(Item)
        when(item_1.value()).thenReturn('item_1')
        Item item_2 = mock(Item)
        when(item_2.value()).thenReturn('item_2')
        Item item_3 = mock(Item)
        when(item_3.value()).thenReturn('item_3')

        when(cmp.items()).thenReturn([item_1, item_2])

        assertThat(cmp, has(items('item_1', 'item_2')))
        assertThat(cmp, has(items(item_1, item_2)))

        try {
            assertThat(cmp, has(items('item_1', 'item_3')))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has item(s) ["item_1", "item_3"]')
                .appendText('\n     but: has item(s) ["item_1", "item_2"]')

            assert e.message == description.toString()
        }

        try {
            assertThat(cmp, has(items(item_1, item_3)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has item(s) ["item_1", "item_3"]')
                .appendText('\n     but: has item(s) ["item_1", "item_2"]')

            assert e.message == description.toString()
        }
    }
}
