package com.fy.service;

import com.fy.mapper.ModuleMapper;
import com.fy.mapper.RoleMapper;
import com.fy.pojo.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class RoleServiceImpl implements  RoleService{

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ModuleMapper moduleMapper;


    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public void deletehhRoleId(String[] hhRoleIds) {
        roleMapper.deletehhRoleId(hhRoleIds);
    }

    @Override
    public void UpdateState(String[] hhRoleIds, int hhRoleStatus) {

        roleMapper.UpdateState(hhRoleIds,hhRoleStatus);
    }


    @Override
    public void SaveRole(Role role) {
        role.sethhRoleId(UUID.randomUUID().toString());
        role.setcreateTime(new Date());
        role.setupdateTime(role.getcreateTime());
        roleMapper.SaveRole(role);
    }

    @Override
    public Role updateRole(String hhRoleId) {

        return roleMapper.updateRole(hhRoleId);
    }

    @Override
    public void update(Role role) {

        role.setupdateTime(role.getcreateTime());
        roleMapper.update(role);
    }

    @Override
    public Role toview(String hhRoleId) {
        return roleMapper.toview(hhRoleId);
    }

    @Override
    public void saveRoleModules(String hhRoleId, String[] hhModuleIds) {
        roleMapper.deleteRole(hhRoleId);

        //保存角色和模块的关联关系
        for (String hhModuleId : hhModuleIds) {
            roleMapper.saveRoleModule(hhRoleId,hhModuleId);
        }

    }


}
