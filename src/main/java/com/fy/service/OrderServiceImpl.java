package com.fy.service;

import com.aliyuncs.exceptions.ClientException;
import com.fy.Exception.MegException;
import com.fy.mapper.HouseInfoMapper;
import com.fy.mapper.MessageMapper;
import com.fy.mapper.OrderMapper;
import com.fy.mapper.UserMapper;
import com.fy.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fy.tools.SendCode;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HouseInfoMapper houseInfoMapper;
    @Autowired
    UserMapper userMppaer;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    SendCode sendCode;

    @Override

    public int SendVerfyCode(Order order) throws ClientException, InterruptedException{
        String type="1";//短信类型
        return SendVerfyCodeReal(order,type);
    }

    @Override
    public void createOrder( Order order,int numPer)throws MegException {
        HouseInfo houseInfo=null;
        //houseInfo=houseInfoMapper.findHouseInfoById(HouseInfoId);
       if((houseInfo.getHhHouseMaxnum()-houseInfo.getHhHouseResidenum())>=numPer) {
           order.setCreateBy("Order");
           order.setCreateTime(new Date());
           order.setUpdateBy("Order");
           order.setUpdateTime(order.getCreateTime());
           order.setHhOrdersStatus(1);//[审核中1，审核未通过2,已入住3，已退房4，已取消5]
           houseInfo.setUpdateBy("Order");
           houseInfo.setUpdateTime(order.getCreateTime());
           if((houseInfo.getHhHouseMaxnum()-houseInfo.getHhHouseResidenum())<=numPer){
               houseInfo.setHhHouseStatus("1");//下架
           }
           houseInfo.setHhHouseResidenum(houseInfo.getHhHouseResidenum()+numPer);//增加入住人数numPer
          // houseInfoMapper.updateHouseInfo(houseInfo);
           orderMapper.createOrder(order);
       }else{;
           throw new MegException("对不起!您所选订的房源不足，请您重新选择");
       }
        String type="2";//短信类型
        SendVerfyCodeReal(order,type);
        Order orderM=new Order();

    }
    private int SendVerfyCodeReal(Order order,String type) {

        SMessage sMessage = new SMessage();
        sMessage.setHhSmessageCell(order.getUser().getHhUserTel());//手件人手机号
        //-------------------------------------------------------
        sMessage.setHhSmessageUserID(order.getUser().getHhUserId());//收件人Id
        sMessage.setHhSmessageRecipients(order.getUser().getHhUserName());//收件人
        sMessage.setHhSmessageOrdersId(order.getHhOrdersId());//订单id
        sMessage.setCreateBy("Order");
        sMessage.setHhSmessageType(type);//设置短信类型  1 代表订单
        try{
            return sendCode.sendSms(sMessage);
        }catch (Exception e){
            e.printStackTrace();

        }
        if(type=="2"){
            SMessage sMessageM = new SMessage();
            sMessage.setHhSmessageCell(order.getHouseInfo().getHhHouseTelephone());//手件人手机号
            //-------------------------------------------------------
            sMessageM.setHhSmessageUserID("");//收件人Id
            sMessageM.setHhSmessageRecipients(order.getHouseInfo().getHhHousePublisher());//收件人
            sMessageM.setHhSmessageOrdersId(order.getHhOrdersId());//订单id
            sMessageM.setCreateBy("Order");
            sMessageM.setHhSmessageType("10");//设置短信类型  1 代表订单
            try{
                return sendCode.sendSms(sMessage);
            }catch (Exception e){
                e.printStackTrace();

            }


        }
        return 0;


    }

    @Override
    public List<Order> findAll(User user) {
        String hhUserId=user.getHhUserId();
        List<Role> roleList=orderMapper.findRolesByUserId(hhUserId);
        for (Role role:roleList) {
        //    if("admin".equals(role.getHhroleName())){
                return orderMapper.findAll();
         //   }
        }
        return orderMapper.findOrdersById(hhUserId);
    }
    public List<Order> findOrdersById(User user){
        String hhUserId=user.getHhUserId();
        return orderMapper.findOrdersById(hhUserId);
    }
}
