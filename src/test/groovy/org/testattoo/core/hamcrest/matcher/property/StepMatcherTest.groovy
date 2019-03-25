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
package org.testattoo.core.hamcrest.matcher.property

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testattoo.core.support.property.StepSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.jupiter.api.Assertions.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testattoo.core.hamcrest.Matchers.has
import static org.testattoo.core.hamcrest.Matchers.step

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Step Property Matcher")
class StepMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        StepSupport cmp = mock(StepSupport)

        when(cmp.step()).thenReturn(10)
        assertThat(cmp, has(step(10)))

        try {
            assertThat(cmp, has(step(50)))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: has step 50')
                .appendText('\n     but: has step 10')

            assert e.message == description.toString()
        }
    }
}
