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

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testattoo.core.component.Component

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

@DisplayName("By selector")
class ByTest {
    private static MetaDataProvider meta

    @BeforeAll
    static void before() {
        Config.provider = mock(Provider)
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(new MetaInfo(id: 'id', node: 'node'))
    }

    @Test
    @DisplayName("Should generate expected expression")
    void should_generate_expected_expression() {
        By by = By.css('option')

        Component cmp = new Component()
        cmp.meta = meta

        assert by.getExpression(cmp) == "\$('[id=\"id\"]').find('option')"

        by = By.js('some_js_expression')

        assert by.getExpression(cmp) == "some_js_expression"
    }

    @Test
    @DisplayName("Should find sub components")
    void should_find_sub_components() {
        Component cmp = new Component()
        cmp.meta = meta
        when(Config.provider.metaInfo("\$('[id=\"id\"]').find('sub_expression')")).thenReturn([
            new MetaInfo(id: '1', node: 'node'),
            new MetaInfo(id: '2', node: 'node')
        ])

        List<Component> subs = cmp.find(By.css('sub_expression'))
        assert subs.size() == 2
    }
}
