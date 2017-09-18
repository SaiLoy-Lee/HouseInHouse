package com.fy.service;

import com.fy.pojo.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface RoleService {

     public List<Role> findAll();

     public void deletehhRoleId(String[] hhRoleIds);

     public void UpdateState(String[] hhRoleIds, int hhRoleStatus);



     public void SaveRole(Role role);

     public Role updateRole(String hhRoleId);

     public void update(Role role);

    public Role toview(String hhRoleId);

    public void saveRoleModules(String hhRoleId, String[] hhModuleIds);
}


