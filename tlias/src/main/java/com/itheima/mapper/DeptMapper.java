package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询全部部门信息
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into dept (name, create_time, update_time) VALUES (#{name} , #{createTime} , #{updateTime})")
    void add(Dept dept);

    /**
     * 根据ID查询部门
     * @param id
     * @return
     */
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 修改员工信息
     * @param dept
     */
    @Update("update dept set name = #{name} , update_time = #{updateTime} , create_time = #{createTime} where id = #{id}")
    void update(Dept dept);
}
