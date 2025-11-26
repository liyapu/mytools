package com.lyp.learn.guava.collect;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

/**
 * 提供 Table 的静态方法
 */
public class TableTest02 {
    /**
     * 返回具有指定行键，列键和值的不可变单元格
     *        1
     *    A  A1
     *
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
     *
     * transpose(Table<R, C, V>)方法允许你把Table<C, R, V>转置成Table<R, C, V>。
     * 例如，如果你在用Table构建加权有向图，这个方法就可以把有向图反转。
     *
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

    @Test
    public void test_transpose2() {
        Table<String, String, String> table = HashBasedTable.create();
        table.put("A", "1", "A1");
        table.put("A", "2", "A2");
        table.put("A", "3", "A3");
        table.put("B", "1", "B1");
        table.put("B", "5", "B5");

        System.out.println(JSONObject.toJSONString(table));
        System.out.println(table.get("A", "1"));
        System.out.println(table.get("5", "B"));
    }

    /**
     *          1       2       3           5
     *      A   A1      A2      A3
     *      B   B1                         B5
     *      C   C1      C2
     */
    @Test
    public void test_table40() {
        Table<String, String, String> table = HashBasedTable.create();
        table.put("A", "1", "A1");
        table.put("A", "2", "A2");
        table.put("A", "3", "A3");
        table.put("B", "1", "B1");
        table.put("B", "5", "B5");
        table.put("C", "1", "C1");
        table.put("C", "2", "C2");

        System.out.println("table.rowKeySet() ---> " + table.rowKeySet());
        System.out.println("table.columnKeySet() ---> " + table.columnKeySet());
        System.out.println("table.cellSet() ---> " + table.cellSet());
        System.out.println("table.values() ---> " + table.values());
        System.out.println("table.rowMap() ---> " + table.rowMap());
        System.out.println("table.columnMap() ---> " + table.columnMap());

        System.out.println("------------");
        System.out.println("table.row(\"A\") ---> " + table.row("A"));
        System.out.println("table.row(\"A\").get(\"1\") ---> " + table.row("A").get("1"));
        System.out.println("table.row(\"A\").get(\"10\") ---> " + table.row("A").get("10"));
        System.out.println("table.row(\"B\") ---> " + table.row("B"));
        System.out.println("table.row(\"X\") ---> " + table.row("X"));
        System.out.println("table.row(\"X\").get(\"1\") ---> " + table.row("X").get("1"));
        System.out.println("table.row(\"X\").get(\"10\") ---> " + table.row("X").get("10"));
        System.out.println("table.column(\"1\") ---> " + table.column("1"));
        System.out.println("table.column(\"10\") ---> " + table.column("10"));
        System.out.println("------------");

        System.out.println("table.contains(\"A\", \"1\") ---> " + table.contains("A", "1"));
        System.out.println("table.contains(\"A\", \"10\") ---> " + table.contains("A", "10"));
        System.out.println("table.containsColumn(\"1\") ---> " + table.containsColumn("1"));
        System.out.println("table.containsColumn(\"10\") ---> " + table.containsColumn("10"));
        System.out.println("table.containsRow(\"A\") ---> " + table.containsRow("A"));
        System.out.println("table.containsRow(\"X\") ---> " + table.containsRow("X"));
        System.out.println("table.containsValue(\"A1\") ---> " + table.containsValue("A1"));
        System.out.println("table.containsValue(\"A10\") ---> " + table.containsValue("A10"));
        System.out.println("table.size() ---> " + table.size());

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    @Test
    public void test_table41() {
        Table<String, String, String> table = HashBasedTable.create();
        table.put("keyIdA", "类型111", "备注111");
        table.put("keyIdB", "类型222", "备注222");
        table.put("keyIdC", "类型333", "备注333");

        System.out.println(table.columnKeySet());
        System.out.println(table.cellSet());
        System.out.println(table.rowKeySet());
        System.out.println(table.values());
        System.out.println(table.rowMap());
        System.out.println(table.columnMap());
    }
}
