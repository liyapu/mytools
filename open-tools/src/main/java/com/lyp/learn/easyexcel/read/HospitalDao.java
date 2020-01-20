package com.lyp.learn.easyexcel.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-20 17:46
 */
public class HospitalDao {
    Logger log = LoggerFactory.getLogger(getClass());

    int save(List<Hospital> hospitalList){
        log.info("保存医院数据，{} 条",hospitalList.size());
        return hospitalList.size();
    }
}
