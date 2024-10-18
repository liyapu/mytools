package com.lyp.learn.mockdata;

import com.lyp.learn.mockdata.bean.Student;
import com.lyp.learn.mockdata.bean.StudentPageResult;
import com.lyp.learn.mockdata.bean.StudentRequest;
import com.lyp.learn.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyapu
 * @date 2024-09-30 19:53
 * @description
 */
@Slf4j
public class StudentService {

    private static List<Student> studentAllList = new ArrayList<>();

    static {
        for (int i = 1; i <= 50; i++) {
            Student student = new Student();
            student.setId(i);
            student.setAge("age" + i);
            student.setName("student" + i);
            studentAllList.add(student);
        }
    }


    public static List<Student> getStudentList(String name, Integer age, Integer offset, Integer limit) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("getStudentList InterruptedException");
        }
        List<Student> tempResult = studentAllList.stream()
//                .filter(s -> s.getName().contains(name))
                .collect(Collectors.toList());
        if (tempResult.size() > offset) {
            if (tempResult.size() > offset + limit) {
                tempResult = tempResult.subList(offset, offset + limit);
            } else {
                tempResult = tempResult.subList(offset, tempResult.size());
            }
        } else {
            tempResult = new ArrayList<>();
        }
        System.out.println("getStudentList " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " name:" + name + " age:" + age + " offset:" + offset + " limit:" + limit + " tempResult:" + JsonUtil.writeToString(tempResult));
        return tempResult;
    }

    public static List<Student> getStudentListThrowException(String name, Integer age, Integer offset, Integer limit) throws Exception {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("getStudentList InterruptedException");
        }
        List<Student> tempResult = studentAllList.stream()
//                .filter(s -> s.getName().contains(name))
                .collect(Collectors.toList());
        if (tempResult.size() > offset) {
            if (tempResult.size() > offset + limit) {
                tempResult = tempResult.subList(offset, offset + limit);
            } else {
                tempResult = tempResult.subList(offset, tempResult.size());
            }
        } else {
            tempResult = new ArrayList<>();
        }
        System.out.println("getStudentListThrowException " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " name:" + name + " age:" + age + " offset:" + offset + " limit:" + limit + " tempResult:" + JsonUtil.writeToString(tempResult));
        return tempResult;
    }

    public static StudentPageResult getStudentPageResultList(String name, Integer age, Integer offset, Integer limit) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("getStudentList InterruptedException");
        }
        List<Student> tempResult = studentAllList.stream()
//                .filter(s -> s.getName().contains(name))
                .collect(Collectors.toList());
        //总条数
        long total = tempResult.size();

        if (tempResult.size() > offset) {
            if (tempResult.size() > offset + limit) {
                tempResult = tempResult.subList(offset, offset + limit);
            } else {
                tempResult = tempResult.subList(offset, tempResult.size());
            }
        } else {
            tempResult = new ArrayList<>();
        }
        StudentPageResult result = new StudentPageResult();
        result.setCode(0);
        result.setMsg("success");

        StudentPageResult.StudentPageInfo studentPageInfo = new StudentPageResult.StudentPageInfo();
        studentPageInfo.setTotal(total);
        studentPageInfo.setStudentList(tempResult);

        result.setData(studentPageInfo);
        System.out.println("getStudentPageResultList " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " name:" + name + " age:" + age + " offset:" + offset + " limit:" + limit + " currentThread:" + Thread.currentThread().getName() + " result:" + JsonUtil.writeToString(result));
        return result;
    }


    public static StudentPageResult getStudentPageResultList(StudentRequest request) {
        String name = request.getName();
        Integer age = request.getAge();
        Integer offset = request.getOffset();
        Integer limit = request.getLimit();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("getStudentList InterruptedException");
        }
        List<Student> tempResult = studentAllList.stream()
//                .filter(s -> s.getName().contains(name))
                .collect(Collectors.toList());
        //总条数
        long total = tempResult.size();

        if (tempResult.size() > offset) {
            if (tempResult.size() > offset + limit) {
                tempResult = tempResult.subList(offset, offset + limit);
            } else {
                tempResult = tempResult.subList(offset, tempResult.size());
            }
        } else {
            tempResult = new ArrayList<>();
        }
        StudentPageResult result = new StudentPageResult();
        result.setCode(0);
        result.setMsg("success");

        StudentPageResult.StudentPageInfo studentPageInfo = new StudentPageResult.StudentPageInfo();
        studentPageInfo.setTotal(total);
        studentPageInfo.setStudentList(tempResult);

        result.setData(studentPageInfo);
        System.out.println("getStudentPageResultList " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " name:" + name + " age:" + age + " offset:" + offset + " limit:" + limit + " currentThread:" + Thread.currentThread().getName() + " result:" + JsonUtil.writeToString(result));
        return result;
    }


}
