package com.fy.mapper;

import com.fy.pojo.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface RoleMapper {

     public List<Role> findAll();

     public void deletehhRoleId(String[] hhRoleIds);

     public void UpdateState(@Param("hhRoleIds") String[] hhRoleIds,@Param("hhRoleStatus") int hhRoleStatus);



     public void SaveRole(Role role);



     public void update(Role role);

     @Select("select * from hh_role where hh_role_id = #{hhRoleId}")
     public Role toview(String hhRoleId);
     @Select("select * from hh_role where hh_role_id = #{hhRoleId}")
     public Role updateRole(String hhRoleId);


     @Insert("insert into hh_module_role(hh_moduleid,hh_roleid) values(#{hhModuleId},#{hhRoleId})")
     public void saveRoleModule(@Param("hhRoleId") String hhRoleId,@Param("hhModuleId") String hhModuleId);

     @Delete("delete from hh_module_role where hh_roleid = #{hhRoleId}")
      public void deleteRole(String hhRoleId);
}

