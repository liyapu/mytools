package com.lyp.learn.page;

import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * @author liyapu
 * @date 2025-09-17 14:27
 * @desc
 */
public class PageTest {

    public List<PersonPO> queryByPage(Long fdcId, String createDateStr) {
        PageQueryByLastMaxQueryId<PersonPO> pageQueryByLastMaxQueryId = (limit, lastMaxQueryId) ->
                this.queryByPageUsingLastMaxQueryId(fdcId, createDateStr, limit, lastMaxQueryId);

        int pageSize = 200;

        return PageQueryUtil.queryAllResultByPage(pageQueryByLastMaxQueryId,
                pageSize, "查询已存在 fdcDemandPlanByDayPO 发生死循环", PersonPO::getId);
    }


//    List<PersonPO> queryByPageUsingLastMaxQueryId(@Param("fdcId") Long fdcId,
//                                                              @Param("createDateStr") String createDateStr,
//                                                              @Param("limit") int limit,
//                                                              @Param("lastMaxQueryId") long lastMaxQueryId);

    List<PersonPO> queryByPageUsingLastMaxQueryId(Long fdcId,
                                                  String createDateStr,
                                                  int limit,
                                                  long lastMaxQueryId){
        return Lists.newArrayList();
    }


    //---------------------------------------------
    public List<Long> queryModifiedSkuIdList(Long fdcId, String createDateStr) {
//        PageQuery<Long> pageQuery = (offset, limit) -> mapper.queryModifiedSkuIdListByPage(fdcId, createDateStr, offset, limit);
        PageQuery<Long> pageQuery = (offset, limit) -> this.queryModifiedSkuIdListByPage(fdcId, createDateStr, offset, limit);
        int pageSize = 200;
        return PageQueryUtil.queryAllResultByPage(pageQuery, pageSize, String.format("查询所有修改的sku记录死循环,fdcId:%s",
                fdcId));
    }


//    List<Long> queryModifiedSkuIdListByPage(@Param("fdcId") Long fdcId, @Param("createDateStr") String createDateStr,
//                                            @Param("offset") int offset, @Param("limit") int limit);


    List<Long> queryModifiedSkuIdListByPage(Long fdcId, String createDateStr,
                                            int offset, int limit){
        return Lists.newArrayList();
    }


}
