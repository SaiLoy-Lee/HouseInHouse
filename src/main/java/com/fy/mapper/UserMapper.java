package com.fy.mapper;

import com.fy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public interface UserMapper {
    public List<User> findAll();

    public void updateStatus(@Param("hhUserIds") String[] hhUserIds, @Param("hhUserStatus") int hhUserStatus);

    public void deleteUser(String[] hhUserIds);

    public void saveUser(User user);

    public User findUserById(String hhUserId);

    public void updateUser(User user);

    public User findUserByUsername(String hhUserUsername);
}
