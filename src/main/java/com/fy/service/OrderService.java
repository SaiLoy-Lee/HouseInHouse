package com.fy.service;

import com.fy.Exception.MegException;
import com.fy.pojo.HouseInfo;
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
     * @param
     * @return
     */
    public List<Order> findAll();

    /**
     * 根据用户查询用户全部订单信息
     * @param user
     * @return
     */
    public List<Order> findOrdersById(User user);

    /**
     *根据订单id查询订单信息
     * * @param hhOrdersId
     * @return
     */
    public Order findOrderByOrderId(String hhOrdersId);

    /**
     * 根据订单号批量修改订单状态
     * @param hhOrderIds
     */

    public void updateOrderStatus(String[] hhOrderIds,String status,String hhOrdersRemarks);

    /**
     * 取消订单
     * @param hhOrdersId
     * @throws MegException
     */
   public  void cancelOrder(String hhOrdersId) throws MegException;

    /**
     * 退租
     * @param hhOrdersId
     * @throws MegException
     */
    public void checkOutOrder(String hhOrdersId) throws MegException;

    public List<Order> findExcel();

    /**
     * 根据订单状态查订单
     * @param status
     * @return
     */
    public List<Order> findOrdersByStatus(int status);

    public HouseInfo findHouseInfoById(String houseInfoId);

    void auto();
}
