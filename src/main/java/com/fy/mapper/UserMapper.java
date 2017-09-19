package com.fy.mapper;

import com.fy.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("select hh_role_id from hh_role_user where hh_user_id = #{hhUserId}")
    public List<String> findRoleIdList(String hhUserId);

    @Delete("delete from hh_role_user where hh_user_id = #{hhUserId}")
    public void deleteUserRole(String hhUserId);

    @Insert("insert into hh_role_user(hh_role_id,hh_user_id) values (#{hhRoleId},#{hhUserId})")
    public void saveUserRole(@Param("hhUserId") String hhUserId, @Param("hhRoleId")String hhRoleId);

    public List<User> findUserByStatus(String hhUserStatus);
}
