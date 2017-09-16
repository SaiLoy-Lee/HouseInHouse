package com.fy.service;

import com.fy.pojo.Dept;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface DeptService {
    public List<Dept> findAll();

    public void deleteDept(String[] hhDeptIds);

    public void updateStatus(String[] hhDeptIds,int hhDeptStatus);

    public void saveDept(Dept dept);

    public Dept findDeptById(String hhDeptId);

   // public List<Dept> findParentList(String hhDeptId);

    public void updateDept(Dept dept);


}
