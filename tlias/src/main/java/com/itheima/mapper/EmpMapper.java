package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 获取总记录数
     */
    //@Select("select count(*) from emp")
    //public Long count();

    /**
     * 获取结果列表
     */
    //@Select("select * from emp limit #{start} , #{pageSize} ")
    //public List<Emp> page(Integer start,Integer pageSize);

    /**
     * pageHelper执行分页查询
     */
//    @Select("select * from emp")
    public List<Emp> list(String name , Short gender , LocalDate begin , LocalDate end);

    /**
     * 批量删除员工信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工信息
     * @param emp
     */
    @Insert("insert into emp " +
            "(id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values (null,#{username} ,#{password},#{name},#{gender} , #{image} , #{job} , #{entrydate} , #{deptId} ,#{createTime} , #{updateTime}) ")
    void insert(Emp emp);

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @Select("select * from emp where id = #{id}")
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
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据部门ID删除部门下员工的信息
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
