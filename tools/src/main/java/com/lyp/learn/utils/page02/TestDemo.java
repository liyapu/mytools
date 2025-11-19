package com.lyp.learn.utils.page02;

import java.util.List;

/**
 * @author liyapu
 * @date 2025-11-18 15:49
 * @desc
 */
public class TestDemo {
//    public List<Long> queryModifiedSkuIdList(Long fdcId, String createDateStr) {
//        PageQuery<Long> pageQuery = (offset, limit) -> mapper.queryModifiedSkuIdListByPage(fdcId, createDateStr,
//                offset, limit);
//        int pageSize = Constants.GROUP_NUM_5000;
//        return PageQueryUtil.queryAllResultByPage(pageQuery, pageSize, String.format("查询所有修改的sku记录死循环,fdcId:%s",
//                fdcId));
//    }

//    /**
//     * 分页查询已存在的配置
//     */
//    private List<NewProductSafeStockCityConfigPO> selectExistingConfigsByPage(NewProductSafeStockCityConfigQueryParam queryParam) {
//        // 使用 PageQuery 接口定义查询逻辑
//        PageQuery<NewProductSafeStockCityConfigPO> pageQuery = (offset, limit) -> {
//            queryParam.setOffset(offset);
//            queryParam.setLimit(limit);
//            return newProductSafeStockCityConfigRepository.selectAllDataByParam(queryParam);
//        };
//
//        // 使用 PageQueryUtil 工具类进行分页查询
//        return PageQueryUtil.queryAllResultByPage(
//                pageQuery,
//                CommonTool.GROUP_NUM_200,
//                "查询新品安全库存配置死循环"
//        );
//    }



//    public List<FdcDemandPlanSimpleDTO> queryByPagePoiId(Long poiId, String createDateStr) {
//        PageQueryByLastMaxQueryId<FdcDemandPlanSimpleDTO> pageQueryByLastMaxQueryId = (limit, lastMaxQueryId) -> mapper.queryByPagePoiIdUsingLastMaxQueryId(poiId, createDateStr, limit, lastMaxQueryId);
//
//        int pageSize = batchPoiQueryFdcDemandSize;
//        return PageQueryUtil.queryAllResultByPage(pageQueryByLastMaxQueryId,
//                pageSize, "查询已存在 fdcDemandPlanPO 发生死循环", FdcDemandPlanSimpleDTO::getId);
//    }
}
