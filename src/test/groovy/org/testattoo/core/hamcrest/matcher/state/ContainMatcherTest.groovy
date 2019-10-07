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
import org.testattoo.core.Config
import org.testattoo.core.Provider
import org.testattoo.core.MetaDataProvider
import org.testattoo.core.MetaInfo
import org.testattoo.core.component.Component

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.jupiter.api.Assertions.fail
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testattoo.core.hamcrest.Matchers.contain

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Contain State Matcher")
class ContainMatcherTest {
    @Test
    @DisplayName("Should have expected matcher available")
    void should_have_expected_matcher() {
        MetaDataProvider containerMeta = mock(MetaDataProvider)
        MetaDataProvider cmp_1Meta = mock(MetaDataProvider)
        MetaDataProvider cmp_2Meta = mock(MetaDataProvider)
        Provider provider = mock(Provider)

        Component container = new Component(provider, containerMeta)
        Component cmp_1 = new Component(provider, cmp_1Meta)
        Component cmp_2 = new Component(provider, cmp_2Meta)

        when(containerMeta.metaInfo(container)).thenReturn(new MetaInfo(id: 'container', node: 'node'))
        when(cmp_1Meta.metaInfo(cmp_1)).thenReturn(new MetaInfo(id: '1', node: 'node'))
        when(cmp_2Meta.metaInfo(cmp_2)).thenReturn(new MetaInfo(id: '2', node: 'node'))

        List<String> result = new ArrayList<>()

        when(provider.getJson(any(String))).thenReturn(result)
        assertThat(container, contain(cmp_1, cmp_2))

        result.add(cmp_2.id())
        when(provider.getJson(any(String))).thenReturn(result)
        try {
            assertThat(container, contain(cmp_1, cmp_2))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription()
            description.appendText('\nExpected: Component Component:container contains [Component:1, Component:2]')
                .appendText('\n     but: does not contains expected component(s): [Component:2]')

            assert e.message == description.toString()
        }
    }
}
