package com.fy.mapper;

import com.fy.pojo.Order;
import com.fy.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface OrderMapper {
    public void createOrder(Order order);

    List<Order> findAll();

    List<Order> findOrdersByUserId(String hhUserId);

    List<Role> findRolesByUserId(String hhUserId);

    Order findOrderByOrderId(String hhOrdersId);

    void updateOrderStatus(@Param("hhOrdersId") String hhOrdersId, @Param("status") String status);

    List<Order> findOrdersByStatus(Object p0);
}

