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
import org.hamcrest.StringDescription
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testattoo.core.component.Component

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testattoo.core.hamcrest.Matchers.hidden

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Hidden State Matcher")
class HiddenMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        Component cmp = mock(Component)

        when(cmp.visible()).thenReturn(false)
        assertThat(cmp, is(hidden()))

        when(cmp.visible()).thenReturn(true)
        try {
            assertThat(cmp, is(hidden()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: is hidden')
                .appendText('\n     but: is visible')

            assert e.message == description.toString()
        }
    }
}
