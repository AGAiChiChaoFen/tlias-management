package com.itheima.controller;

import com.itheima.annocation.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 员工列表查询
     */
    @Log
    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name , Short gender ,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin ,
                       @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate end) {
        log.info("员工列表查询，参数：{}，{}",page,pageSize);

        PageBean pageBean = empService.page(page , pageSize , name , gender , begin , end);

        return Result.success(pageBean);
    }

    /**
     * 删除员工信息
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除员工信息：{}" , ids);

        empService.delete(ids);

        return Result.success();
    }

    /**
     * 新增员工信息
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工信息：{}",emp);

        empService.save(emp);

        return Result.success();

    }

    /**
     * 根据ID查询员工信息
     */
    @Log
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer  id) {

        log.info("根据ID查询员工信息：{}",id);

        Emp emp = empService.getById(id);

        return Result.success(emp);

    }

    /**
     * 修改员工信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工信息：{}",emp);

        empService.update(emp);

        return Result.success();
    }
}
