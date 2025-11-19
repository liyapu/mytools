package com.lyp.learn.utils.retry01;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author liyapu
 * @date 2025-11-19 17:18
 * @desc
 */
public class RetryDemo {

//    public List<SkuDTO> querySkuList(List<Long> skuIds) throws TException {
//        log.info("querySkuList skuIds = {}", JsonUtils.writeToString(skuIds));
//        if (CollectionUtils.isEmpty(skuIds)) {
//            return Collections.emptyList();
//        }
//        List<SkuDTO> skuDTOList = new ArrayList<>();
//        List<List<Long>> skuIdList = Lists.partition(skuIds, SKUID_LIMIT_SIZE);
//        for (List<Long> skuIdSubList : skuIdList) {
//            QuerySkuListRequest request = new QuerySkuListRequest();
//            request.setSkuIds(skuIdSubList);
//            request.setNeedAllCategory(true);
//            request.setNeedDictBrand(true);
//            log.info("productSkuQueryService.querySkuListBySkuIdList request = {}", JsonUtils.writeToString(request));
//            QuerySkuListResponse response = RetryUtils.call(CommonConstants.RETRY_TIMES, true,
//                    //如果response返回的code不是成功code，则进行重试
//                    obj -> Objects.isNull(obj) || (CommonConstants.SUCCESS != obj.getCode()),
//                    () -> productSkuQueryService.querySkuListBySkuIdList(request));
//            if (log.isDebugEnabled()) {
//                log.info("productSkuQueryService.querySkuListBySkuIdList response:{}", JsonUtils.writeToString(response));
//            } else {
//                List<Long> skuIdResponseList = ListUtils.emptyIfNull(response.getSkuDTOList())
//                        .stream()
//                        .map(SkuDTO::getSkuId)
//                        .collect(Collectors.toList());
//                log.info("productSkuQueryService.querySkuListBySkuIdList response skuIdResponseList:{}",
//                        JsonUtils.writeToString(skuIdResponseList));
//            }
//            ValidateUtils.checkResponse(response.getCode(), response.getMessage());
//            skuDTOList.addAll(response.getSkuDTOList());
//        }
//        return skuDTOList;
//    }
}
