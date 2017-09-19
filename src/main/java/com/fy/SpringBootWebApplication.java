package com.fy;

import com.fy.quartz.OrderJob;
import com.fy.quartz.UserJob;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Administrator on 2017/9/13.
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan(value = "com.fy.mapper")
public class SpringBootWebApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext= SpringApplication.run(SpringBootWebApplication.class,args);
        OrderJob.setApplicationContext(applicationContext);
        UserJob.setApplicationContext(applicationContext);
    }


}
