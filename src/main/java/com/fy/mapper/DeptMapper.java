package com.fy.mapper;

import com.fy.pojo.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface DeptMapper {
    public List<Dept> findAll();

    public void deleteDept(String [] hhDeptIds);

    public void updateState(@Param("hhDeptIds")String[] hhDeptIds, @Param("hhDeptStatus")int hhDeptStatus);

    public void saveDept(Dept dept);

    public Dept findDeptById(String hhDeptId);

    //public List<Dept> findParentList(String hhDeptId);

    public void updateDept(Dept dept);
}
