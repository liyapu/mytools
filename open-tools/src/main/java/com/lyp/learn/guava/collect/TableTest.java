package com.lyp.learn.guava.collect;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Table 代表一个特殊的映射，其中两个键可以在组合的方式被指定为单个值。它类似于创建映射的映射。
 * 通常来说，当你想使用多个键做索引的时候，你可能会用类似 Map<FirstName, Map<LastName, Person>> 的实现，这种方式很丑陋，使用上也不友好。
 * Guava为此提供了新集合类型 Table，它有两个支持所有类型的键：”行”和”列”，类似效果：Map --> Table --> rowKey+columnKye+value
 *
 * Table 有如下几种实现：
 *      HashBasedTable：本质上用 HashMap<R, HashMap<C, V>> 实现；
 *      TreeBasedTable：本质上用 TreeMap<R, TreeMap<C,V>> 实现；
 *      ImmutableTable：本质上用 ImmutableMap<R, ImmutableMap<C, V>> 实现；注：ImmutableTable对稀疏或密集的数据集都有优化。
 *      ArrayTable：要求在构造时就指定行和列的大小，本质上由一个二维数组实现，以提升访问速度和密集Table的内存利用率。ArrayTable与其他Table的工作原理有点不同，请参见Javadoc了解详情。
 *
 *
 *
 */
public class TableTest {
    /**
     *      1       2       3       4
     *  A   A1      A2      A3      A4
     *  B   B1      B2      B3      B4
     *  C   C1      C2      C3      C4
     */
    static Table<String,String,String> table = HashBasedTable.create();

    static {
        table.put("A","1","A1");
        table.put("A","2","A2");
        table.put("A","3","A3");
        table.put("A","4","A4");

        table.put("B","1","B1");
        table.put("B","2","B2");
        table.put("B","3","B3");
        table.put("B","4","B4");

        table.put("C","1","C1");
        table.put("C","2","C2");
        table.put("C","3","C3");
        table.put("C","4","C4");
    }

    /**
     * cellSet()
     * 返回以 Table.Cell 为元素的Set集合，类似于 Map.entrySet.
     */
    @Test
    public void testCellSet(){
        for(Table.Cell<String,String,String> cell : table.cellSet()){
            System.out.println(cell.getRowKey() + "\t" + cell.getColumnKey() + "\t" + cell.getValue());
        }
    }

    /**
     * size()
     * 返回表中的行键/列键/值映射的数量.
     *  isEmpty()
     * 如果表不包含映射，则返回true.
     * clear()
     * 删除Table中所有的行列映射关系.
     */
    @Test
    public void testBase(){
        System.out.println(table.size());
        table.remove("A","2");
        System.out.println(table.size());
        System.out.println(table.isEmpty());
        table.clear();
        System.out.println(table.size());
        System.out.println(table.isEmpty());

    }

    @Test
    public void testContains(){
        System.out.println(table.containsRow("A"));
        System.out.println(table.containsRow("Z"));

        System.out.println();
        System.out.println(table.containsColumn("2"));
        System.out.println(table.containsColumn("8"));

        System.out.println();
        System.out.println(table.contains("A","2"));
        System.out.println(table.contains("A","8"));

        System.out.println();
        System.out.println(table.containsValue("A2"));
        System.out.println(table.containsValue("C2"));
        System.out.println(table.containsValue("X2"));
    }

    /**
     * 行相关
     *
     * rowKeySet()
     * 返回所有的rowKey
     *
     */
    @Test
    public void testRow1(){
        System.out.println("-----rowKeySet-----");
        Set<String> rowKeySet = table.rowKeySet();
        rowKeySet.stream()
                .forEach(System.out::println);

        System.out.println("-----row-------");
        Map<String, String> bMap = table.row("B");
        System.out.println(bMap);

        Map<String, String> zMap = table.row("Z");
        System.out.println(zMap);

        System.out.println("-----rowMap-------");
        Map<String, Map<String, String>> stringMapMap = table.rowMap();
        System.out.println(stringMapMap);
    }

    /**
     * 列相关
     */
    @Test
    public void testColumn1(){
        System.out.println("------columnKeySet-----");
        Set<String> columnKeySet = table.columnKeySet();
        columnKeySet.stream()
                    .forEach(System.out::println);

        System.out.println("----column2");
        Map<String, String> column2 = table.column("2");
        System.out.println(column2);

        System.out.println("------columnMap----");
        Map<String, Map<String, String>> stringMapMap = table.columnMap();
        System.out.println(stringMapMap);
    }

    /**
     * table 行或列 返回的视图
     * 修改视图，会影响底层的table,同样的，修改table也会改变视图的值
     */
    @Test
    public void testViews(){
        for(Table.Cell<String,String,String> cell : table.cellSet()){
            System.out.println(cell.getRowKey() + "\t" + cell.getColumnKey() + "\t" + cell.getValue());
        }
        System.out.println();

        Map<String, String> bMap = table.row("B");
        System.out.println(bMap);
        bMap.put("2","B2222");
        bMap.put("4","B4444");
        System.out.println(bMap);

        System.out.println();
        for(Table.Cell<String,String,String> cell : table.cellSet()){
            System.out.println(cell.getRowKey() + "\t" + cell.getColumnKey() + "\t" + cell.getValue());
        }

    }

    /**
     * values()
     * 返回各行各列中所有的value值.
     */
    @Test
    public void testValues(){
        Collection<String> values = table.values();
        values.stream()
                .forEach(System.out::println);
    }
}
