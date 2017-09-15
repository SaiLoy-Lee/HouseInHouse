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
    public void deletehhroleId(String[] hhroleIds) {
        roleMapper.deletehhroleId(hhroleIds);
    }

    @Override
    public void toStart(String[] hhroleIds, int hhroleStatus) {

        roleMapper.toStart(hhroleIds,hhroleStatus);
    }

    @Override
    public void toStop(String[] hhroleIds, int hhroleStatus) {
        roleMapper.toStop(hhroleIds,hhroleStatus);
    }

    @Override
    public void SaveRole(Role role) {
        role.setHhroleId(UUID.randomUUID().toString());
        role.setCreateTime(new Date());
        role.setUpdateTime(role.getCreateTime());
        roleMapper.SaveRole(role);

    }


}
