package com.lyp.learn.guava.collect;

import com.google.common.base.Function;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * 提供 Table 的静态方法
 */
public class TablesTest {
    /**
     * 返回具有指定行键，列键和值的不可变单元格
     */
    @Test
    public void test_immutableCell() {
        Table.Cell<String, Integer, String> entry = Tables.immutableCell("A", 1, "A1");
        System.out.println(entry.getRowKey());
        System.out.println(entry.getColumnKey());
        System.out.println(entry.getValue());
        System.out.println(entry);

        System.out.println();
        Table.Cell<String, Integer, Character> nullEntry = Tables.immutableCell(null, null, null);
        System.out.println(nullEntry.toString());
    }

    /**
     * transpose(Table<R,C,V> table) rowKey与columnKey翻转
     * 创建翻转其行键和列键的给定表的转置视图.
     *
     * transformValues(Table<R,C,V1> fromTable, Function<? super V1,V2> function)
     * 返回表的视图，其中每个值都由函数转换。
     */
    @Test
    public void test_transpose() {
        Table<String, String, String> table = HashBasedTable.create();
        table.put("A", "1", "A1");
        table.put("A", "2", "A2");
        table.put("A", "3", "A3");
        table.put("B", "1", "B1");
        table.put("B", "5", "B5");

        System.out.println("遍历 table");
        Set<Table.Cell<String, String, String>> cells = table.cellSet();
        cells.forEach(c -> System.out.println(c.getRowKey() + "-" + c.getColumnKey() + " : " + c.getValue()));

        System.out.println("\n遍历 transposeTable");
        Table<String, String, String> transposeTable = Tables.transpose(table);
        transposeTable.cellSet().forEach(c -> System.out.println(c.getRowKey() + "-" + c.getColumnKey() + " : " + c.getValue()));

        System.out.println("\n遍历 transformValues");
        Table<String, String, Object> transformValues = Tables.transformValues(table, new Function<String, Object>() {
            @Nullable
            @Override
            public Object apply(@Nullable String input) {
                return input.contains("B");
            }
        });
        transformValues.cellSet().forEach(c -> System.out.println(c.getRowKey() + "-" + c.getColumnKey() + " : " + c.getValue()));
    }

}
