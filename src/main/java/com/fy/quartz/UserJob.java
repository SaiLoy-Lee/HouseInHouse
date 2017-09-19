package com.fy.quartz;

import com.aliyuncs.exceptions.ClientException;
import com.fy.pojo.SMessage;
import com.fy.pojo.User;
import com.fy.service.OrderService;
import com.fy.service.UserService;
import com.fy.tools.SendCode;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
@Component
public class UserJob implements Job{
   /* @Autowired
    private User user;*/
   private static ApplicationContext applicationContext;
    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        UserService userService  = (UserService)applicationContext.getBean(UserService.class);

        List<User> userList = userService.findUserByStatus("1");
        if(userList.isEmpty() || userList ==null){
            return;
        }
        for(User user:userList) {
            String hhUserId = user.getHhUserId();
            String hhUserName = user.getHhUserName();
            String hhUserTel = user.getHhUserTel();
            String creatDept = user.getCreateDept();
            Date dateStoptime = user.getDept().getHhDeptStoptime();
            long stoptimeMiliseconds = dateStoptime.getTime();
           // SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateNow = new Date();
            long nowMiliseconds = dateNow.getTime();
            long seconds = stoptimeMiliseconds - 6*24*3600*1000 - nowMiliseconds;
            long seconds2 = 24*3600*1000;
            if(seconds >= 0 && seconds <= seconds2){

                SMessage smessage = new SMessage();
                smessage.setHhSmessageUserID(hhUserId);
                smessage.setHhSmessageRecipients(hhUserName);
                smessage.setHhSmessageCell(hhUserTel);
                smessage.setCreateDept(creatDept);
                smessage.setHhSmessageType("4");

                SendCode sc  = (SendCode)applicationContext.getBean(SendCode.class);
                try {
                    sc.sendSms(smessage);
                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
            long seconds3 =  nowMiliseconds - stoptimeMiliseconds;
            if(seconds >= 0 && seconds <= seconds2){

                SMessage smessage2 = new SMessage();
                smessage2.setHhSmessageUserID(hhUserId);
                smessage2.setHhSmessageRecipients(hhUserName);
                smessage2.setHhSmessageCell(hhUserTel);
                smessage2.setCreateDept(creatDept);
                smessage2.setHhSmessageType("2");
                SendCode sc = new SendCode();
                try {
                    sc.sendSms(smessage2);
                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
        }


    }

}
