package com.fy.service;

import com.fy.mapper.UserMapper;
import com.fy.pojo.User;
import com.fy.tools.MD5HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll(){
        return userMapper.findAll();
    }

    @Override
    public void updateStatus(String[] hhUserIds, int hhUserStatus) {
        userMapper.updateStatus(hhUserIds,hhUserStatus);
    }

    @Override
    public void deleteUser(String[] hhUserIds) {
        userMapper.deleteUser(hhUserIds);
    }

    @Override
    public void saveUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setHhUserId(id);
        user.setCreateTime(new Date());
        user.setUpdateTime(user.getCreateTime());
        String md5Password = MD5HashPassword.getPassword(user.getHhUserUsername(),user.getHhUserPassword());
        user.setHhUserPassword(md5Password);
        userMapper.saveUser(user);
    }

    @Override
    public User findUserById(String hhUserId) {

        return userMapper.findUserById(hhUserId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User findUserByUsername(String hhUserUsername) {
        return userMapper.findUserByUsername(hhUserUsername);
    }

    @Override
    public List<String> findRoleIdList(String hhUserId) {
        return userMapper.findRoleIdList(hhUserId);
    }

    @Override
    public void saveUserRole(String hhUserId, String[] hhRoleIds) {
        userMapper.deleteUserRole(hhUserId);

        for (String roleId : hhRoleIds) {
            userMapper.saveUserRole(hhUserId,roleId);
        }
    }

    @Override
    public List<User> findUserByStatus(String status) {
        return userMapper.findUserByStatus(status);
    }


}
