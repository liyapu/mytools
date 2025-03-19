package com.lyp.learn.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class JsonDiffUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 对比两个 JSON 数据
     *
     * @param jsonA          第一个 JSON 字符串
     * @param jsonB          第二个 JSON 字符串
     * @param ignoreDiffPath 需要忽略对比的 JSON 路径（如 ["a.b.c", "x.y"]）
     * @param isOrder        是否考虑数组顺序（true=顺序敏感，false=顺序不敏感）
     * @return diff结果 1:一致;  2:不一致; 0:解析失败;
     */
    public static Integer diffJson(String jsonA, String jsonB, List<String> ignoreDiffPath, boolean isOrder) {
        try {
            JsonNode nodeA = mapper.readTree(jsonA);
            JsonNode nodeB = mapper.readTree(jsonB);
            boolean isEqual = compareNodes(nodeA, nodeB, "", ignoreDiffPath, isOrder);
            return isEqual ? 1 : 2;
        } catch (Exception e) {
            log.error("diffJson error,", e);
            // 解析失败
            return 0;
        }
    }

    // 递归对比节点
    private static boolean compareNodes(JsonNode nodeA, JsonNode nodeB,
                                        String currentPath, List<String> ignorePaths,
                                        boolean isOrder) {
        // 检查当前路径是否需要忽略
        if (ignorePaths.contains(currentPath)) {
            return true; // 忽略该路径的对比
        }

        // 类型不同直接返回 false
        if (!nodeA.getNodeType().equals(nodeB.getNodeType())) {
            return false;
        }

        switch (nodeA.getNodeType()) {
            case OBJECT:
                return compareObjects((ObjectNode) nodeA, (ObjectNode) nodeB,
                        currentPath, ignorePaths, isOrder);
            case ARRAY:
                return compareArrays(nodeA, nodeB, currentPath, ignorePaths, isOrder);
            case STRING:
            case NUMBER:
            case BOOLEAN:
                return nodeA.equals(nodeB);
            default:
                return true;
        }
    }

    // 对比对象节点
    private static boolean compareObjects(ObjectNode objA, ObjectNode objB,
                                          String parentPath, List<String> ignorePaths,
                                          boolean isOrder) {
        Set<String> fieldsA = new HashSet<>();
        objA.fieldNames().forEachRemaining(fieldsA::add);
        Set<String> fieldsB = new HashSet<>();
        objB.fieldNames().forEachRemaining(fieldsB::add);

        // 字段数量不同
        if (fieldsA.size() != fieldsB.size()) {
            return false;
        }

        // 遍历所有字段
        for (String field : fieldsA) {
            String childPath = parentPath.isEmpty() ? field : parentPath + "." + field;
            if (!fieldsB.contains(field)) {
                return false;
            }

            JsonNode childA = objA.get(field);
            JsonNode childB = objB.get(field);

            if (!compareNodes(childA, childB, childPath, ignorePaths, isOrder)) {
                return false;
            }
        }
        return true;
    }

    // 对比数组节点
    private static boolean compareArrays(JsonNode arrA, JsonNode arrB,
                                         String parentPath, List<String> ignorePaths,
                                         boolean isOrder) {
        // 数组长度不同
        if (arrA.size() != arrB.size()) {
            return false;
        }

        List<JsonNode> listA = new ArrayList<>();
        List<JsonNode> listB = new ArrayList<>();

        arrA.forEach(listA::add);
        arrB.forEach(listB::add);

        // 若需忽略顺序，则排序
        if (!isOrder) {
            Comparator<JsonNode> comparator = Comparator.comparing(JsonNode::toString);
            listA.sort(comparator);
            listB.sort(comparator);
        }

        // 逐个对比元素
        for (int i = 0; i < listA.size(); i++) {
            String childPath = parentPath + "[" + i + "]";
            if (!compareNodes(listA.get(i), listB.get(i), childPath, ignorePaths, isOrder)) {
                return false;
            }
        }
        return true;
    }


}
