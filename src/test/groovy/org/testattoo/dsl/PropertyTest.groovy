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
package org.testattoo.dsl

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.testattoo.core.Evaluator
import org.testattoo.core.MetaDataProvider
import org.testattoo.core.MetaInfo
import org.testattoo.core.component.*
import org.testattoo.core.component.datagrid.Cell
import org.testattoo.core.component.datagrid.Column
import org.testattoo.core.component.datagrid.DataGrid
import org.testattoo.core.component.datagrid.Row
import org.testattoo.core.component.field.RangeField
import org.testattoo.core.component.field.TextField

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.testattoo.core.Testattoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Property")
class PropertyTest {
    private static MetaDataProvider meta
    private static MetaInfo metaInfo = new MetaInfo(id: 'id', node: 'node')

    @BeforeAll
    static void before() {
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(metaInfo)
        config.evaluator = mock(Evaluator)
    }

    @Test
    @DisplayName("Should support label and placeholder")
    void should_support_label_and_placeholder() {
        TextField field = spy(TextField)
        field.meta = meta

        doReturn('Label').when(field).label()
        field.should { have label('Label') }

        doReturn('placeholder').when(field).placeholder()
        field.should { have placeholder('placeholder') }
    }

    @Test
    @DisplayName("Should support: maximum, minimum and step")
    void should_support_maximum_minimum_and_step() {
        RangeField field = spy(RangeField)
        field.meta = meta

        doReturn(5).when(field).minimum()
        doReturn(10).when(field).maximum()
        field.should {
            have minimum(5)
            have maximum(10)
        }

        doReturn(2).when(field).step()
        field.should { have step(2) }
    }

    @Test
    @DisplayName("Should support value")
    void should_support_value() {
        TextField field = spy(TextField)
        field.meta = meta

        doReturn('Value').when(field).value()
        field.should { have value('Value') }
    }

    @Test
    @DisplayName("Should support text")
    void should_support_text() {
        Button button = spy(Button)
        button.meta = meta

        doReturn('Text').when(button).text()
        button.should { have text('Text') }
    }

    @Test
    @DisplayName("Should support length")
    void should_support_length() {
        TextField other_field = spy(TextField)
        other_field.meta = meta

        doReturn(25).when(other_field).length()
        other_field.should { have length(25) }
    }

    @Test
    @DisplayName("Should support reference")
    void should_support_reference() {
        Link link = spy(Link)
        link.meta = meta

        doReturn('http://reference').when(link).reference()
        link.should { have reference('http://reference') }
    }

    @Test
    @DisplayName("Should support datagrid properties")
    void should_support_datagrid() {
        DataGrid datagrid = spy(DataGrid)
        datagrid.meta = meta

        Column column_1 = spy(Column)
        column_1.meta = meta
        Column column_2 = spy(Column)
        column_2.meta = meta

        doReturn([column_1, column_2]).when(datagrid).columns()
        datagrid.should { have columns(column_1, column_2) }

        doReturn('Column 1').when(column_1).title()
        doReturn('Column 2').when(column_2).title()
        datagrid.should { have columns('Column 1', 'Column 2') }

        Row row_1 = spy(Row)
        row_1.meta = meta
        Row row_2 = spy(Row)
        row_2.meta = meta

        doReturn([row_1, row_2]).when(datagrid).rows()
        datagrid.should { have rows(row_1, row_2) }

        doReturn('Row 1').when(row_1).title()
        doReturn('Row 2').when(row_2).title()
        datagrid.should { have rows('Row 1', 'Row 2') }

        row_1.should { have title('Row 1') }

        Cell cell_1 = spy(Cell)
        cell_1.meta = meta
        Cell cell_2 = spy(Cell)
        cell_2.meta = meta

        doReturn([cell_1, cell_2]).when(row_1).cells()
        row_1.should { have cells(cell_1, cell_2) }

        doReturn('Cell 1').when(cell_1).value()
        doReturn('Cell 2').when(cell_2).value()
        row_1.should { have cells('Cell 1', 'Cell 2') }
    }

    @Test
    @DisplayName("Should support dropdown properties")
    void should_support_dropdown() {
        Dropdown dropdown = spy(Dropdown)
        dropdown.meta = meta

        Group group_1 = spy(Group)
        group_1.meta = meta
        Group group_2 = spy(Group)
        group_2.meta = meta

        doReturn([group_1, group_2]).when(dropdown).groups()
        dropdown.should { have groups(group_1, group_2) }

        doReturn('Group 1').when(group_1).value()
        doReturn('Group 2').when(group_2).value()
        dropdown.should { have groups('Group 1', 'Group 2') }

        Item item_1 = spy(Item)
        item_1.meta = meta

        Item item_2 = spy(Item)
        item_2.meta = meta

        doReturn([item_1, item_2]).when(dropdown).items()
        dropdown.should { have items(item_1, item_2) }

        doReturn('Item 1').when(item_1).value()
        doReturn('Item 2').when(item_2).value()
        dropdown.should { have items('Item 1', 'Item 2') }

        doReturn(item_1).when(dropdown).selectedItem()
        dropdown.should {
            have selectedItem(item_1)
            have selectedItem('Item 1')
        }
    }

    @Test
    @DisplayName("Should support ListBox properties")
    void should_support_listbox() {
        ListBox listBox = spy(ListBox)
        listBox.meta = meta

        Item item_1 = spy(Item)
        item_1.meta = meta

        Item item_2 = spy(Item)
        item_2.meta = meta

        doReturn('Item 1').when(item_1).value()
        doReturn('Item 2').when(item_2).value()
        doReturn([item_1, item_2]).when(listBox).selectedItems()
        listBox.should {
            have selectedItems(item_1, item_2)
            have selectedItems('Item 1', 'Item 2')
        }
    }
}
