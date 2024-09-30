package com.lyp.learn.utils.looputilcompletablefuturelearn.package02;

import com.lyp.learn.mockdata.StudentService;
import com.lyp.learn.mockdata.bean.Student;
import com.lyp.learn.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author liyapu
 * @date 2024-09-30 20:02
 * @description
 */
@Slf4j
public class LoopUtilsTest {


    @Test
    public void testGetFullDataWithNoTotal01() {
        List<Student> resultList = LoopUtils.getFullDataWithNoTotal(
                (offset, limit) -> StudentService.getStudentList("2", 18, offset, limit),
                Function.identity(), 10);
        System.out.println("resultList ====== " + JsonUtil.writeToString(resultList));
    }

    @Test
    public void testGetFullDataWithNoTotal02() {
        List<Student> resultList = LoopUtils.getFullDataWithNoTotal(
                (offset, limit) -> StudentService.getStudentList("2", 18, offset, limit),
                Function.identity(), 20);
        System.out.println("resultList ====== " + JsonUtil.writeToString(resultList));
    }

    @Test
    public void testGetFullDataWithNoTotal1001() {
        List<Student> resultList = LoopUtils.getFullDataWithNoTotal(
                (offset, limit) -> {
                    try {
                        return StudentService.getStudentListThrowException("2", 18, offset, limit);
                    } catch (Exception e) {
                        log.error("getFullDataWithNoTotal1001 error", e);
                        return Collections.emptyList();
                    }
                },
                Function.identity(), 20);
        System.out.println("resultList ====== " + JsonUtil.writeToString(resultList));
    }


    @Test
    public void testProcessByOffsetLimit01() {
        LoopUtils.processByOffsetLimit(
                (offset, limit) -> StudentService.getStudentList("2", 18, offset, limit),
                10,
                studentList -> {
                    System.out.println("studentList ===单次处理=== " + JsonUtil.writeToString(studentList));
                });
    }

    @Test
    public void testGetFullDataByTotalCount001() {
        List<Student> resultList = LoopUtils.getFullDataByTotalCount(
                (offset, limit) -> StudentService.getStudentPageResultList("2", 18, offset, limit),
                result -> result.getData().getTotal(),
                result -> result.getData().getStudentList(),
                10);
        System.out.println("resultList ====== " + JsonUtil.writeToString(resultList));
    }


}
