package com.fy.service;

import com.fy.pojo.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface RoleService {

     public List<Role> findAll();

     public void deletehhroleId(String[] hhroleIds);

     public void toStart(String[] hhroleIds, int hhroleStatus);

      public void toStop(String[] hhroleIds, int hhroleStatus);

     public void SaveRole(Role role);
}


