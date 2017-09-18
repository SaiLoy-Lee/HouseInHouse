package com.fy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/9/18.
 */

@Controller
public class WebAjaxController{

    @RequestMapping("/getImage")
    public void getImage(String imgUrl, HttpServletResponse response){

        File img = new File(imgUrl);
        try {
            InputStream is=new FileInputStream(img);
            OutputStream os = response.getOutputStream();
            byte[] bs=new byte[1024*1024*5];
            int i = -1;
            while((i = is.read(bs)) != -1){
                os.write(bs,0,i);
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
