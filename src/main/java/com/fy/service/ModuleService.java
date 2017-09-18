package com.fy.service;


import com.fy.pojo.Module;

import java.util.List;

public interface ModuleService {
    public List<Module> findAll();

   public void UpdateState(String[] hhModuleIds, int hhModuleState);

    public void saveModule(Module module);

    public void deleteModule(String[] hhModuleIds);


    public List<String> findModuleListByRoleId(String hhRoleId);

}
