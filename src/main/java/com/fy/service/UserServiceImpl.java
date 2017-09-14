package com.fy.service;

import com.fy.mapper.UserMapper;
import com.fy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service("UserService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;


    public List<User> findAll(){
        return userMapper.findAll();
    }

    @Override
    public void updateStatus(String[] hhUserIds, int hhUserStatus) {
        userMapper.updateStatus(hhUserIds,hhUserStatus);
    }

    @Override
    public void deleteUser(String hhUserIds) {
        userMapper.deleteUser(hhUserIds);
    }

    @Override
    public void saveUser(User user) {
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

}
