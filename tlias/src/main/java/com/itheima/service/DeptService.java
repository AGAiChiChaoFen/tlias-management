package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 查询全部部门信息
     * @return
     */
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据ID查询部门
     * @param id
     * @return
     */
    Dept getById(Integer id);

    /**
     * 修改部门信息
     * @param dept
     */
    void update(Dept dept);
}
