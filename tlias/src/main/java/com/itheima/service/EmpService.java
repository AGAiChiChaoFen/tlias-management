package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize ,
                  String name , Short gender , LocalDate begin , LocalDate end);

    /**
     * 批量删除员工信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工信息
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    Emp getById(Integer id);


    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 登录功能
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
