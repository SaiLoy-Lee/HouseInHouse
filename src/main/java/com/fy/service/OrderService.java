package com.fy.service;

import com.fy.Exception.MegException;
import com.fy.pojo.Order;
import com.fy.pojo.User;
import org.springframework.stereotype.Service;
import com.aliyuncs.exceptions.ClientException;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public interface OrderService {

    /**
     * 发送验证短信

     * @param order 订单信息（订单号）
     * @return
     * @throws ClientException
     * @throws InterruptedException
     */
    public int SendVerfyCode(Order order)throws ClientException, InterruptedException;

    /**
     * 创建订单
     * @param order 订单信息（订单号）
     * @return
     */
    public void createOrder(Order order,int numPer)throws MegException;

    /**
     * 根据用户权限查询全部订单信息
     * @param user
     * @return
     */
    List<Order> findAll(User user);

    /**
     * 根据用户查询用户全部订单信息
     * @param user
     * @return
     */
    public List<Order> findOrdersById(User user);
}
