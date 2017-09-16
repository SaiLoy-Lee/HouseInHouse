package com.fy.mapper;

import com.fy.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface RoleMapper {

     public List<Role> findAll();

     public void deletehhRoleId(String[] hhRoleIds);

     public void toStart(@Param("hhRoleIds") String[] hhRoleIds,@Param("hhRoleStatus") int hhRoleStatus);

     public void toStop(@Param("hhRoleIds")String[] hhRoleIds,@Param("hhRoleStatus") int hhRoleStatus);

     public void SaveRole(Role role);

     public Role updateRole(String hhRoleId);

     public void update(Role role);

     @Select("select * from hh_role where hh_role_id = #{hhRoleId}")
     public Role toview(String hhRoleId);
}

