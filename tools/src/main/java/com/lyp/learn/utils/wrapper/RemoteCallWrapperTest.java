package com.lyp.learn.utils.wrapper;

import com.lyp.learn.mockdata.StudentService;
import com.lyp.learn.mockdata.bean.StudentPageResult;
import com.lyp.learn.mockdata.bean.StudentRequest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author liyapu
 * @date 2024-10-11 11:04
 * @description
 */
public class RemoteCallWrapperTest {

    @Resource
    private RemoteCallWrapper remoteCallWrapper;

    private static final int SUCCESS_CODE = 0;

    @Test
    public void testRemoteCallWrapper001() {
        StudentRequest request = new StudentRequest();
        StudentPageResult studentResult = remoteCallWrapper.retryCall(request, t -> StudentService.getStudentPageResultList(request),
                "StudentService.getStudentPageResultList", "getStudentPageResultList",
                GsonUtil::toJsonStr, GsonUtil::toJsonStr,
                r -> r != null && r.getCode() == SUCCESS_CODE);
    }
}
