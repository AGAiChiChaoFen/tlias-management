package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        //获取总记录数
//        Long count = empMapper.count();
//
//        //获取结果列表
//        Integer start = (page - 1)*pageSize;
//        List<Emp> list = empMapper.page(start,pageSize);
//
//        //封装结果
//        PageBean pageBean = new PageBean(count ,list);
//
//        return pageBean;
//    }

    /**
     * 用pageHelper插件查询
     */
    public PageBean page(Integer page , Integer pageSize, String name ,
                         Short gender , LocalDate begin , LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Emp> empList = empMapper.list(name , gender , begin , end);
        Page<Emp> p = (Page<Emp>)empList;

        //封装结果

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

    /**
     * 批量删除员工信息
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /**
     * 新增员工信息
     * @param emp
     */
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
    }

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 更新员工信息
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    /**
     * 登录功能
     * @param emp
     * @return
     */
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
