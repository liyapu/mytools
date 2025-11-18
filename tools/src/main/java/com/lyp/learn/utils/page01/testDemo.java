package com.lyp.learn.utils.page01;

/**
 * @author liyapu
 * @date 2025-11-18 15:43
 * @desc
 */
public class testDemo {

//    /**
//     * 批量删除商品集明细表数据
//     *
//     * @param idList
//     */
//    private void batchDeleteGroupDetailById(List<Long> idList) {
//        if (CollectionUtils.isEmpty(idList)) {
//            return;
//        }
//        PageUtil.doConsumerByPage(part -> skuSingleDimensionGroupDetailMapper.batchDeleteByPrimaryKey(part),
//                idList, ArsConstants.GROUP_NUM_200);
//    }


//    /**
//     * 根据补货单列表获取补货单明细
//     */
//    public List<ArsDetailPO> queryArsDetailByBsIds(List<String> bsIdList) {
//        return PageUtil.doFunctionByPage(
//                partList -> arsDetailMapper.queryArsDetailByBsidList(partList),
//                bsIdList,
//                CommonTool.GROUP_NUM_100
//        );
//    }


//    /**
//     * 查询出清中高库存数据
//     * @param poiId
//     * @param skuIds
//     * @param timeStamp
//     * @return
//     */
//    public List<HighStockDTO> getHighStockList(Long poiId, List<Long> skuIds, Long timeStamp) {
//        if (null == poiId || CollectionUtils.isEmpty(skuIds)) {
//            return new ArrayList<>();
//        }
//        return PageUtil.doFunctionByPage(list -> getHighStocks(poiId, list, timeStamp), skuIds, GROUP_NUM_200, clearStorageQueryPool);
//    }

//
//    public Map<Long, List<String>> getFdcLockDayMap(Long poiId, List<Long> skuIdList) {
//        if (null == poiId || CollectionUtils.isEmpty(skuIdList)) {
//            return Maps.newHashMap();
//        }
//        List<PoiSkuLockDayInfoDTO> lockDayList =  PageUtil.doFunctionByPage(list -> getFdcLockDayInfo(poiId, list),
//                skuIdList,
//                CommonTool.GROUP_NUM_200,
//                queryFdcDailyDemandPool);
//        return CollectionUtils.emptyIfNull(lockDayList).stream().collect(Collectors.toMap(
//                PoiSkuLockDayInfoDTO::getSkuId,
//                PoiSkuLockDayInfoDTO::getLockDayList,
//                (v1, v2) -> v1
//        ));
//    }
}
