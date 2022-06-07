package com.lyp.learn.bean;

import com.lyp.learn.utils.JsonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        int id = 1;
        int end = 38;
        for (char i = 'a'; i <'z' ; i++) {
            for (char j = 'a'; j < 'z'; j++) {
                manList.add(Man.of(id++, i+""+j));
                if(i == end){
                    break;
                }
            }
            if(i == end){
                break;
            }
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

    /**
     * 根据 id列表 查询
     * @param ids
     * @return
     */
    public ManListResponse queryByIds(List<Integer> ids){
        System.out.println("queryByIds ===== ids:::" + JsonUtil.writeToString(ids));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ManListResponse manListResponse = new ManListResponse();
        manListResponse.setCode(0);
        manListResponse.setMsg("成功");

        List<Man> dataList = new ArrayList<>();
        Map<Integer, Man> id$ManMap =
            manList.stream().collect(Collectors.toMap(Man::getId, Function.identity(), (k1, k2) -> k1));
        for (Integer id : ids) {
            Man man = id$ManMap.get(id);
            dataList.add(man);
        }
        manListResponse.setData(dataList);
        System.out.println("queryByIds ===== manListResponse::" + JsonUtil.writeToString(manListResponse));

        return manListResponse;
    }


    /**
     * 根据 id 查询
     *
     * @param id
     * @return
     */
    public Man queryById(Integer id) {
        System.out.println("queryById ===== id:::" + JsonUtil.writeToString(id));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<Integer, Man> id$ManMap =
            manList.stream().collect(Collectors.toMap(Man::getId, Function.identity(), (k1, k2) -> k1));
        return id$ManMap.get(id);
    }


}
