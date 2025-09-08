package com.lyp.learn.model;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author liyapu
 * @date 2025-08-29 14:10
 * @desc
 */
public class MyTest {


//    public List<TPoiSkuPromotionDTO> queryPoiSkuPromotionGray(long poiId, List<Long> skuIdList) {
//        if (CollectionUtils.isEmpty(skuIdList)) {
//            return Lists.newArrayList();
//        }
//        GrayConfig config = new GrayConfig();
//        config.setPhase(MccConfig.getNewPromotionMigrateReaderGray());
//        config.setPercentage(newPromotionMigrateGrayPercent);
//        return GrayUtils.<List<TPoiSkuPromotionDTO>>createGrayReader("queryPoiSkuPromotionGray", QUERY_POI_SKU_PROMOTION_GRAY, config)
//                .oldReader(() -> queryPoiSkuPromotionListOld(poiId, skuIdList))
//                .newReader(() -> queryPoiSkuPromotionListNew(poiId, skuIdList))
//                .asyncComparator(new AsyncComparator<List<TPoiSkuPromotionDTO>>() {
//                    @Override
//                    public boolean compare(List<TPoiSkuPromotionDTO> oldResult, List<TPoiSkuPromotionDTO> newResult) {
//                        return queryPoiSkuPromotionGrayCompare(oldResult, newResult, skuIdList);
//                    }
//
//                    @Override
//                    public void actionWhenCompareFail(List<TPoiSkuPromotionDTO> oldResult, List<TPoiSkuPromotionDTO> newResult) {
//                    }
//                }).read();
//    }
}
