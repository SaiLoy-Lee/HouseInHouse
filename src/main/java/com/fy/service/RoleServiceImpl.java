package com.fy.service;

import com.fy.mapper.RoleMapper;
import com.fy.pojo.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class RoleServiceImpl implements  RoleService{


    private RoleMapper roleMapper;


    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public void deletehhRoleId(String[] hhRoleIds) {
        roleMapper.deletehhRoleId(hhRoleIds);
    }

    @Override
    public void toStart(String[] hhRoleIds, int hhRoleStatus) {

        roleMapper.toStart(hhRoleIds,hhRoleStatus);
    }

    @Override
    public void toStop(String[] hhRoleIds, int hhRoleStatus) {
        roleMapper.toStop(hhRoleIds,hhRoleStatus);
    }

    @Override
    public void SaveRole(Role role) {
        role.sethhRoleId(UUID.randomUUID().toString());
        role.setCreateTime(new Date());
        role.setUpdateTime(role.getCreateTime());
        roleMapper.SaveRole(role);
    }

    @Override
    public Role updateRole(String hhRoleId) {

        return roleMapper.updateRole(hhRoleId);
    }

    @Override
    public void update(Role role) {
        role.sethhRoleId(UUID.randomUUID().toString());
        role.setCreateTime(new Date());
        role.setUpdateTime(role.getCreateTime());
        roleMapper.update(role);
    }

    @Override
    public Role toview(String hhRoleId) {
        return roleMapper.toview(hhRoleId);
    }


}
