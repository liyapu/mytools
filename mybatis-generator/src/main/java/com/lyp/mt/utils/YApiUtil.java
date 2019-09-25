package com.lyp.mt.utils;

import com.alibaba.fastjson.JSONObject;
import com.lyp.mt.entity.FieldEntity;
import com.lyp.mt.entity.yapi.*;
import com.lyp.mt.entity.yapi.business.TableShowFieldVo;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-09 20:51
 */
public class YApiUtil {

    //排除的字段
    static List<String> excludeFields = new ArrayList<>();

    static {
        excludeFields.add("id");
        excludeFields.add("valid");
        excludeFields.add("createTime");
        excludeFields.add("updateTime");
        excludeFields.add("tableName");
        excludeFields.add("create_time");
        excludeFields.add("update_time");
        excludeFields.add("table_name");
    }

    public static Page getPage() {
        Page page = new Page();
        page.setType("object");
        page.setDescription("分页时用到的页面信息");

        List<String> required = new ArrayList<>();
        required.add("count");
        required.add("pageCount");
        required.add("page");
        required.add("head");
        required.add("hasNextPage");
        required.add("currentPageOffset");
        required.add("pageSize");
        required.add("tail");
        page.setRequired(required);

        Map<String, Object> properties = new HashMap<>();
        properties.put("count", new Field("integer", "总数"));
        properties.put("currentPageOffset", new Field("integer", "当前页的起始位置"));
        properties.put("hasNextPage", new Field("boolean", "是否有下一页"));
        properties.put("head", new Field("boolean", "是否是首页"));
        properties.put("page", new Field("integer", "当前页"));
        properties.put("pageCount", new Field("integer", "总页数"));
        properties.put("pageSize", new Field("integer", "每页数据量"));
        properties.put("tail", new Field("boolean", "是否是尾页"));
        page.setProperties(properties);
        return page;
    }

    public static Root getRoot() {
        Root root = new Root();
        root.setType("object");
        root.setTitle("empty object");
        List<String> required = new ArrayList<>();
        required.add("code");
        required.add("message");
        required.add("result");
        root.setRequired(required);
        return root;
    }

    public static Root getRootPage() {
        Root root = new Root();
        root.setType("object");
        root.setTitle("empty object");
        List<String> required = new ArrayList<>();
        required.add("code");
        required.add("message");
        required.add("result");
        required.add("page");
        root.setRequired(required);
        return root;
    }

    public WebResult getWebResult() {
        WebResult wr = new WebResult();
        wr.setCode(new Field("integer", "状态码"));
        wr.setMessage(new Field("string", "状态码对应的描述信息"));

        return wr;
    }

    /**
     * 财务指标行业排名
     */
    @Test
    public void test01() {

        WebResult wr = getWebResult();
        Page page = getPage();
        Result result = getResult();
        wr.setPage(page);
        wr.setResult(result);

        Root root = getRootPage();
        root.setProperties(wr);
        System.out.println("-----------------");
        System.out.println(JSONObject.toJSONString(root));

        MyFileUtil.toFileJson(root,"rootest");

    }

    public Result getResult() {
        Result result = new Result();

        result.setType("object");
        result.setDescription("结果集");

        Map<String,Object> properties = new HashMap<>();
        Data data = getData();
        Data title = getTitle();
        properties.put("data",data);
        properties.put("title",title);
        result.setProperties(properties);

        List<String> required = new ArrayList<>();
        required.add("data");
        required.add("title");
        return result;
    }

    public Data getTitle() {
        Data title = new Data();
        Items items = getTitleItems();
        title.setType("array");
        title.setDescription("表头信息");
        title.setItems(items);
        return title;
    }

    public Items getTitleItems() {
        Items items = new Items();
        items.setType("object");
        List<String> required = new ArrayList<>();
        items.setRequired(required);
        Map<String, Field> properties = new HashMap<>();
        items.setProperties(properties);

        List<FieldEntity> fieldEntities = MySqlMetaDataUtil.listByTableNameSql("table_show_field");
        //System.out.println(fieldEntities);
        for (FieldEntity fe : fieldEntities) {
            String fieldName = FieldUtil.lineToHump(fe.getField());
            String comment = fe.getComment();
            if (excludeFields.contains(fieldName)) {
                continue;
            }
            required.add(fieldName);
            properties.put(fieldName, new Field("string", comment));
        }
        return items;
    }

    public Data getData() {
        Data data = new Data();
        data.setType("array");
        data.setDescription("数据信息");
        Items items = getDataItems();
        data.setItems(items);
        return data;
    }

    public Items getDataItems() {
        Items items = new Items();
        items.setType("object");
        List<String> required = new ArrayList<>();
        items.setRequired(required);
        Map<String, Field> properties = new HashMap<>();
        items.setProperties(properties);

//        List<FieldEntity> fieldEntities = MyMetaDataUtil2.listByTableNameSql("financial_indicator_industry_ranking");
        List<FieldEntity> fieldEntities = MySqlMetaDataUtil.listByTableNameSql("report_indicator");
        //System.out.println(fieldEntities);
        for (FieldEntity fe : fieldEntities) {
            String fieldName = FieldUtil.lineToHump(fe.getField());
            String comment = fe.getComment();
            if (excludeFields.contains(fieldName)) {
                continue;
            }
            required.add(fieldName);
            properties.put(fieldName, new Field("string", comment));
        }
       // System.out.println(JSONObject.toJSONString(items));
        return items;
    }

    public List<TableShowFieldVo> getTableShowFieldByTableName(String tableName) {
        List<TableShowFieldVo> tableShowFieldVos = new ArrayList<>();
        TableShowFieldVo tsfv = null;
        Connection connection = MySqlMetaDataUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select field_name,field_show,field_order from table_show_field where valid =1 and table_name = '" + tableName + "' order by field_order ASC";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tsfv = new TableShowFieldVo();
                String name = rs.getString("field_name");
                String show = rs.getString("field_show");
                int order = rs.getInt("field_order");
                tsfv.setFieldName(name);
                tsfv.setFieldShow(show);
                tsfv.setFieldOrder(order);
                tableShowFieldVos.add(tsfv);
            }
        } catch (Exception e) {
            System.out.println("getTableShowFieldByTableName error" + e);
        } finally {
            MySqlMetaDataUtil.close(connection, ps, rs);
        }
        return tableShowFieldVos;
    }




    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(getPage()));
    }
}
