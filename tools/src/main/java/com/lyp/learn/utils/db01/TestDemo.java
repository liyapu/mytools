package com.lyp.learn.utils.db01;

import java.util.List;
import java.util.Objects;

/**
 * @author liyapu
 * @date 2025-11-19 12:08
 * @desc
 */
public class TestDemo {


//    public void test02(){
//        try {
//            //查询数据库中存在的配置
//            List<NewProductSafeStockCityConfigPO> dbConfigPOList = selectExistingConfigs(configPOList);
//
//            //构建持久化BO
//            CommonPersistenceBO<NewProductSafeStockCityConfigPO> persistenceBO =
//                    PersistentTool.getCommonPersistenceBO(
//                            dbConfigPOList,
//                            configPOList,
//                            this::buildConfigKey,
//                            NewProductSafeStockCityConfigPO::getId,
//                            NewProductSafeStockCityConfigPO::setId
//                    );
//
//            PersistenceBO<NewProductSafeStockCityConfigPO> newProductSafeStockCityConfigPOPersistenceBO = newProductSafeStockCommonService.convert2PersistenceBO(persistenceBO);
//            //持久化数据
//            newProductSafeStockCityConfigRepository.processPersistenceDate(newProductSafeStockCityConfigPOPersistenceBO);
//
//            // 上报日志
//            metricsGateway.writeOpLog(
//                    LogModuleEnum.NEW_PRODUCT_SAFE_STOCK_CONFIG,
//                    LogSubModuleEnum.CITY,
//                    filterValidConfigPOList(dbConfigPOList),
//                    configPOList,
//                    this::buildConfigKey,
//                    operator
//            );
//            log.info("新品安全库存配置更新成功, operator: {}, 影响配置数: {}", operator, configPOList.size());
//        } finally {
//            squirrelGateway.unlock(NewProductSafeStockConfigConstant.CITY_LOCK_PARAM, key);
//        }
//    }
//
//    public <T> PersistenceBO<T> convert2PersistenceBO(CommonPersistenceBO<T> commonPersistenceBO) {
//        return PersistenceBO.<T>builder()
//                .insertList(commonPersistenceBO.getInsertList())
//                .deleteList(commonPersistenceBO.getDeleteList())
//                .updateList(commonPersistenceBO.getUpdateList())
//                .build();
//    }
//
//    /**
//     * 批量处理持久化数据，包含新增、更新、删除
//     *
//     * @param persistenceBO persistenceBO
//     */
//    public void processPersistenceDate(PersistenceBO<NewProductSafeStockCityConfigPO> persistenceBO) {
//        if (Objects.isNull(persistenceBO)) {
//            return;
//        }
//        batchInsert(persistenceBO.getInsertList());
//        batchUpdate(persistenceBO.getUpdateList());
//        batchInvalid(persistenceBO.getDeleteList());
//    }

}
