package com.fy.mapper;

import com.fy.pojo.Order;
import com.fy.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface OrderMapper {
    public void createOrder(Order order);


    List<Order> findAll();

    List<Order> findOrdersById(String hhUserId);

    List<Role> findRolesByUserId(String hhUserId);
}

