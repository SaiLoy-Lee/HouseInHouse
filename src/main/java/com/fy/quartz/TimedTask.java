package com.fy.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2017/9/14.
 */
@Component
public class TimedTask {
//--------------------------------------------------------------------------
//        1.Seconds (0~59)
//        2.Minutes (0~59)
//        3.Hours (0~23)
//        4.Day-of-Month (1~31,但是要注意有些月份没有31天)
//        5.Month (0~11，或者"JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV,DEC")
//        6.Day-of-Week (1~7,1=SUN 或者"SUN, MON, TUE, WED, THU, FRI, SAT”)
//        7.Year (1970~2099
//       CronScheduleBuilder.cronSchedule("1 2 3 4 5 6 7"))
//--------------------------------------------
//        触发时间 每天十点自动运行定时任务
    private static String time = "1/10 * * * * ?";
    //private static String time = "0 0 10 * * ?";

    @PostConstruct
    public static void Start() throws Exception {

        JobKey jobKeyA = new JobKey("orderJob", "group1");

        JobDetail jobA = JobBuilder.newJob(OrderJob.class)
                .withIdentity(jobKeyA).build();

        JobKey jobKeyB = new JobKey("userJob", "group1");
        JobDetail jobB = JobBuilder.newJob(UserJob.class)
                .withIdentity(jobKeyB).build();

        //触发器1
        Trigger trigger1 = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName1", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(time))
                .build();
        //触发器2
        Trigger trigger2 = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName2", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(time))
                .build();
        //任务调度器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.start();
        scheduler.scheduleJob(jobA, trigger1);
        scheduler.scheduleJob(jobB, trigger2);


    }
}
