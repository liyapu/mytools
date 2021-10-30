package com.lyp.learn.bean;

import cn.hutool.core.util.RandomUtil;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.IOP.Encoding;

/**
 * @author liyapu
 * @date 2021-10-30 17:26
 * @desc
 */
public class Man {
    private int id;
    private String name;

    public static List<Man> manList = new ArrayList<>();

    static {
        for (int i = 1; i < 35; i++) {
            manList.add(Man.of(i, RandomUtil.randomString(3)));
        }
    }

    public static Man of(int id,String name){
       return new Man(id,name);
    }

    public Man(){

    }

    public Man(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 分页查询
     * @param factoryName 工厂名称
     * @param offset 偏移量，从0开始
     * @param limit 每页大小
     * @return
     */
    public ManResponse queryManByPage(String factoryName,int offset,int limit){
        int total = manList.size();
        List<Man> mans = new ArrayList<>();
        int endIndex = offset + limit;
        if(endIndex > total){
            endIndex = total;
        }
        mans = manList.subList(offset,endIndex);

        ManDTO manDTO = new ManDTO();
        manDTO.setTotal(total);
        manDTO.setManList(mans);

        ManResponse manResponse = new ManResponse();
        manResponse.setCode(0);
        manResponse.setMsg("成功");
        manResponse.setData(manDTO);
        return manResponse;
    }



}
