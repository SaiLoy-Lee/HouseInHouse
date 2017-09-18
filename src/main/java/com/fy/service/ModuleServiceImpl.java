package com.fy.service;

import com.fy.mapper.ModuleMapper;
import com.fy.pojo.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class ModuleServiceImpl implements ModuleService{

        @Autowired
        private ModuleMapper moduleMapper;

    @Override
    public List<Module> findAll() {
        return moduleMapper.findAll();
    }

    @Override
    public void UpdateState(String[] hhModuleIds, int hhModuleState) {

        moduleMapper.UpdateState(hhModuleIds,hhModuleState);
    }

    @Override
    public void saveModule(Module module) {

        module.sethhModuleId(UUID.randomUUID().toString());
        module.setCreateTime(new Date());
        module.setUpdateTime(module.getCreateTime());

        moduleMapper.saveModule(module);
    }

    @Override
    public void deleteModule(String[] hhModuleIds) {
        moduleMapper.deleteModule(hhModuleIds);
    }

    @Override
    public List<String> findModuleListByRoleId(String hhRoleId) {
        return moduleMapper.findModuleListByRoleId(hhRoleId);
    }


}
