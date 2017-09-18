package com.fy.mapper;

import com.fy.pojo.Module;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface ModuleMapper {
    public List<Module> findAll();

    public void UpdateState(@Param("hhModuleIds") String[] hhModuleIds,@Param("hhModuleState") int hhModuleState);

    public void saveModule(Module module);

    public void deleteModule(String[] hhModuleIds);

    @Select("select * from hh_module where hh_module_id = #{hhModuleId}")
    public Module findModule(String hhModuleId);

    public void updateModule(Module module);

    @Select("select hh_moduleid from hh_module_role where hh_roleid = #{hhRoleId}")
    public List<String> findModuleListByRoleId(String hhRoleId);


}
