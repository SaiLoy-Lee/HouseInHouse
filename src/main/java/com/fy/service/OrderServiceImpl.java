package com.fy.service;

import com.aliyuncs.exceptions.ClientException;
import com.fy.Exception.MegException;
import com.fy.mapper.HouseInfoMapper;
import com.fy.mapper.MessageMapper;
import com.fy.mapper.OrderMapper;
import com.fy.mapper.UserMapper;
import com.fy.pojo.HouseInfo;
import com.fy.pojo.Order;
import com.fy.pojo.SMessage;
import com.fy.pojo.User;
import com.fy.tools.SendCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private HouseInfoMapper houseInfoMapper;
    @Autowired
    private UserMapper userMppaer;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private SendCode sendCode;

    @Override



    public int SendVerfyCode(Order order) throws ClientException, InterruptedException {
        String type = "1";//发送验证吗
        return SendVerfyCodeReal(order, type);
    }

    @Override
    public void createOrder(Order order, int numPer) throws MegException {
        HouseInfo houseInfo = null;
        houseInfo=orderMapper.findHouseInfoById(order.getHouseInfo().getHhHouseId());
        if ((houseInfo.getHhHouseMaxnum() - houseInfo.getHhHouseResidenum()) >= numPer) {
            order.setCreateBy("Order");
            order.setCreateTime(new Date());
            order.setUpdateBy("Order");
            order.setUpdateTime(order.getCreateTime());
            order.setHhOrdersStatus(1);//[审核中1，审核未通过2,已入住3，已退房4，已取消5]
            houseInfo.setUpdateBy("Order");
            houseInfo.setUpdateTime(order.getCreateTime());
            if ((houseInfo.getHhHouseMaxnum() - houseInfo.getHhHouseResidenum()) <= numPer) {
                houseInfo.setHhHouseStatus("1");//下架
            }
            houseInfo.setHhHouseResidenum(houseInfo.getHhHouseResidenum() + numPer);//增加入住人数numPer
            // houseInfoMapper.updateHouseInfo(houseInfo);
            orderMapper.createOrder(order);
        } else {
            ;
            throw new MegException("对不起!您所选订的房源不足，请您重新选择");
        }
        String type = "5";//表示用户的订单已经成功生成
        SendVerfyCodeReal(order, type);
        Order orderM = new Order();

    }

    private int SendVerfyCodeReal(Order order, String type) {

        SMessage sMessage = new SMessage();
        sMessage.setHhSmessageCell(order.getUser().getHhUserTel());//手件人手机号
        //-------------------------------------------------------
        sMessage.setHhSmessageUserID(order.getUser().getHhUserId());//收件人Id
        sMessage.setHhSmessageRecipients(order.getUser().getHhUserName());//收件人
        sMessage.setHhSmessageOrdersId(order.getHhOrdersId());//订单id
        sMessage.setCreateBy("Order");
        sMessage.setHhSmessageType(type);//设置短信类型  1 代表订单
        try {
            return sendCode.sendSms(sMessage);
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (type == "2") {
            SMessage sMessageM = new SMessage();
            sMessage.setHhSmessageCell(order.getHouseInfo().getHhHouseTelephone());//手件人手机号
            //-------------------------------------------------------
            sMessageM.setHhSmessageUserID("");//收件人Id
            sMessageM.setHhSmessageRecipients(order.getHouseInfo().getHhHousePublisher());//收件人
            sMessageM.setHhSmessageOrdersId(order.getHhOrdersId());//订单id
            sMessageM.setCreateBy("Order");
            sMessageM.setHhSmessageType("10");//设置短信类型  1 代表订单
            try {
                return sendCode.sendSms(sMessage);
            } catch (Exception e) {
                e.printStackTrace();

            }


        }
        return 0;


    }


    public List<Order> findAll() {

        return orderMapper.findAll();


    }

    public List<Order> findOrdersById(User user) {
        String hhUserId = user.getHhUserId();
        return orderMapper.findOrdersByUserId(hhUserId);
    }

    @Override
    public Order findOrderByOrderId(String hhOrdersId) {
        return orderMapper.findOrderByOrderId(hhOrdersId);
    }

    @Override
    public void updateOrderStatus(String[] hhOrderIds, String status, String hhOrdersRemarks) {
        if (hhOrderIds.length == 1 && hhOrdersRemarks != null && hhOrdersRemarks != "") {
            orderMapper.updateOrderRemarks(hhOrdersRemarks, hhOrderIds[0]);
        }
        for (String hhOrderId : hhOrderIds) {

            orderMapper.updateOrderStatus(hhOrderId, status);

        }

    }

    @Override
    public void cancelOrder(String hhOrdersId) throws MegException {
        Order order = orderMapper.findOrderByOrderId(hhOrdersId);
        if (order.getHhOrdersStatus() == 1) {
            String status = "5";
            orderMapper.updateOrderStatus(hhOrdersId, status);
            SendVerfyCodeReal(order, "6");

        } else {
            throw new MegException("取消失败，你的订单状态不正确，请确认！！！");
        }

    }

    @Override
    public void checkOutOrder(String hhOrdersId) throws MegException {
        Order order = orderMapper.findOrderByOrderId(hhOrdersId);
        if (order.getHhOrdersStatus() == 3) {
            String status = "6";
            orderMapper.updateOrderStatus(hhOrdersId, status);
            SendVerfyCodeReal(order, "6");

        } else {
            throw new MegException("取消失败，你的订单状态不正确，请确认！！！");
        }

    }

    public List<Order> findExcel() {
        return orderMapper.findAll();
    }

    @Override
    public List<Order> findOrdersByStatus(int status) {
        return orderMapper.findOrdersByStatus(status);
    }

    @Override
    public HouseInfo findHouseInfoById(String houseInfoId) {
        return orderMapper.findHouseInfoById(houseInfoId);
    }
    public void auto(){
        System.out.println("OrderServiceImpl定时任务中");
    }
}
