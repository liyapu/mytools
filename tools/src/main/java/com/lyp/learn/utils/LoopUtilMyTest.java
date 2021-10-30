package com.lyp.learn.utils;

import com.lyp.learn.bean.User;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static java.util.stream.Collectors.toList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;

/**
 * @author liyapu
 * @date 2021-10-29 20:26
 * @desc
 */
public class LoopUtilMyTest {

    //=================================================================================
//    @Test
//    public void testGetFullDataByTotalCount(){
//        searchUserAll();
//    }

//    public List<Integer> searchUserAll(Long supplierId, Long poiId,Long firstCategoryId) throws TException {
//        List<User> UserS = LoopUtil.getFullDataByTotalCount((offset, limit) -> searchUserList(supplierId, poiId,offset, limit),
//                res -> res.getTotal().longValue(),
//                res -> res.getUserList(),
//                PageUtils.LIMIT_200);
//        return UserS.stream().map(User::getId).distinct().collect(toList());
//    }

    /**
     * 单个 分页的方法请求
     */
//    private SearchUserResponse searchUserList(Long id, Long userId, Long genderId,int offset, int limit) throws TException {
//        Object request;
//
//        Object response = userService.getUserDTO(request);
//        return response;
//    }
}


