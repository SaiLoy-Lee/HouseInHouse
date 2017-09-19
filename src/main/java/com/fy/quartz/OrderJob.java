package com.fy.quartz;

import com.aliyuncs.exceptions.ClientException;
import com.fy.pojo.Order;
import com.fy.pojo.SMessage;
import com.fy.pojo.User;
import com.fy.service.OrderService;
import com.fy.tools.SendCode;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public class OrderJob implements Job{
    private static ApplicationContext applicationContext;
    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        OrderService orderService  = (OrderService)applicationContext.getBean(OrderService.class);
        orderService.auto();
        List<Order> OrderList=orderService.findOrdersByStatus(3);
        for (Order order:OrderList ) {
            long outTime=order.getHhOrdersOuttime().getTime();
            long currentTime=new Date().getTime();
            double gapTime=(outTime-currentTime)/(24*3600*1000);

            if(gapTime>4&&gapTime<=5){
                SendCode sc  = (SendCode)applicationContext.getBean(SendCode.class);
                SMessage smessage = new SMessage();
                smessage.setHhSmessageUserID(order.getUser().getHhUserId());
                smessage.setHhSmessageRecipients(order.getUser().getHhUserName());
                smessage.setHhSmessageCell(order.getUser().getHhUserTel());
                smessage.setHhSmessageType("3");
                try {
                    sc.sendSms(smessage);
                } catch (ClientException e) {
                   // e.printStackTrace();
                } catch (InterruptedException e) {
                   // e.printStackTrace();
                }
            }

        }
        List<Order> OrderList1=orderService.findOrdersByStatus(1);
        for (Order order:OrderList ) {
            long outTime = order.getHhOrdersOuttime().getTime();
            long currentTime = new Date().getTime();
            double gapTime = (outTime - currentTime) / (24 * 3600 * 1000);
            if (gapTime >= 1 && gapTime < 2) {
                SendCode sc = (SendCode) applicationContext.getBean(SendCode.class);
                SMessage smessage = new SMessage();
                smessage.setHhSmessageUserID(order.getUser().getHhUserId());
                smessage.setHhSmessageRecipients(order.getUser().getHhUserName());
                smessage.setHhSmessageCell(order.getUser().getHhUserTel());
                smessage.setHhSmessageType("6");
                try {
                    sc.sendSms(smessage);
                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
