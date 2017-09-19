package com.fy.mapper;

import com.fy.pojo.*;
import com.fy.pojo.HouseInfo;
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
    @Update("update  hh_orders set hh_orders_remarks=#{hhOrdersRemarks} where hh_Orders_id=#{hhOrdersId}")
    void updateOrderRemarks(@Param("hhOrdersRemarks")String hhOrdersRemarks, @Param("hhOrdersId")String hhOrdersId);
    @Select("select * from hh_house where hh_house_id =#{hh_house_id}")
    HouseInfo findHouseInfoById(String houseInfoId);


}

