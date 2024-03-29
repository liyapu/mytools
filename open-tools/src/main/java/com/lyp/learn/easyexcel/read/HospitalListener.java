package com.lyp.learn.easyexcel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyapu
 * @description: 模板的读取类
 * @date 2020-01-20 17:45
 */
// 有个很重要的点 HospitalListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class HospitalListener extends AnalysisEventListener<Hospital> {
    private static final Logger log = LoggerFactory.getLogger(HospitalListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Hospital> list = new ArrayList<Hospital>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private HospitalDao hospitalDao;

    public HospitalListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        hospitalDao = new HospitalDao();
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param hospitalDao
     */
    public HospitalListener(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    /**
     * 获取表头字段 headRowNumber(1) 写几会调用几次
     * @param headMap
     * @param context
     */
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("headMap=============headMap:{},context:{}",JSON.toJSONString(headMap),JSON.toJSONString(context));
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(Hospital data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        hospitalDao.save(list);
        log.info("存储数据库成功！");
    }
}
