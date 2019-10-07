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
package org.testattoo.core.component.datagrid

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testattoo.core.component.Component
import org.testattoo.core.support.property.CellSupport
import org.testattoo.core.support.property.TitleSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Column")
class ColumnTest {
    @Test
    @DisplayName("Should have expected inheritance")
    void inheritance() {
        assert Column in Component
        assert Column in TitleSupport
        assert Column in CellSupport
    }

    @Test
    @DisplayName("Should have equality and hashcode based on column title")
    void equality_and_hashcode() {
        Column column_1 = new TestColumn('title_1')
        Column column_2 = new TestColumn('title_2')
        Column column_3 = new TestColumn('title_1')

        assert !column_1.equals(column_2)
        assert column_1.equals(column_3)

        assert column_1.hashCode() == 'title_1'.hashCode()
    }

    private class TestColumn extends Column {
        private String title

        TestColumn(String title) {
            this.title = title
        }

        List<Cell> cells() {
            return null
        }

        Cell cell(Object value) {
            return null
        }

        String title() {
            return title
        }
    }
}
