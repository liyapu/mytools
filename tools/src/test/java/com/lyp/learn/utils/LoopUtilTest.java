package com.lyp.learn.utils;

import cn.hutool.json.JSONUtil;
import com.lyp.learn.bean.Man;
import com.lyp.learn.bean.ManResponse;
import java.util.List;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liyapu
 * @date 2021-10-30 17:25
 * @desc
 */
public class LoopUtilTest extends TestCase {

    /**
     * 测试 分页查询数据
     */
    public void testQueryManByPage() {
        Man man = new Man();
        ManResponse manResponse1 = man.queryManByPage("工人工作工厂", 0, 10);
        System.out.println("manResponse1 =  " + JsonUtil.writeToString(manResponse1));

        ManResponse manResponse2 = man.queryManByPage("工人工作工厂", 10, 10);
        System.out.println("manResponse2 =  " + JsonUtil.writeToString(manResponse2));

        ManResponse manResponse3 = man.queryManByPage("工人工作工厂", 20, 10);
        System.out.println("manResponse3 =  " + JsonUtil.writeToString(manResponse3));

    }

    /**
     * 循环获取全部数据
     *     根据 total
     */
    public void testGetFullDataByTotalCount() {
        Man man = new Man();
        List<Man> manList =
                LoopUtil.getFullDataByTotalCount((offset, limit) -> man.queryManByPage("工人工作工厂", offset, limit),
                        response -> (long) response.getData().getTotal(),
                        response -> response.getData().getManList(),
                        PageUtils.LIMIT_5);

        System.out.printf("manList = " + JsonUtil.writeToString(manList));
    }

    /**
     * 循环获取全部数据
     *    没有total
     */
    public void  testGetFullDataWithNoTotal(){
        Man man = new Man();
        List<Man> manList = LoopUtil.getFullDataWithNoTotal((offset, limit) -> man.queryManByPage("工人", offset, limit),
                res -> res.getData().getManList(),
                PageUtils.LIMIT_5);
        System.out.println("manList------------"+ JsonUtil.writeToString(manList));
    }

}