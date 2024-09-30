package com.lyp.learn.utils.looputilcompletablefuturelearn.package02;

import com.lyp.learn.mockdata.StudentService;
import com.lyp.learn.mockdata.UserService;
import com.lyp.learn.mockdata.bean.Student;
import com.lyp.learn.mockdata.bean.User;
import com.lyp.learn.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
        long start = System.currentTimeMillis();
        List<Student> resultList = LoopUtils.getFullDataWithNoTotal(
                (offset, limit) -> StudentService.getStudentList("2", 18, offset, limit),
                Function.identity(), 10);
        System.out.println("resultList ====== " + JsonUtil.writeToString(resultList));
        System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000 + "s");
    }

    @Test
    public void testGetFullDataWithNoTotal02() {
        long start = System.currentTimeMillis();
        List<Student> resultList = LoopUtils.getFullDataWithNoTotal(
                (offset, limit) -> StudentService.getStudentList("2", 18, offset, limit),
                Function.identity(), 20);
        System.out.println("resultList ====== " + JsonUtil.writeToString(resultList));
        System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000 + "s");
    }

    @Test
    public void testGetFullDataWithNoTotal1001() {
        long start = System.currentTimeMillis();
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
        System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000 + "s");

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
        long start = System.currentTimeMillis();
        List<Student> resultList = LoopUtils.getFullDataByTotalCount(
                (offset, limit) -> StudentService.getStudentPageResultList("2", 18, offset, limit),
                result -> result.getData().getTotal(),
                result -> result.getData().getStudentList(),
                10);
        System.out.println("resultList ====== " + JsonUtil.writeToString(resultList));
        System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000 + "s");

    }

    @Test
    public void testGetFullDataByTotalCountWithMutiThread0001() {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Student> resultList = LoopUtils.getFullDataByTotalCountWithMutiThread(
                (offset, limit) -> StudentService.getStudentPageResultList("2", 18, offset, limit),
                result -> result.getData().getTotal(),
                result -> result.getData().getStudentList(),
                10,
                executorService,
                2
        );
        System.out.println("resultList ====== " + JsonUtil.writeToString(resultList));
        System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000 + "s");
    }

    /**
     * 多线程 请求 学生和用户, 学生多线程和用户多线程 串行
     */
    @Test
    public void testGetFullDataByTotalCountWithMutiThread0002() {
        long studentStart = System.currentTimeMillis();
        ExecutorService studentExecutorService = Executors.newFixedThreadPool(10);
        ExecutorService userExecutorService = Executors.newFixedThreadPool(10);

        List<Student> studentResultList = LoopUtils.getFullDataByTotalCountWithMutiThread(
                (offset, limit) -> StudentService.getStudentPageResultList("2", 18, offset, limit),
                result -> result.getData().getTotal(),
                result -> result.getData().getStudentList(),
                10,
                studentExecutorService,
                2
        );
        System.out.println("studentResultList ====== " + JsonUtil.writeToString(studentResultList));
        long studentEnd = System.currentTimeMillis();
        System.out.println("学生耗时：" + (studentEnd - studentStart) / 1000 + "s");

        long userStart = System.currentTimeMillis();
        List<User> userResultList = LoopUtils.getFullDataByTotalCountWithMutiThread(
                (offset, limit) -> UserService.getUserPageResultList("1", offset, limit),
                result -> result.getData().getTotal(),
                result -> result.getData().getUserList(),
                10,
                userExecutorService,
                2
        );
        System.out.println("userResultList ====== " + JsonUtil.writeToString(userResultList));
        long userEnd = System.currentTimeMillis();
        System.out.println("学生耗时：" + (userEnd - userStart) / 1000 + "s");
        System.out.println("总耗时：" + (userEnd - studentStart) / 1000 + "s");
    }


}
