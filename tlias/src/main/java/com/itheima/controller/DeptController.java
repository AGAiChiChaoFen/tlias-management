package com.itheima.controller;

import com.itheima.annocation.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门信息
     */
    @Log
    @GetMapping
    public Result list() {

        log.info("查询全部部门信息：");

        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    /**
     * 删除部门信息
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        log.info("删除部门信息：{}",id);

        deptService.delete(id);

        return Result.success();
    }

    /**
     * 新增部门
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {     //接收json数据要用@RequestBosy注解
        log.info("新增部门：{}",dept);

        deptService.add(dept);

        return Result.success(dept);
    }

    /**
     * 根据ID查询部门
     */
    @Log
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询部门：{}",id);

        Dept dept = deptService.getById(id);

        return Result.success(dept);
    }

    /**
     * 修改部门信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {

        log.info("修改员工信息：{}",dept);

        deptService.update(dept);

        return Result.success();
    }
}
