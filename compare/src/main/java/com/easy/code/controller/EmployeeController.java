package com.easy.code.controller;

import com.easy.code.entity.Employee;
import com.easy.code.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 职员表(Employee)表控制层
 *
 * @author makejava
 * @since 2020-01-03 18:01:33
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Employee selectOne(Long id) {
        return this.employeeService.queryById(id);
    }

}