package com.fy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017/9/13.
 */
@SpringBootApplication
@MapperScan(value = "com.fy.mapper")
public class SpringBootWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootWebApplication.class,args);
	}

}
