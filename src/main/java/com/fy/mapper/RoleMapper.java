package com.fy.mapper;

import com.fy.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface RoleMapper {

    public List<Role> findAll();

    public void deletehhroleId(String[] hhroleIds);

     public void toStart(@Param("hhroleIds") String[] hhroleIds,@Param("hhroleStatus") int hhroleStatus);

     public void toStop(@Param("hhroleIds")String[] hhroleIds,@Param("hhroleStatus") int hhroleStatus);

     public void SaveRole(Role role);

}

