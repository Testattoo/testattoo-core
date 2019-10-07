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
import org.testattoo.core.component.Window

import static org.mockito.Mockito.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Window")
class WindowTest {
    private static Provider provider

    @BeforeAll
    static void before() {
        provider = mock(Provider)
    }

    @Test
    @DisplayName("Should create a window")
    void should_create_a_window() {
        Window window = new Window(provider, 'id')

        assert window.id == 'id'
        assert window.toString() == 'id'
    }

    @Test
    @DisplayName("Should have equality and hashcode based on id")
    void equality_and_hashcode() {
        Window window_1 = new Window(provider, 'id_1')
        Window window_2 = new Window(provider, 'id_2')
        Window window_3 = new Window(provider, 'id_1')

        assert window_1 != window_2
        assert window_1 == window_3

        assert window_1.hashCode() == 'id_1'.hashCode()
    }

    @Test
    @DisplayName("Should call underline provider on close")
    void should_call_underline_provider_on_close() {
        Provider provider = mock(Provider)

        Window window = new Window(provider, 'id')
        verify(provider, times(0)).closeWindow(window.id)
        window.close()
        verify(provider, times(1)).closeWindow(window.id)
    }
}
