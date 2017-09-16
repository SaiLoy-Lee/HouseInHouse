package com.fy.service;

import com.fy.mapper.DeptMapper;
import com.fy.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptMapper deptMapper;


    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }


    @Override
    public void deleteDept(String[] hhDeptIds) {
        deptMapper.deleteDept(hhDeptIds);
    }

    @Override
    public void updateStatus(String[] hhDeptIds, int hhDeptStatus) {
        deptMapper.updateStatus(hhDeptIds, hhDeptStatus);
    }
    @Override
    public void saveDept(Dept dept) {
        dept.setCreateTime(new Date());
        dept.setUpdateTime(dept.getCreateTime());
        deptMapper.saveDept(dept);
    }


    @Override
    public Dept findDeptById(String hhDeptId) {
        return deptMapper.findDeptById(hhDeptId);
    }


   /* @Override
    public List<Dept> findParentList(String deptId) {
        return deptMapper.findParentList(deptId);
    }
*/

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(new Date());
        deptMapper.updateDept(dept);
    }

}
